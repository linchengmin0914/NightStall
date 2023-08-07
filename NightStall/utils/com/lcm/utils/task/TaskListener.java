package com.lcm.utils.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.service.business.Y23SystemParamService;

@Component
public class TaskListener implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger log = LoggerFactory.getLogger(TaskListener.class);  
	public static Map<String ,Y23SystemParam> paramMap23 = new LinkedHashMap<String ,Y23SystemParam>();	
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
        	log.info("系统正在进行初始化操作，请稍等...");
        	
        	//系统参数
        	Y23SystemParamService y23SystemParamService  = (Y23SystemParamService)event.getApplicationContext().getBean("y23SystemParamService");
        	loadParamMap23(y23SystemParamService);
        	
        	log.info("系统已完成初始化操作.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 
    public void loadParamMap23(Y23SystemParamService y23SystemParamService) {
    	paramMap23 = y23SystemParamService.getMap();
    }
}
