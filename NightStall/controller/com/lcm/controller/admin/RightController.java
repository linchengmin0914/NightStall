package com.lcm.controller.admin;

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
import com.lcm.service.business.RightService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller
@RequestMapping("/admin/right")
public class RightController extends BaseController {
	@Autowired
	private RightService rightService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		List<Right> parents = getParentRightList();
		model.addAttribute("parents", parents);
		return ADMIN_PG_PREFIX + "/right/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Right> list = rightService.getRightList(param);
		PageResponse response = new PageResponse(rightService.getRightCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Right> parents = getParentRightList();
		model.addAttribute("parents", parents);
		return ADMIN_PG_PREFIX + "/right/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Right right) {
		ResponseInfo responseInfo = rightService.add(right);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Right entity = rightService.getRightDetail(id);
		model.addAttribute("entity", entity);
		List<Right> parents = getParentRightList();
		model.addAttribute("parents", parents);
		return ADMIN_PG_PREFIX + "/right/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Right right) {
		ResponseInfo responseInfo = rightService.update(right);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = rightService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = rightService.deleteMul(ids);
		return responseInfo;
	}
	
	private List<Right> getParentRightList() {
		Map param = new LinkedHashMap();
		param.put("parentId", "parentId");
		List<Right> parents = rightService.getRightList(param);
		return parents;
	}
}
