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
import com.lcm.entity.pojo.Y23Categories;
import com.lcm.entity.pojo.Y23PropertyName;
import com.lcm.entity.pojo.Y23PropertyName;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23PropertyNameService;
import com.lcm.service.business.Y23PropertyNameService;
import com.lcm.service.business.Y23PropertyValueService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23PropertyNameController")
@RequestMapping("/admin/01/goods/propertyname")
public class Y23PropertyNameController extends BaseController {
	@Autowired
	private Y23PropertyNameService y23PropertyNameService;
	@Autowired
	private Y23CategoriesService y23CategoriesService;
	
	private String path = "01/propertyname";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23PropertyName> list = y23PropertyNameService.getList(param);
		PageResponse response = new PageResponse(y23PropertyNameService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Y23Categories> cateList = y23CategoriesService.getList();
		model.addAttribute("cateList", cateList);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23PropertyName entity) {
		ResponseInfo responseInfo = y23PropertyNameService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23PropertyName entity = y23PropertyNameService.getDetail(id);
		model.addAttribute("entity", entity);
		
		List<Y23Categories> cateList = y23CategoriesService.getList();
		model.addAttribute("cateList", cateList);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23PropertyName entity) {
		ResponseInfo responseInfo = y23PropertyNameService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23PropertyNameService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23PropertyNameService.deleteMul(ids);
		return responseInfo;
	}
	
}
