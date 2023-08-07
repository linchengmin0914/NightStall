package com.lcm.utils.wx.template;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.wx.common.AccessToken;
import com.lcm.utils.wx.common.WeixinUtil;
import com.lcm.utils.wx.pay.CommonUtil;
import com.lcm.utils.wx.pay.PayCommonUtil;
import com.lcm.utils.wx.pay.Sha1Util;
import com.lcm.utils.wx.pay.XMLUtil;

/**
 * 发送模板信息
 * http://code.taobao.org/p/springwechat/src/trunk/src/org/liufeng/
 * */

public class SendTemplateMsgUtil {
	private final static Logger log = LoggerFactory.getLogger(SendTemplateMsgUtil.class);
	
	 /**
     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识
     * temp 模板内容
     */
    public static void sendTemplateMessage(String appId, String appSecret, String openId, WxTemplate temp) {
        AccessToken token = WeixinUtil.getAccessToken(appId, appSecret);
        String access_token = token.getToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
        
        String jsonString = JSONObject.fromObject(temp).toString();
       
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);
//        System.out.println(jsonObject);
        int result = 0;
        if (null != jsonObject) {  
             if (0 != jsonObject.getInt("errcode")) {  
                 result = jsonObject.getInt("errcode");
                 log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
             }  
         }
        log.info("模板消息发送结果："+result);
    }
    
    /**
     * 发送每日更新信息
     * @param openId 通知的用户openid
     * @param url 跳转的URL 为空的时候不跳转
     * */
    public static ResponseInfo sendUpdateMsg(String openId, String url, 
    		String a, String b, String c, String d, String e, String f) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("3GsqIj9_xX1VkfL-frriFmZZ3IZkYRnI68Phx5yiDso");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue("优得配又有您感兴趣的内容，等待着您去关注。如想退订，请点击左下角“我要”->“退订通知”");  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#FF0000");  
			
			keyword1.setValue("\n有:\n" + a + "" + b + "" + c + "" + d + "" + e + "" + f + "");  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
			m.put("keyword2", keyword2);
			
			temp.setData(m);
			
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception exception) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
    
    /**
     * 发送方案
     * */
    public static ResponseInfo sendFAMsg(String openId, String title, String url) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("3GsqIj9_xX1VkfL-frriFmZZ3IZkYRnI68Phx5yiDso");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue("优得配又有新的方案内容，等待着您去关注。");  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#FF0000");  
			
			keyword1.setValue(title);  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
			m.put("keyword2", keyword2);
			
			temp.setData(m);
			
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception e) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
    
    /**
     * 发送妙答信息
     * @param openId 通知的用户openid
     * @param url 跳转的URL 为空的时候不跳转
     * */
    public static ResponseInfo sendJXWZMsg(String openId, String url, Object count) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("3GsqIj9_xX1VkfL-frriFmZZ3IZkYRnI68Phx5yiDso");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue("优得配又有新的妙搭内容，等待着您去关注。");  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#FF0000");  
			
			keyword1.setValue("妙搭（" + count + "）篇");  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
			m.put("keyword2", keyword2);
			
			temp.setData(m);
			
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception e) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
    
    /**
     * 发送配件/求购/材料审核信息
     * @param openId 通知的用户openid
     * @param header 信息头
     * @param url 跳转的URL 为空的时候不跳转
     * @param title 标题
     * @param checkStatus 审核结果
     * @param time 审核时间
     * */
    public static ResponseInfo sendCheckMsg(String openId, String url, String header,
    		String title, String checkStatus, String time) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("DqMuheB9i5v_4AAQrb5HdFzjooBLKddVVWqix3wSEJA");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue(header);  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#000000");  
			keyword1.setValue(title);  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(checkStatus);  
			m.put("keyword2", keyword2);
			
			TemplateData keyword3 = new TemplateData();  
			keyword3.setColor("#000000");  
			keyword3.setValue(time);  
			m.put("keyword3", keyword3);
			
			temp.setData(m);
			
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception e) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
   
    /**
     * 发送求购匹配成功信息
     * @param openId 通知的用户openid
     * @param header 信息头
     * @param url 跳转的URL 为空的时候不跳转
     * @param title 标题
     * @param matchStatus 审核结果
     * @param time 审核时间
     * */
    public static ResponseInfo sendMatchMsg(String openId, String url, String header,
    		String title, String matchStatus, String time) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("4bhdziIElz0VKolN4nMMmbbWX3HBRfSfybLKxVSVmxQ");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue(header);  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#000000");  
			keyword1.setValue("求购匹配");  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(title);  
			m.put("keyword2", keyword2);
			
			TemplateData keyword3 = new TemplateData();  
			keyword3.setColor("#000000");  
			keyword3.setValue(matchStatus);  
			m.put("keyword3", keyword3);
			
			TemplateData keyword4 = new TemplateData();  
			keyword4.setColor("#000000");  
			keyword4.setValue(time);  
			m.put("keyword4", keyword4);
			
			temp.setData(m);
		
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception e) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
    
    /**
     * 发送支付信息
     * @param openId 通知的用户openid
     * @param url 跳转的URL 为空的时候不跳转
     * */
    public static ResponseInfo sendPayMsg(String openId, String url) {
    	try {
			String appId = "wx3bfc1374f90fa33e";
			String appSecret = "c75abb80c2490741be5a9be58336f5c1";
			
			WxTemplate temp = new WxTemplate();
			temp.setUrl(url);
			temp.setTouser(openId);
			temp.setTopcolor("#000000");
			temp.setTemplate_id("3GsqIj9_xX1VkfL-frriFmZZ3IZkYRnI68Phx5yiDso");
			
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			
			TemplateData first = new TemplateData();
			first.setColor("#000000");  
			first.setValue("支付测试。");  
			m.put("first", first); 
			
			TemplateData keyword1 = new TemplateData();  
			keyword1.setColor("#FF0000");  
			
			keyword1.setValue("支付测试");  
			m.put("keyword1", keyword1);
			
			TemplateData keyword2 = new TemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
			m.put("keyword2", keyword2);
			
			temp.setData(m);
			
			sendTemplateMessage(appId, appSecret, openId, temp);
			return new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "成功");
		} catch (Exception e) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "失败");
		} 
    }
    
    public static void main(String[] args) {
//    	String appId = "wx3bfc1374f90fa33e";
//    	String appSecret = "c75abb80c2490741be5a9be58336f5c1";
//    	String openId = "oM4XKvswtrjcb9wwFUAvhaNcFJnY";
//    	
//    	WxTemplate temp = new WxTemplate();
//    	
//    	temp.setUrl("http://www.udpei.com/");
//        temp.setTouser(openId);
//        temp.setTopcolor("#000000");
//        temp.setTemplate_id("dNB7tUMWJRuiUiZrR1MkblmRoNweY6KPRi7eUUGK8VY");
//        
//        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
//        
//        TemplateData first = new TemplateData();
//        first.setColor("#000000");  
//        first.setValue("您好！您在优得配注册账号成功。");  
//        m.put("first", first);  
//        
//        TemplateData keyword1 = new TemplateData();  
//        keyword1.setColor("#000000");  
//        keyword1.setValue("林成民");  
//        m.put("keyword1", keyword1);
//        
//        TemplateData keyword2 = new TemplateData();  
//        keyword2.setColor("#000000");  
//        keyword2.setValue("123456");  
//        m.put("keyword2", keyword2);
//        
//        temp.setData(m);
//    	
//    	sendTemplateMessage(appId, appSecret, openId, temp);
    	
    	 try {
			String appid = "wx3bfc1374f90fa33e";  
			 String paternerKey = "c75abb80c2490741be5a9be58336f5c1";  
			 String nonceStr = PayCommonUtil.getRandomString(16);
			 String outTradeNo = PayCommonUtil.getOrderIdByUUId();
			
			 Map<String, String> paraMap = new LinkedHashMap<String, String>();  
			 paraMap.put("appid", appid);  												//公众账号ID
//         paraMap.put("attach", "测试");  											//附加数据
			 paraMap.put("body", "发布供应/求购次数（1）");  									//商品描述
			 paraMap.put("device_info", "20160524");  									//设备号
			 paraMap.put("mch_id", "1436871502"); 										//商户ID  
			 paraMap.put("nonce_str", nonceStr);  										//随机字符串
			 paraMap.put("notify_url", "http://2nd.udpei.com/weixin/test/pay/backpay.html");// 此路径是微信服务器调用支付结果通知路径  
			 paraMap.put("openid", "oM4XKvswtrjcb9wwFUAvhaNcFJnY");  											//用户标识
			 paraMap.put("out_trade_no", outTradeNo);  									//商户订单号
//         paraMap.put("sign_type", "MD5");											//签名类型
//         paraMap.put("spbill_create_ip", spbillCreateIp);  						//终端IP
			 paraMap.put("total_fee", "100");  				//标价金额
			 paraMap.put("trade_type", "JSAPI");  										//交易类型
			 String sign = PayCommonUtil.createSign("UTF-8",paraMap);  
			 paraMap.put("sign", sign);										//签名
			 System.out.println("--------sign----" + sign);
			 
			 String xml = PayCommonUtil.getRequestXml(paraMap);
			 String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";  
			 String xmlStr = CommonUtil.httpsRequest(url, "POST", xml);
			 System.out.println("--------xmlStr----" + xmlStr);
			 // 预付商品id  
			 String prepay_id = "";  

			 if (xmlStr.indexOf("SUCCESS") != -1) {  
			     Map<String, String> map = XMLUtil.doXMLParse(xmlStr);  
			     prepay_id = (String) map.get("prepay_id");  
			 }  
			 System.out.println("--------prepay_id----" + prepay_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}
