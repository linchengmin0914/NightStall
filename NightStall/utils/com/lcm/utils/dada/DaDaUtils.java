package com.lcm.utils.dada;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.jpay.ext.kit.PaymentKit;
import com.lcm.utils.file.HttpClientUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DaDaUtils {
	private static Log log = LogFactory.getLog(DaDaUtils.class); 
	
	 /**
     * 构建真正的api接口参数
     *
     * @param body 接口的body参数内容
     * @return 构建真正的api接口参数
     */
    public static Map<String, String> generateParams(String body, Map<String,String> ddparams) {
    	String appkey = ddparams.get("ddappkey");
        String appSecret = ddparams.get("ddappsercret");
        String sourceId = ddparams.get("sourceId");
        String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
        Map<String, String> data = new HashMap<String, String>();
        data.put("source_id", sourceId);
        data.put("app_key", appkey);
        data.put("timestamp", timestamp);
        data.put("format", "json");
        data.put("v", "1.0");
        data.put("body", body);
        String signStr = "app_key" + appkey + "body" + body + "format" +  "json" + "source_id" + sourceId
        					+ "timestamp" + timestamp + "v1.0";
        String finalSignStr = appSecret + signStr + appSecret;
        finalSignStr = DigestUtils.md5Hex(finalSignStr).toUpperCase();
        data.put("signature", finalSignStr);
        return data;
    }
    
    public static JSONObject doPost(String url, Map<String, String> params) { 
    	String json = JSON.toJSONString(params);
    	JSONObject param = JSON.parseObject(json);
    	
    	//定义接收数据
        JSONObject result = new JSONObject();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        //请求参数转JOSN字符串
        StringEntity entity = new StringEntity(param.toJSONString(), "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try {
            HttpResponse response = client.execute(httpPost);
            System.out.println("======response=====" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                result = JSON.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "连接错误！");
        }
        return result;
      
    } 
    
    public static void main(String[] args) {
//    	String body = "{\"callback\": \"www.example.com/example\",\"cargo_price\": 100,\"cargo_weight\": 1,\"city_code\": \"021\",\"is_prepay\": 0,\"origin_id\": \"orderNo-1694793462-1668435655167\",\"receiver_address\": \"东方渔人码头\",\"receiver_lat\": 31.257801,\"receiver_lng\": 121.538842,\"receiver_name\": \"李四\",\"receiver_phone\": \"13011112222\",\"shop_no\": \"16280040e76a48dc\"}";
//    	
//    	Map<String, String> params = generateParams(body);
//    	JSONObject result = doPost(TESTURL + addOrder, params);
//		System.out.println("=======" + result.toJSONString());
	}
}
