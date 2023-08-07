package com.lcm.utils.dada;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class QueryOrder {
	//查询订单详情
	private static String URL = "/api/order/status/query";
	
	public static JSONObject sendRequest(QueryOrderRequest entity, Map<String,String> ddparams) {
		String body = JSON.toJSONString(entity);
		System.out.println("==body====" + body);
    	Map<String, String> params = DaDaUtils.generateParams(body,ddparams);
    	JSONObject result = DaDaUtils.doPost(ddparams.get("url") + URL, params);
		return result;
	}
	
	public static void main(String[] args) {
		Map<String,String> ddparams = new HashMap<String, String>();
		ddparams.put("ddappkey", "dada1df2dad7cf6ea92");
		ddparams.put("ddappsercret", "407533338b8f76427df29be58078c5c3");
		ddparams.put("url", "https://newopen.qa.imdada.cn");
		ddparams.put("sourceId", "1694793462");
		QueryOrderRequest entity = new QueryOrderRequest();
		entity.setOrder_id("YFJX20221115222217003");
		JSONObject result = QueryOrder.sendRequest(entity,ddparams);
		System.out.println("===result====" + result.toJSONString());
		OrderDetailResult orderDetailResult = JSON.parseObject(result.toJSONString(), OrderDetailResult.class);
		System.out.println("===OrderDetailResult====" + orderDetailResult.getResult().getTransporterName());
	}
}
