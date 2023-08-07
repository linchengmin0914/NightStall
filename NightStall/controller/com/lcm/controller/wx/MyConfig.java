package com.lcm.controller.wx;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

public class MyConfig extends WXPayConfig {
	 @Value("${res.imgPath}")
     public String imgPath;
 
     private byte[] certData;
 
 
     @Override
     public String getAppID() {
    	 return "wx86949d4fa08d57fc";
     }
 
     @Override
     public String getMchID() {
    	 return "1596814181";
     }
 
     @Override
     public String getKey() {
    	 return "8d7111083ee13cdeab7d22e57492ce13";
     }
 
     @Override
     public InputStream getCertStream() {
	     ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
	     return certBis;
     }
 
     @Override
     public int getHttpConnectTimeoutMs() {
    	 return 8000;
     }
 
     @Override
     public int getHttpReadTimeoutMs() {
    	 return 10000;
     }
 
     @Override
     IWXPayDomain getWXPayDomain() {
    	 return new IWXPayDomain() {
	         @Override
	         public void report(String domain, long elapsedTimeMillis, Exception ex) {
	         }
	 
	         @Override
	         public DomainInfo getDomain(WXPayConfig config) {
	         return new DomainInfo("api.mch.weixin.qq.com", false);
	         }
	     };
     }
    
     public void initCert() throws Exception {
	     String certPath = "/etc/pki/ca-trust/source/anchorswj/"+"apiclient_cert.p12";//从微信商户平台下载的安全证书存放的目录
	     System.out.println(certPath);
	     File file = new File(certPath);
	     InputStream certStream = new FileInputStream(file);
	     this.certData = new byte[(int) file.length()];
	     certStream.read(this.certData);
	     certStream.close();
     }
}
