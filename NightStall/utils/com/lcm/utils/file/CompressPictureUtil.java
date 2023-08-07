package com.lcm.utils.file;

import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder; 

/**
 * 压缩图片工具类，用于压缩图片，有两种压缩方法，其一为
 * 等比压缩，其二为固定长短压缩，一般使用等比压缩
 * date：2013-9-9 11:24:22
 * @author usher
 * */
public class CompressPictureUtil {
    private File file = null; // 文件对象   
    private String inputDir; // 输入图路径  
    private String outputDir; // 输出图路径  
    private String inputFileName; // 输入图文件名  
    private String outputFileName; // 输出图文件名  
    private int outputWidth = 100; // 默认输出图片宽  
    private int outputHeight = 100; // 默认输出图片高  
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)  

    public CompressPictureUtil() { // 初始化变量  
	inputDir = "";   
	outputDir = "";   
	inputFileName = "";   
	outputFileName = "";   
	outputWidth = 100;   
	outputHeight = 100;   
    }   

    /**  
     * 获得图片大小  
     * 传入参数 String path ：图片路径  
     * */   
    public long getPicSize(String path) {   
	file = new File(path);   
	return file.length();   
    }  

    /**
     * 图片处理
     * date：2013-9-9 11:25:21
     * */
    public String compressPic() {   
	try {   
	    //获得源文件   
	    file = new File(inputDir + inputFileName); 

	    if (!file.exists()) {   
		return "";   
	    }   

	    Image img = ImageIO.read(file);   

	    // 判断图片格式是否正确   
	    if (img.getWidth(null) == -1) {  
		return "no";   

	    } else {   
		int newWidth; int newHeight;  

		// 判断是否是等比缩放   
		if (this.proportion == true) {   
			// 为等比缩放计算输出的图片宽度及高度   
			double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
			double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
			// 根据缩放比率大的进行缩放控制   
			double rate = rate1 > rate2 ? rate1 : rate2;   
			newWidth = (int) (((double) img.getWidth(null)) / rate);   
			newHeight = (int) (((double) img.getHeight(null)) / rate);  

		} else {   
			newWidth = outputWidth; // 输出的图片宽度   
			newHeight = outputHeight; // 输出的图片高度   
		}   

		BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   

		/* 
		 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
		 * 优先级比速度高 生成的图片质量比较好 但速度慢 
		 */
		try {
			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
			FileOutputStream out = new FileOutputStream(outputDir + outputFileName);  
			// JPEGImageEncoder可适用于其他图片类型的转换   
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
			encoder.encode(tag);   
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
				   
	    }  

    	} catch (IOException ex) {   
    		ex.printStackTrace();   
    	}   
    	return "ok"; 
    }   

    /**
     * 压缩图片，调用方法可设置图片的保存路径，生成图片名等，按等比压缩
     * date：2013-9-9 11:26:13
     * @param inputDir 源图片的路径
     * @param outputDir 图片的输入路径
     * @param inputFileName 原图片名
     * @param outputFileName 输出文件名
     * */
    public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) {   
    	// 输入图路径   
    	this.inputDir = inputDir;   
    	// 输出图路径   
    	this.outputDir = outputDir;   
    	// 输入图文件名   
    	this.inputFileName = inputFileName;   
    	// 输出图文件名  
    	this.outputFileName = outputFileName;   
    	return compressPic();   
    }   

    /**
     * 压缩图片，调用方法可设置图片的保存路径，生成图片名等，按等比压缩
     * date：2013-9-9 11:26:13
     * @param inputDir 源图片的路径
     * @param outputDir 图片的输入路径
     * @param inputFileName 原图片名
     * @param outputFileName 输出文件名
     * @param width 缩小化图片的宽度，或缩小的比例
     * @param height 缩小化图片的高度，或缩小的比例
     * @param gp 是否进行等比压缩，若true表示是
     * */
    public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {   
    	// 输入图路径   
    	this.inputDir = inputDir;   
    	// 输出图路径   
    	this.outputDir = outputDir;   
    	// 输入图文件名   
    	this.inputFileName = inputFileName;   
    	// 输出图文件名   
    	this.outputFileName = outputFileName;   
    	// 设置图片长宽  
    	setWidthAndHeight(width, height);   
    	// 是否是等比缩放 标记   
    	this.proportion = gp;   
    	return compressPic();   
    }   

    public void setInputDir(String inputDir) {   
    	this.inputDir = inputDir;   
    }  
    
    public void setOutputDir(String outputDir) {   
    	this.outputDir = outputDir;   
    }  
    
    public void setInputFileName(String inputFileName) {   
    	this.inputFileName = inputFileName;  
    }  
    
    public void setOutputFileName(String outputFileName) {   
    	this.outputFileName = outputFileName;   
    }  
    
    public void setOutputWidth(int outputWidth) {  
    	this.outputWidth = outputWidth;   
    } 
    
    public void setOutputHeight(int outputHeight) {   
    	this.outputHeight = outputHeight;   
    }  
    
    public void setWidthAndHeight(int width, int height) {   
    	this.outputWidth = width;  
    	this.outputHeight = height;   
    }  

     // main测试   
     // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))  
     public static void main(String[] arg) {   
     	CompressPictureUtil mypic = new CompressPictureUtil();   
     	System.out.println("输入的图片大小：" + mypic.getPicSize("C:\\Users\\ASUS\\Desktop\\Desert - 副本 (3).jpg")/1024 + "KB");   
    
     	int count = 0; // 记录全部图片压缩所用时间  
     	for (int i = 0; i < 100; i++) {   
     		int start = (int) System.currentTimeMillis();   // 开始时间   
     		mypic.compressPic("C:\\Users\\ASUS\\Desktop\\", "C:\\Users\\ASUS\\Desktop\\test\\", "Desert - 副本 (3).jpg", "r1"+i+".jpg");   
     		int end = (int) System.currentTimeMillis(); // 结束时间   
     		int re = end-start; // 但图片生成处理时间   
     		count += re; System.out.println("第" + (i+1) + "张图片压缩处理使用了: " + re + "毫秒");   
     		System.out.println("输出的图片大小：" + mypic.getPicSize("e:\\test\\r1"+i+".jpg")/1024 + "KB");   
     	}  
     	System.out.println("总共用了：" + count + "毫秒");   
     }  
}
