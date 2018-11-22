package com.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//import com.constant.ContextConstant;






import com.huisou.constant.ContextConstant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**文件上传的工具类
 * Created by loneless on 2017/7/15.
 */
public class UploadUtils {
	private  static  final Logger logger = LoggerFactory.getLogger(UploadUtils.class);
    /**
     * 上传单个图片，参数一是springmvc接收的file
     * ,string 是文件保存地址
     * @param file
     * @param pathname
     * @param userId 
     * @param filetype 上传的类型
     * @return
     */
    public static String uploadimage(MultipartFile file, String pathname, int userId){
        if (!file.isEmpty() && StringUtils.isNotBlank(pathname)) {
            //判断文件的类型和文件的大小
            BufferedOutputStream out = null;
            try {
            	pathname=pathname+userId+"/";
            	if(!new File(pathname).exists()){
            		new File(pathname).mkdir();
            	}
                int k = file.getContentType().indexOf("/") + 1;
                String suffix = file.getContentType().substring(k);
                String[] filetype={"bmp", "jpg","jpeg", "png", "gif", "webp"};
                boolean imageType=false;
                for (int i = 0; i < filetype.length; i++) {
                    if (filetype[i].equals(suffix)) {
                        imageType=true;
                    }
                }
                if(!imageType){
                	return ContextConstant.IMAGE_TYPE;
                }
                //限制文件的大小
                if(file.getSize()>10485760){
                	return ContextConstant.SIZE_EXCEEDS;
                }
                String image = System.nanoTime()+"."+suffix;
                String url=pathname+image;
                logger.info("上传upload地址==="+url);
                out = new BufferedOutputStream(new FileOutputStream(new File(url)));
                out.write(file.getBytes());
                out.flush();
              
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return ContextConstant.UPLOAD_FAILURE;
            }finally{
                try {
                	if(out!=null)
                		out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        
        } else {
            return ContextConstant.IMAGE_NULL;
        }
    }

    /** 上传多个文件
     * 一个文件判断错误，全部不保存
     */
    public static List<String> uploadImages(List<MultipartFile> files, String pathName) {
        MultipartFile file = null;
        List<String> urls=new ArrayList<String>();
        BufferedOutputStream out = null;
        //先进行判断
        for (int i =0; i< files.size(); i++) {
            file = files.get(i);
            if (!file.isEmpty()) {
                //判断文件的类型和文件的大小
                try {
	                    int k = file.getContentType().indexOf("/") + 1;
	                    String suffix = file.getContentType().substring(k);
	                    String[] filetype={"bmp", "jpg","jpeg", "png", "gif", "webp"};
	                    boolean imageType=false;
	                    for (int j = 0; j < filetype.length; j++) {
	                        if (filetype[j].equals(suffix)) {
	                            imageType=true;
	                        }
	                    }
	                    if(!imageType){
	                    	urls.add(ContextConstant.IMAGE_TYPE);
	                    	return urls;
	                    }
	                    //限制文件的大小
	                    if(file.getSize()>10485760){
	                    	urls.add(ContextConstant.SIZE_EXCEEDS);
	                    	return urls;
	                    } 
                    } catch (Exception e) {
                        e.printStackTrace();
                        urls.add(ContextConstant.UPLOAD_FAILURE);
                        return urls;
                    }
            }else {
				urls.add(ContextConstant.IMAGE_NULL);
				return urls;
			}
        }
        
        
        for (int i = 0; i < files.size(); i++) {
            
            //保存所有的图片
            try {
				int k = file.getContentType().indexOf("/") + 1;
				String suffix = file.getContentType().substring(k);
				String url=pathName+System.nanoTime()+"."+suffix;
				out = new BufferedOutputStream(new FileOutputStream(new File(url)));
				out.write(file.getBytes());
				out.flush();
				urls.add(url);
			} catch (IOException e) {
				urls.add(ContextConstant.UPLOAD_FAILURE);
				e.printStackTrace();
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
        return urls;
        
        
    }
    /**
     * 用来上传文件的，比上传图片多增加一步对相同名称的文件进行判断
     * @param file
     * @param pathname
     * @param userId
     * @return
     */
	public static String uploaddoc(MultipartFile file, String pathname,
			int userId) {
		 if (!file.isEmpty() && StringUtils.isNotBlank(pathname)) {
	            //判断文件的类型和文件的大小
	            BufferedOutputStream out = null;
	            pathname=pathname+userId+"/";
	            try {
	            	if(!new File(pathname).exists()){
	            		new File(pathname).mkdir();
	            	}
	            	String docname=file.getOriginalFilename();
	                String suffix =docname.substring(docname.lastIndexOf(".") + 1);
	                String[] filetype={"doc","txt", "docx","jpg", "pdf", "xls", "xlsx","zip","rar"};
	                boolean imageType=false;
	                for (int i = 0; i < filetype.length; i++) {
	                    if (filetype[i].equals(suffix)) {
	                        imageType=true;
	                    }
	                }
	                if(!imageType){
	                	return ContextConstant.IMAGE_TYPE;
	                }
	                //限制文件的大小
	                if(file.getSize()>10485760){
	                	return ContextConstant.SIZE_EXCEEDS;
	                }
	                //拼接文件保存路径
	                String url=pathname+docname;
	                String prefix = docname.substring(0, docname.lastIndexOf(".")-1);
	                int i=0;
	                while(true){
	                	if(new File(url).exists()){
	                		i++;
	                		docname=prefix+i+"."+suffix;
	                		url=pathname+docname;
	                	}else {
							break;
						}
	                }
	                out = new BufferedOutputStream(new FileOutputStream(new File(url)));
	                out.write(file.getBytes());
	                out.flush();
	              
	                return docname;
	            } catch (IOException e) {
	                e.printStackTrace();
	                return ContextConstant.UPLOAD_FAILURE;
	            }finally{
	                try {
	                	if(out!=null)
	                		out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            }
	        
	        } else {
	            return ContextConstant.IMAGE_NULL;
	        }
	}
}
