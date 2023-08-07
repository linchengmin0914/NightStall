package com.lcm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Admin;
import com.lcm.entity.pojo.Right;
import com.lcm.entity.pojo.RoleRight;
import com.lcm.service.business.AdminService;
import com.lcm.service.business.RightService;
import com.lcm.service.business.RoleRightService;
import com.lcm.utils.response.ResponseInfo;

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private RightService rightService;
	@Autowired
	private RoleRightService roleRightService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
		request.getSession().removeAttribute(SESSION_ADMIN);
		return ADMIN_PG_PREFIX + "/login";
	}
	
	@ResponseBody
	@RequestMapping("/login/done")
	public ResponseInfo loginDone(HttpServletRequest request,Model model, @RequestParam String nickname,
			@RequestParam String passwd) {
		Admin admin = adminService.getAdminDetail(nickname);
		if(admin == null) {
			return new ResponseInfo(0, "该账户不存在!!!");
		}
		
		if(!passwd.equals(admin.getPasswd())) {
			return new ResponseInfo(0, "密码错误!!!");
		}
		
		request.getSession().setAttribute(SESSION_ADMIN, admin);
		initRight(admin, request); 
		
		return new ResponseInfo(1, "登录成功!!!");
	}
	
	private void initRight(Admin admin, HttpServletRequest request) {
		List<Right> rights = new ArrayList<Right>();
		
		if(admin.getNickname().equals("admin")) {
			rights = rightService.getRightList();
			
			request.getSession().setAttribute("isShowLY", true);
		} else {
			if(admin.getRoleId() != null) {
				List<Right> list = rightService.getRightList();
				Map param = new LinkedHashMap();
				param.put("roleId", admin.getRoleId());
				List<RoleRight> roleRights = roleRightService.getRoleRightList(param);
				
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < roleRights.size(); j++) {
						if(roleRights.get(j).getRightId().longValue() == list.get(i).getId()) {
							rights.add(list.get(i));
							break;
						}
					}
				}
				
			}
		}
		
		boolean isShowLY = false;
		Map<Right, List<Right>> map = new LinkedHashMap<Right, List<Right>>();
		for(Right right:rights) {
			if("admin/liuYan/index.do".equals(right.getUrl())) {
				isShowLY = true;
			}
			
			if(right.getParentId() == null) {
				map.put(right, new ArrayList<Right>());
			}
		}
		
		request.getSession().setAttribute("isShowLY", isShowLY);
		
		for (Map.Entry<Right, List<Right>> entry : map.entrySet()) {  
			List<Right> list = entry.getValue();
			for(Right right:rights) {
				if(right.getParentId() != null) {
					if(right.getParentId().longValue() == entry.getKey().getId().longValue()) {
						list.add(right);
					}
				}
			}
			map.put(entry.getKey(), list);
		  
		}  
		
		setAttrToSession("rightMap", map);
	}
}
