package com.lcm.controller.admin;

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
import com.lcm.entity.pojo.Y23User;
import com.lcm.service.business.Y23UserService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23UserController")
@RequestMapping("/admin/01/user")
public class Y23UserController extends BaseController {
	@Autowired
	private Y23UserService y23UserService;
	
	private String path = "01/user";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23User> list = y23UserService.getList(param);
		PageResponse response = new PageResponse(y23UserService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	@RequestMapping("/fxindex")
	public String fxindex(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/fxindex";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23User entity) {
		ResponseInfo responseInfo = y23UserService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23User entity = y23UserService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23User entity) {
		ResponseInfo responseInfo = y23UserService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/fxupdate/done")
	public ResponseInfo fxupdateDone(HttpServletRequest request,Model model, @ModelAttribute Y23User entity) {
		ResponseInfo responseInfo = y23UserService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23UserService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23UserService.deleteMul(ids);
		return responseInfo;
	}
	
}
