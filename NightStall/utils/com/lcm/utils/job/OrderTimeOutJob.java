package com.lcm.utils.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lcm.utils.spring.SpringContextUtil;

@Component
@Configurable
@EnableScheduling
public class OrderTimeOutJob {
private final Logger log = LoggerFactory.getLogger(OrderTimeOutJob.class);  
	
	@Scheduled(fixedRate = 1000 * 60 * 60)
    public void run(){
		log.info("正在进行价格开放状态更新....");
		
//		Y21SystemParamService y21SystemParamService = (Y21SystemParamService)SpringContextUtil.getBean("y21SystemParamService");
//		Y21SystemParam kfjg = y21SystemParamService.getY21SystemParamDetail("kfjg");
//		
//		if(kfjg.getValue().equals("1")) { 
//			Y21SystemParam orderEnd = y21SystemParamService.getY21SystemParamDetail("orderEnd");
//			Date nowDate = new Date();
//			String date= new SimpleDateFormat("yyyyMMddHHmm").format(nowDate);
//			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
//			String endDate = sd.format(nowDate) + orderEnd.getValue().replace(":", "");
//			if(Long.parseLong(endDate) < Long.parseLong(date)){
//				kfjg.setValue("0");
//				y21SystemParamService.update(kfjg);
//				
//				Y21GoodsService y21GoodsService = (Y21GoodsService)SpringContextUtil.getBean("y21GoodsService");
//				List<Y21Goods> list = y21GoodsService.getList();
//				for (Y21Goods y21Goods:list) {
//					if(y21Goods.getIsIndex()) {
//						y21Goods.setIsIndex(false);
//						y21GoodsService.update(y21Goods);
//					}
//				}
//			}
//			
//		}
		
    }
	
}
