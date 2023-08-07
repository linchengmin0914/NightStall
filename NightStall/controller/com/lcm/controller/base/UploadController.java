package com.lcm.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.lcm.utils.file.CompressPictureUtil;
import com.lcm.utils.response.EditorResponse;
import com.lcm.utils.response.ResponseInfo;

/**
 * 上传图片
 * @author Administrator
 *
 */
@Controller
public class UploadController {

    //异步请求没有返回值，不用跳转页面，上传图片
	@ResponseBody
    @RequestMapping(value="/uploadPic" )
    public ResponseInfo uploadPic(@RequestParam(required=false) MultipartFile pic,HttpServletResponse response,HttpServletRequest request,
    		@RequestParam(required = false) String ppath){
        ResponseInfo responseInfo = null;
		try {
			
			//图片名称生成策略
			//1 因为这是一个高并发的，将时间精确到毫秒
			DateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//作为名称的一部分
			String format=df.format(new Date());
			//2随机三位数
			Random r= new Random();
			for(int i=0;i<3;i++){
			    format+=r.nextInt(10);
			}
			//得到上传文件的扩展名
			String ext=FilenameUtils.getExtension(pic.getOriginalFilename());
			//保存数据库的path
			String newFileName = format + "." + ext;
			String path = "upload/";
			if(!StringUtils.isEmpty(ppath)) {
				path = ppath;
			}
//			String savePath = "D:/" + path;
			String savePath = request.getRealPath("/") + path;
			File targetFile = new File(savePath + newFileName);  
			if(!targetFile.exists()){  
			    targetFile.mkdirs();  
			}  
			responseInfo = new ResponseInfo();
			//保存  
			pic.transferTo(targetFile);  
			responseInfo.setResCode(1);
			responseInfo.setResDes(path + newFileName);
			
			if(StringUtils.isEmpty(ppath)) {
				//图片压缩
				//获取压缩的图片类型，正常为small，normal，big；具体由配置文件设定
				String [] compressPictureType = {"big"};
				int len = compressPictureType.length;
				CompressPictureUtil compressPicture = new CompressPictureUtil();
				try {
					
					
					for (int i = 0; i < len; i++) {
						System.out.println("正在压缩：" + newFileName);
						compressPicture.compressPic(savePath, savePath, newFileName, newFileName.replace(".", "_yashuo."), 400*((i == 0?1:i) * (i + 1)), 400*((i == 0?1:i) * (i + 1)), true);
					}
				} catch (Exception e) {
					try{
						FileInputStream input=new FileInputStream(savePath + newFileName);//可替换为任何路径何和文件名
						FileOutputStream output=new FileOutputStream(savePath + newFileName.replace(".", "_yashuo."));//可替换为任何路径何和文件名
						int in=input.read();
						while(in!=-1){
						output.write(in);
						in=input.read();
						}
					}catch (IOException e1){
						System.out.println(e1.toString());
					}
				}
			}
			
			//文件复制
//			FileUtils.copyFileToDirectory(targetFile, new File("C:/Program Files/apache-tomcat-ylb/webapps/UDPei/upload"));
//			File targetFile2 = new File(savePath + newFileName.replace(".", "_yashuo."));  
//			FileUtils.copyFileToDirectory(targetFile2, new File("C:/Program Files/apache-tomcat-ylb/webapps/UDPei/upload"));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return responseInfo;
    }
	
	@ResponseBody
    @RequestMapping(value="/uploadImages" )
	public EditorResponse uploadImages(@RequestParam(required=false) MultipartFile images,HttpServletResponse response,HttpServletRequest request)  {
		//图片名称生成策略
        //1 因为这是一个高并发的，将时间精确到毫秒
        DateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //作为名称的一部分
        String format=df.format(new Date());
        //2随机三位数
        Random r= new Random();
        for(int i=0;i<3;i++){
            format+=r.nextInt(10);
        }
        //得到上传文件的扩展名
        String ext=FilenameUtils.getExtension(images.getOriginalFilename());

        //保存数据库的path
        String path = "upload/" + format + "." + ext;
        String savePath = request.getRealPath("/") + path;
//        String savePath = "D:/" + path;
        File targetFile = new File(savePath);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        
        EditorResponse editorResponse = new EditorResponse();
        
        //保存  
        try {  
        	images.transferTo(targetFile);  
        	editorResponse.setOriginal(images.getOriginalFilename());
        	editorResponse.setUrl("/" + path);
        	editorResponse.setTitle(images.getOriginalFilename());
        	editorResponse.setState("SUCCESS");
        	
        	//文件复制
//			FileUtils.copyFileToDirectory(targetFile, new File("D:/soft/apache-tomcat-7.0.37/webapps/UDPei2nd/upload"));
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return editorResponse;
	}
	
	
	@ResponseBody
    @RequestMapping(value="/uploadYs" )
    public ResponseInfo uploadYs(HttpServletResponse response,HttpServletRequest request, @RequestParam String base64,
    		@RequestParam(required = false) String ppath){
        ResponseInfo responseInfo = null;
        BASE64Decoder decoder = new BASE64Decoder();
		String picStr = "";
		 try {  
            //Base64解码  
			if(base64.indexOf("data:image/gif;base64") > -1) {
		    	base64 = base64.replace("data:image/gif;base64,", "");
		    } else if(base64.indexOf("data:image/png;base64") > -1) {
		    	base64 = base64.replace("data:image/png;base64,", "");
		    } else if(base64.indexOf("data:image/jpeg;base64") > -1) {
		    	base64 = base64.replace("data:image/jpeg;base64,", "");
		    } else if(base64.indexOf("data:image/x-icon;base64") > -1) {
		    	base64 = base64.replace("data:image/x-icon;base64,", "");
		    }
            byte[] b = decoder.decodeBuffer(base64);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0) {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成图片  
            String path = "upload/";
            if(!StringUtils.isEmpty(ppath)) {
            	path = ppath;
			}
            
            String savePath = request.getRealPath("/") + path;
//            String savePath = "D:/" + path;   
            File sFile = new File(savePath);
            if (!sFile.exists()) sFile.mkdirs();
            
    		String newFileName= "";//新文件名
    		Date date = new Date();
    		try {
    			Thread.sleep(500);
    		} catch (InterruptedException e1) {
    			e1.printStackTrace();
    		} 
    		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);//当前时间
    		newFileName = nowTime + ".jpg";  
    		picStr = path + nowTime + ".jpg";
            
            String imgFilePath = savePath + newFileName;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);  
            out.flush();  
            out.close(); 
            
            if(StringUtils.isEmpty(ppath)) {
            	String imgFilePath2 = savePath + nowTime + "_yashuo.jpg";//新生成的图片  
                OutputStream out2 = new FileOutputStream(imgFilePath2);
                out2.write(b);  
                out2.flush();  
                out2.close(); 
			}
            
            
        }   
        catch (Exception e)   {  
        	e.printStackTrace();
        }  
		
		responseInfo = new ResponseInfo(1, picStr);
        return responseInfo;
    }
}