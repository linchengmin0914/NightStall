package com.lcm.utils.file;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 处理异步JSON的基本数据处理工具类<br/>
 * author:linchengmin
 * date:2013-6-21 14:23:01
 * */

public class JsonUtil {
	
    /**
     * json数据类型输出处理<br/>
     * date:2013-6-21 14:31:58<br/>
     * @param json 数据的json字符串
     * */
    public static void handleJSON(HttpServletResponse response, String json) {
    	response.setContentType("application/json;charset=utf-8");
    	response.setHeader("Cache-Control","no-cache");
    	
    	PrintWriter pw;
    	
    	try {
    		pw = response.getWriter();
    		pw.print(json);
    		pw.flush();
    		pw.close();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    /**
     * 根据传入的Map,构造数据操作提示的json数据<br/>
     * 输出标识为flag<br/>
     * date:2013-6-21 14:33:04<br/>
     * @param map 操作返回值
     * @return String 输出的json信息
     * */
    public static String getJsonContent(Map<Boolean,String> map) {
    	//构造JSON内容
    	String json = "";
    	
    	if (map.containsKey(true)) {
    		json = "[{\"respCode\":0,\"respDesc\":\""+map.get(true)+"\"}]";

    	} else {
    		json = "[{\"respCode\":1,\"respDesc\":\""+map.get(false)+"\"}]";
    	}
    	
    	return json;
    }
    
    /**
     * 根据传入的Map,构造数据操作提示的json数据<br/>
     * 输出标识为flag<br/>
     * date:2014-4-16 9:41:41<br/>
     * @param content 要转化的内容
     * @return String 输出的json信息
     * */
    public static String getJsonContent(String content) {
    	//构造JSON内容
    	String json = "";
    	
    	json = "[{\"flag\":\"" + content + "\"}]";
    	
    	return json;
    }

    /**
     * 根据传入的Map,构造数据操作提示的json数据<br/>
     * 输入标识的个数应该与输出标识的值相同<br/>
     * date:2013-6-21 14:35:20<br/>
     * @param names 输出标识列表
     * @param values 输出表示对于的值列表
     * @return String 输出的json信息
     * */
    public static String getJsonContent(List<List<String>> names,List<List<Object>> values) {
    	//构造JSON内容
    	String json = "[";

    	int nameSize = names.size();
    	int valueSize = values.size();
    	
    	if (nameSize == valueSize) {
    		
    		for (int i = 0; i < nameSize; i++) {
    			int num = names.get(i).size();
    			
    			json += "{";
    			
    			for (int j = 0; j < num; j++) {
    				//modify by liuyanzhi on 20131015
    				json += "\"" +names.get(i).get(j) + "\":\"" + values.get(i).get(j).toString().replaceAll("\"", "”") + "\"";
    				
    				if (num-1 > j) {
    					json += ",";
    				}
    				
    			}
    			
    			json += "}";
    			
    			if (nameSize-1 > i) {
    				json += ",";
    			}
    			
    		}
    		
    		json += "]";
    		
    	} else {
    		json += "]";
    	}
    	
    	return json;
    }
    
    /**
     * 转换entity为json
     * @param object 转换的对象
     */
    public static String tranJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
