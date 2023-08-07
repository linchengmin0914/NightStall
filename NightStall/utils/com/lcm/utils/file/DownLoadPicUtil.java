package com.lcm.utils.file;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern; 

/*** 
 * java抓取网络图片 
 */  

public class DownLoadPicUtil {
	// 编码  
    private static final String ECODING = "UTF-8";  
    // 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    // 获取src路径的正则  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";  
  
    public static void downloadPic(String url) {  
        // 获得html文本内容  
        String HTML = null;  
        try {  
            HTML = getHTML(url);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        if (null != HTML && !"".equals(HTML)) {  
  
            // 获取图片标签  
            List<String> imgUrl = getImageUrl(HTML);  
            // 获取图片src地址  
            List<String> imgSrc = getImageSrc(imgUrl);  
            // 下载图片  
            download(imgSrc);  
        }  
    }  
  
    /*** 
     * 获取HTML内容 
     *  
     * @param url 
     * @return 
     * @throws Exception 
     */  
    private static String getHTML(String url) throws Exception {  
        URL uri = new URL(url);  
        URLConnection connection = uri.openConnection();  
        InputStream in = connection.getInputStream();  
        byte[] buf = new byte[1024];  
        int length = 0;  
        StringBuffer sb = new StringBuffer();  
        while ((length = in.read(buf, 0, buf.length)) > 0) {  
            sb.append(new String(buf, ECODING));  
        }  
        in.close();  
        return sb.toString();  
    }  
  
    /*** 
     * 获取ImageUrl地址 
     *  
     * @param HTML 
     * @return 
     */  
    private static List<String> getImageUrl(String HTML) {  
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);  
        List<String> listImgUrl = new ArrayList<String>();  
        while (matcher.find()) {  
            listImgUrl.add(matcher.group());  
        }  
        return listImgUrl;  
    }  
  
    /*** 
     * 获取ImageSrc地址 
     *  
     * @param listImageUrl 
     * @return 
     */  
    private static List<String> getImageSrc(List<String> listImageUrl) {  
        List<String> listImgSrc = new ArrayList<String>();  
        for (String image : listImageUrl) {  
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);  
            while (matcher.find()) {  
                listImgSrc.add(matcher.group().substring(0,  
                        matcher.group().length() - 1));  
            }  
        }  
        return listImgSrc;  
    }  
  
    /*** 
     * 下载图片 
     *  
     * @param listImgSrc 
     */  
    private static void download(List<String> listImgSrc) {  
  
        for (String url : listImgSrc) {  
            try {  
                String imageName = url.substring(url.lastIndexOf("/") + 1,  
                        url.length());  
                System.out.println("=====imageName=======" + imageName);  
                URL uri = new URL(url);  
                InputStream in = uri.openStream();  
                FileOutputStream fo = new FileOutputStream(new File("D:/upload/" + imageName));  
                byte[] buf = new byte[1024];  
                int length = 0;  
                while ((length = in.read(buf, 0, buf.length)) != -1) {  
                    fo.write(buf, 0, length);  
                }  
                in.close();  
                fo.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    public static void main(String[] args) {
		String url = "https://mp.weixin.qq.com/s?__biz=MzI2NDAxOTYxMA==&tempkey=xnmEj5v5OPw0b8DRZLlyEVZcVkO%2Fghp7Er4qycp0DTbFieCStYMx7mdqTkcWASAWcC6VW5Fh2bLHAlVGUuPMt2xQ6yHqZnWuPrNlFCsKTaJP209KMavLezgUl3Wu7hRckbM8ewWrK%2BF2cM0CFbNgkA%3D%3D&chksm=71065b694671d27feef45a71156d2337e534643f884ab4a267d8b7ebf55cbad12a46df611b49#rd";
		downloadPic(url);
		
	}
}
