package com.lcm.controller.base;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lcm.entity.pojo.Admin;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.service.business.Y23SystemParamService;
import com.lcm.utils.file.HttpClientUtil;

public class BaseController {
	public static final String SESSION_ADMIN = "sessionAdmin";
	public static final String SESSION_USER = "sessionUser";
	protected static final String ADMIN_PG_PREFIX = "admin/page/";
	protected static final String MOBILE_ADMIN_PG_PREFIX = "mobile/admin/";
	protected static final String WEB_PREFIX = "web/";
	
	@Autowired
	private Y23SystemParamService y23SystemParamService;
	
	public Map<String,String> getDaDaParams() {
		Map<String,String> param = new HashMap<String, String>();
		Y23SystemParam ddappkey = y23SystemParamService.getY23SystemParamDetail("ddappkey");
		param.put("ddappkey", ddappkey.getValue());
		Y23SystemParam ddappsercret = y23SystemParamService.getY23SystemParamDetail("ddappsercret");
		param.put("ddappsercret", ddappsercret.getValue());
		
		Y23SystemParam useType = y23SystemParamService.getY23SystemParamDetail("useType");
		if(useType.getValue().equals("1")) {
			Y23SystemParam ddzsurl = y23SystemParamService.getY23SystemParamDetail("ddzsurl");
			Y23SystemParam sourceId = y23SystemParamService.getY23SystemParamDetail("sourceId");
			param.put("url", ddzsurl.getValue());
			param.put("sourceId", sourceId.getValue());
		} else {
			Y23SystemParam ddtesturl = y23SystemParamService.getY23SystemParamDetail("ddtesturl");
			Y23SystemParam testSourceId = y23SystemParamService.getY23SystemParamDetail("testSourceId");
			param.put("url", ddtesturl.getValue());
			param.put("sourceId", testSourceId.getValue());
		}
		
		return param;
	}
	
	public static void getAllFileName(String path,ArrayList<String> fileName){
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null)
        fileName.addAll(Arrays.asList(names));
        for(File a:files)
        {
            if(a.isDirectory())
            {
                getAllFileName(a.getAbsolutePath(),fileName);
            }
        }
    }
	
	public Admin getSessionAdmin() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		if(request.getSession().getAttribute(SESSION_ADMIN) != null) {
			return (Admin)request.getSession().getAttribute(SESSION_ADMIN);
		}
		return null;
	}
	
	
	public void setAttrToSession(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		request.getSession().setAttribute(key, value);
	}
	
	public Object getAttrFromSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		if(request.getSession().getAttribute(key) != null) {
			return request.getSession().getAttribute(key);
		}
		return null;
	}
	
	public void removeSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		request.getSession().removeAttribute(key);
	}
	
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		
		if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		
		return request.getRemoteAddr();
	}
}
