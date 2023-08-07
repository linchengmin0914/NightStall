package com.lcm.utils.dada;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SendAddAfterQuery {
	//查询运费后保价下单
	private static String URL = "/api/order/addAfterQuery";
	
	public static JSONObject sendRequest(AddAfterQuery entity, Map<String,String> ddparams) {
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
		AddAfterQuery entity = new AddAfterQuery();
		entity.setDeliveryNo("Dada142f6e72ac2d443fa55d6a95aafbb378");
		JSONObject result = SendAddAfterQuery.sendRequest(entity,ddparams);
		System.out.println("===result====" + result.toJSONString());
		AddAfterQueryResult addAfterQueryResult = JSON.parseObject(result.toJSONString(), AddAfterQueryResult.class);
		System.out.println("===addAfterQueryResult====" + addAfterQueryResult.getMsg());
	}
}
