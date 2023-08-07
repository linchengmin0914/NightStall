package com.lcm.controller.admin;

import java.util.HashMap;
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
import com.lcm.entity.pojo.Y23Store;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23CategoriesController")
@RequestMapping("/admin/01/goods/categories")
public class Y23CategoriesController extends BaseController {
	@Autowired
	private Y23CategoriesService y23CategoriesService;
	@Autowired
	private Y23StoreService y23StoreService;
	
	private String path = "01/categories";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		getParentList(model);
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		if(param.get("orderBy").toString().indexOf("cateName") > -1) {
			param.put("orderBy", param.get("orderBy").toString().replace("cateName", "cate_name"));
		}
		List<Y23Categories> list = y23CategoriesService.getList(param);
		PageResponse response = new PageResponse(y23CategoriesService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		getParentList(model);
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23Categories entity) {
		ResponseInfo responseInfo = y23CategoriesService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23Categories entity = y23CategoriesService.getDetail(id);
		model.addAttribute("entity", entity);
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		getParentList(model);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23Categories entity) {
		ResponseInfo responseInfo = y23CategoriesService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23CategoriesService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23CategoriesService.deleteMul(ids);
		return responseInfo;
	}
	
	private void getParentList(Model model) {
		Map param = new HashMap();
		param.put("pid", 0);
		List<Y23Categories> parentList = y23CategoriesService.getList(param);
		model.addAttribute("parentList", parentList);
	}
	
}
