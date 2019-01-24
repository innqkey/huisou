package com.huisou.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.FastDFSClient;
import com.common.ResUtils;
import com.common.ThreadPoolUtil;

/**
 * @author qinkai
 * @date 2018年12月14日
 */
@RestController
@RequestMapping("fastdfs")
public class FastDFSController {
    
    private  static  final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @PostMapping("file/upload") 
    public String singleFileUpload(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return ResUtils.errRes(ResUtils.exceCode, "上传文件为空");
        }
        try {
            // Get the file and save it somewhere
            String path=FastDFSClient.saveFile(file);
            return ResUtils.okRes(path);
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }
        return ResUtils.errRes(ResUtils.exceCode, "上传文件有误");
    }
    
    @PostMapping("/batch/upload")
    public String batchFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        int i = 0;
        if (null != files && files.size() > 0){
            long startTime = System.currentTimeMillis();
            for (MultipartFile file : files) {
                if (null == file || file.isEmpty()) {
                    return ResUtils.errRes(ResUtils.exceCode, "上传文件为空");
                }
                try {
                    // Get the file and save it somewhere
                    String path=FastDFSClient.saveFile(file);
                    i++;
                    logger.info("已上传文件数： " + i);
                } catch (Exception e) {
                    logger.error("upload file failed, fileName: ", file.getOriginalFilename());
                }
            }
            long entTime = System.currentTimeMillis();
            logger.info("upload " + files.size() + ", time " + (entTime - startTime) / 1000);
            return ResUtils.okRes();
        }
        return ResUtils.execRes();
    }
    
    //多线程,批量上传
    @PostMapping("/batchs/upload")
    public String batchsFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (null != files && files.size() > 0){
            //使添加线程等待转换线程执行后执行的工具
            final CountDownLatch doWork = new CountDownLatch(files.size()); 
            long startTime = System.currentTimeMillis();
            try {
                for (MultipartFile file : files) {
                    if (null == file || file.isEmpty()) {
                        doWork.countDown();
                        logger.info("上传的文件为空，文件名: ", file.getOriginalFilename());
                        continue;
                    }
                    ThreadPoolUtil.submit(new Runnable() {
                        public void run() {
                            try {
                                String path=FastDFSClient.saveFile(file);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //递减锁存器的计数
                            doWork.countDown();
                        }
                    });
                }
                //添加线程等待转换线执行
                doWork.await();
                long entTime = System.currentTimeMillis();
                logger.info("上传文件总数 " + files.size() + ", 用时, " + (entTime - startTime) / 1000 + " s");
                return ResUtils.okRes();
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("上传文件失败");;
            }
        }
        return ResUtils.execRes("上传文件为空");
    }
    
    //filenName: group1/M00/00/00/rBDHA1wTK3eAUnCkAAAFSQy1dxc203.png
    @GetMapping("file/delete")
    public String deleteFileFromDFS(String fileName){
        try {
            if (fileName.startsWith("/")){
                fileName = fileName.substring(1);
            }
            String[] split = fileName.split("/", 2);
            if (split.length != 2){
                return ResUtils.errRes(ResUtils.exceCode, "文件名不正确");
            }
            //logger.info("groupName: " + split[0] + ", remoteFileName: " + split[1]);
            int ret = FastDFSClient.deleteFile(split[0], split[1]);
            if (ret == 0){
                logger.info("delete success, groupName: " + split[0] + ", remoteFileName: " + split[1]);
                return ResUtils.okRes();
            } else if (ret == 2){
                logger.info("file is not exist, groupName: " + split[0] + ", remoteFileName: " + split[1]);
                return ResUtils.okRes("文件不存在");
            }
        } catch (Exception e) {
            logger.error("delete file error",e);
        }
        return ResUtils.errRes(ResUtils.exceCode, "删除文件失败");
    }
    
    @GetMapping("file/test")
    public String test (){
        final CountDownLatch doWork = new CountDownLatch(10); 
        for (int i = 0 ; i < 10; i++){
            //使添加线程等待转换线程执行后执行的工具
            try {
                    ThreadPoolUtil.submit(new Runnable() {
                        public void run() {
                            try {
                                FastDFSClient.uploadFile();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //递减锁存器的计数
                            doWork.countDown();
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("上传文件失败");
            }
        }
        
        //添加线程等待转换线执行
        try {
            doWork.await();
            logger.info("测试完成》》》》》》》》》》》》");
            return ResUtils.okRes();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResUtils.execRes();
    }
    
}
