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
import com.lcm.entity.pojo.Y23DadaStore;
import com.lcm.entity.pojo.Y23Store;
import com.lcm.service.business.Y23DadaStoreService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23DadaStore")
@RequestMapping("/admin/01/order/ddstore")
public class Y23DadaStoreController extends BaseController {
	@Autowired
	private Y23DadaStoreService y23DadaStoreService;
	@Autowired
	private Y23StoreService y23StoreService;
	
	private String path = "01/ddstore";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23DadaStore> list = y23DadaStoreService.getList(param);
		PageResponse response = new PageResponse(y23DadaStoreService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23DadaStore entity) {
		ResponseInfo responseInfo = y23DadaStoreService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		
		Y23DadaStore entity = y23DadaStoreService.getDetail(id);
		model.addAttribute("entity", entity);
		
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23DadaStore entity) {
		ResponseInfo responseInfo = y23DadaStoreService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23DadaStoreService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23DadaStoreService.deleteMul(ids);
		return responseInfo;
	}
	
}
