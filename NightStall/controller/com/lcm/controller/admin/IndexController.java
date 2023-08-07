package com.lcm.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.service.business.AdminService;
import com.lcm.utils.file.StringUtil;
import com.lcm.utils.spring.SpringContextUtil;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController extends BaseController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		if(getSessionAdmin() == null) {
			return ADMIN_PG_PREFIX + "/login";
		}
		
		return ADMIN_PG_PREFIX + "/index";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/welcome";
	}
	
	
	@RequestMapping("/showpicture")
	public String showpicture(HttpServletRequest request,Model model, @RequestParam String url) {
		model.addAttribute("url", url);
		return ADMIN_PG_PREFIX + "/include/show_picture";
	}
}
