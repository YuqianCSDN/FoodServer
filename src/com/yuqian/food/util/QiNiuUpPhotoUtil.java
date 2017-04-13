package com.yuqian.food.util;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

public class QiNiuUpPhotoUtil {
	 private final String ACCESS_KEY = "Z_UFHtV2vX1L_JYvEGhxv1RCN5xLBkzEWKVQWfur"; //这两个登录七牛 账号里面可以找到  
	 private final String SECRET_KEY = "DBbYQzCsEpKEIZnFhEX0FG11j0DrLoaISnH3G5FO";  

	    //要上传的空间  
	 private final String bucketname = "oldbook"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）  
	    //上传到七牛后保存的文件名  
	 
	    //密钥配置  
	 private  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);  
	    //创建上传对象  
	 private UploadManager uploadManager = new UploadManager();  

	    
	     
	    //普通上传  
	    public  boolean upload(File file,String key) {  
	    	String token=auth.uploadToken(bucketname);
	      try {  
	        //调用put方法上传
	        Response res = uploadManager.put(file, key, token);
	        System.out.println(res.isOK());
	        //打印返回的信息  
	        System.out.println(res.bodyString());   
	        if(res.isOK()) {
	        	return res.isOK(); 
	        }
	        } catch (QiniuException e) {  
	            Response r = e.response;  
	            // 请求失败时打印的异常的信息  
	            System.out.println(r.toString());  
	            try {  
	                //响应的文本信息  
	              System.out.println(r.bodyString());  
	            } catch (QiniuException e1) {  
	                //ignore  
	            }  
	        }
		return false;         
	    }  
}
