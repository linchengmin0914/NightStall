package com.lcm.controller.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Right;
import com.lcm.entity.pojo.Role;
import com.lcm.entity.pojo.RoleRight;
import com.lcm.service.business.RightService;
import com.lcm.service.business.RoleRightService;
import com.lcm.service.business.RoleService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RightService rightService;
	@Autowired
	private RoleRightService roleRightService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/role/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Role> list = roleService.getRoleList(param);
		PageResponse response = new PageResponse(roleService.getRoleCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Right> rights = rightService.getRightList();
		
		Map<Right, List<Right>> map = new LinkedHashMap<Right, List<Right>>();
		for(Right right:rights) {
			if(right.getParentId() == null) {
				map.put(right, new ArrayList<Right>());
			}
		}
		
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
		
		model.addAttribute("rightMap", map);
		return ADMIN_PG_PREFIX + "/role/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Role role, 
			@RequestParam(required = false) String rightIds) {
		ResponseInfo responseInfo = roleService.add(role, rightIds);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		List<Right> rights = rightService.getRightList();
		
		Map<Right, List<Right>> map = new LinkedHashMap<Right, List<Right>>();
		for(Right right:rights) {
			if(right.getParentId() == null) {
				map.put(right, new ArrayList<Right>());
			}
		}
		
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
		
		model.addAttribute("rightMap", map);
		
		Map param = new LinkedHashMap();
		param.put("roleId", id);
		List<RoleRight> roleRights = roleRightService.getRoleRightList(param);
		List<Long> rightIds = new ArrayList<Long>();
		for (int i = 0; i < roleRights.size(); i++) {
			rightIds.add(roleRights.get(i).getRightId());
		}
		model.addAttribute("rightIds", rightIds);
//		System.out.println("=======roleRights=====" + roleRights.size());
//		System.out.println("=======rightIds=====" + rightIds);
		Role entity = roleService.getRoleDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/role/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Role role,
			@RequestParam(required = false) String rightIds) {
		ResponseInfo responseInfo = roleService.update(role, rightIds);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = roleService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = roleService.deleteMul(ids);
		return responseInfo;
	}
}
