package com.huisou.controller;

import java.util.List;

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
    
}
