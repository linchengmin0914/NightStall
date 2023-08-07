package com.lcm.utils.dada;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SendQueryDeliverFee {
	//查询运费
	private static String URL = "/api/order/queryDeliverFee";
	
	public static JSONObject sendRequest(QueryDeliverFee entity, Map<String,String> ddparams) {
		String body = JSON.toJSONString(entity);
		System.out.println("==body====" + body);
    	Map<String, String> params = DaDaUtils.generateParams(body,ddparams);
    	System.out.println("======222=====" + (ddparams.get("url") + URL));
    	JSONObject result = DaDaUtils.doPost(ddparams.get("url") + URL, params);
		return result;
	}
	
	public static void main(String[] args) {
		Map<String,String> ddparams = new HashMap<String, String>();
		ddparams.put("ddappkey", "dada1df2dad7cf6ea92");
		ddparams.put("ddappsercret", "407533338b8f76427df29be58078c5c3");
		ddparams.put("url", "https://newopen.qa.imdada.cn");
		ddparams.put("sourceId", "1694793462");
		
		QueryDeliverFee entity = new QueryDeliverFee();
		entity.setOrigin_id("003");
		entity.setCargo_price(100.00);
		entity.setReceiver_name("张三");
		entity.setReceiver_address("岑东路154号(中国农业银行(厦门集源支行))");
		entity.setReceiver_lat(24.574373);
		entity.setReceiver_lng(118.097899);
		entity.setCallback("xmyj.yifan668.com");
		entity.setCargo_weight(1.00);
		entity.setReceiver_phone("15259269857");
		entity.setInfo("测试备注");
		JSONObject result = SendQueryDeliverFee.sendRequest(entity,ddparams);
		System.out.println("===result====" + result.toJSONString());
		QueryDeliverFeeResult queryDeliverFeeResult = JSON.parseObject(result.toJSONString(), QueryDeliverFeeResult.class);
		System.out.println("===queryDeliverFeeResult====" + queryDeliverFeeResult.getResult().getDeliveryNo());
	}
}
