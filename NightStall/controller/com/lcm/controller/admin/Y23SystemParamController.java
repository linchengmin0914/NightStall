package com.lcm.controller.admin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.ParamBean;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.service.business.Y23SystemParamService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.task.TaskListener;

@Controller("y23SystemParamControllerBG")
@RequestMapping("/admin/y23/system")
public class Y23SystemParamController extends BaseController {
	@Autowired
	private Y23SystemParamService y23SystemParamService;
	
	@RequestMapping("/param")
	public String param(HttpServletRequest request,Model model) {
		Map<String, Y23SystemParam> paramMap = y23SystemParamService.getMap();
		model.addAttribute("paramMap", paramMap);
		return ADMIN_PG_PREFIX + "/01/y23systemparam/index";
	}
	
	@RequestMapping("/fxparam")
	public String fxparam(HttpServletRequest request,Model model) {
		Map<String, Y23SystemParam> paramMap = y23SystemParamService.getMap();
		model.addAttribute("paramMap", paramMap);
		return ADMIN_PG_PREFIX + "/01/y23systemparam/fxparam";
	}
	
	@RequestMapping("/yqparam")
	public String yqparam(HttpServletRequest request,Model model) {
		Map<String, Y23SystemParam> paramMap = y23SystemParamService.getMap();
		model.addAttribute("paramMap", paramMap);
		return ADMIN_PG_PREFIX + "/01/y23systemparam/yqparam";
	}
	
	@RequestMapping("/dadaparam")
	public String dadaparam(HttpServletRequest request,Model model) {
		Map<String, Y23SystemParam> paramMap = y23SystemParamService.getMap();
		model.addAttribute("paramMap", paramMap);
		return ADMIN_PG_PREFIX + "/01/y23systemparam/dadaparam";
	}
	
	@ResponseBody
	@RequestMapping("/param/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute ParamBean bean) {
		ResponseInfo responseInfo = new ResponseInfo();
		
	    try {
			Field[] field = bean.getClass().getDeclaredFields();   
			
			for(int j=0 ; j<field.length ; j++){     //遍历所有属性
				String name = field[j].getName();    //获取属性的名字
				name = name.substring(0,1).toUpperCase()+name.substring(1); 
				Method m = bean.getClass().getMethod("get"+name);
				Y23SystemParam systemParam = (Y23SystemParam)m.invoke(bean);
				if(systemParam != null) {
					y23SystemParamService.update(systemParam);
				}
				
			}
			TaskListener.paramMap23 = y23SystemParamService.getMap();
			responseInfo = new ResponseInfo(1, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo = new ResponseInfo(0, "修改失败");
		} 
		
		return responseInfo;
	}
}
