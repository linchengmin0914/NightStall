package com.lcm.utils.search;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.ArrayList;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class HtmlParser {  
    /** 
     * 要分析的网页 
     */  
    String htmlUrl;  
  
    /** 
     * 分析结果 
     */  
    ArrayList<String> hrefList = new ArrayList();  
  
    /** 
     * 网页编码方式 
     */  
    String charSet;  
  
    public HtmlParser(String htmlUrl) {  
        // TODO 自动生成的构造函数存根  
        this.htmlUrl = htmlUrl;  
    }  
  
    /** 
     * 获取分析结果 
     *  
     * @throws IOException 
     */  
    public ArrayList<String> getHrefList() throws IOException {  
  
        parser();  
        return hrefList;  
    }  
  
    /** 
     * 解析网页链接 
     *  
     * @return 
     * @throws IOException 
     */  
    private void parser() throws IOException {  
        URL url = new URL(htmlUrl);  
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.setDoOutput(true);  
  
        String contenttype = connection.getContentType();  
        charSet = getCharset(contenttype);  
  
        InputStreamReader isr = new InputStreamReader(  
                connection.getInputStream(), charSet);  
        BufferedReader br = new BufferedReader(isr);  
  
        String str = null, rs = null;  
        while ((str = br.readLine()) != null) {  
            rs = getHref(str);  
  
            if (rs != null)  
                hrefList.add(rs);  
        }  
  
    }  
  
    /** 
     * 获取网页编码方式 
     *  
     * @param str 
     */  
    private String getCharset(String str) {  
        Pattern pattern = Pattern.compile("charset=.*");  
        Matcher matcher = pattern.matcher(str);  
        if (matcher.find())  
            return matcher.group(0).split("charset=")[1];  
        return null;  
    }  
  
    /** 
     * 从一行字符串中读取链接 
     *  
     * @return 
     */  
    private String getHref(String str) {  
        Pattern pattern = Pattern.compile("<a target=\"_blank\" href=.*</a>");  
        Matcher matcher = pattern.matcher(str);  
        if (matcher.find())  
            return matcher.group(0);  
        return null;  
    }  
    
    private String getURL(String str) {
    	Matcher matcher = Pattern.compile("href=['\"]([^'\"]*)['\"]").matcher(str);
        if (matcher.find())  {
        	String url = matcher.group(0);
        	return url.substring(6,url.length() - 1).replace("&amp;", "&");  
        }
        return null;  
    }
  
    public static void main(String[] arg) throws IOException {  
    	for (int j = 0; j < 100; j++) {
    		  HtmlParser a = new HtmlParser("http://weixin.sogou.com/weixin?query=%E4%BC%98%E5%BE%97%E9%85%8D%E5%A6%99%E6%90%AD&_sug_type_=&sut=9394&lkt=4%2C1494398855322%2C1494398855615&s_from=input&_sug_=n&type=2&sst0=1494398863277&page=" + j + "&ie=utf8&w=01019900&dr=1");  
    	        ArrayList<String> hrefList = a.getHrefList();  
    	        for (int i = 0; i < hrefList.size(); i++) {
    	        	String ahref = hrefList.get(i);
//    	        	System.out.println(ahref);  
    	        	System.out.println(a.getURL(ahref));
    	        }
		}
      
    }  
  
}  