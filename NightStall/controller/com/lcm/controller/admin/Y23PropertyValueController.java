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
import com.lcm.entity.pojo.Y23PropertyName;
import com.lcm.entity.pojo.Y23PropertyValue;
import com.lcm.service.business.Y23PropertyNameService;
import com.lcm.service.business.Y23PropertyValueService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23PropertyValueController")
@RequestMapping("/admin/01/goods/propertyvalue")
public class Y23PropertyValueController extends BaseController {
	@Autowired
	private Y23PropertyValueService y23PropertyValueService;
	@Autowired
	private Y23PropertyNameService y23PropertyNameService;
	
	private String path = "01/propertyvalue";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model, @RequestParam Long propNameId) {
		model.addAttribute("propNameId", propNameId);
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23PropertyValue> list = y23PropertyValueService.getList(param);
		PageResponse response = new PageResponse(y23PropertyValueService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model, @RequestParam Long propNameId) {
		Y23PropertyName y23PropertyName = y23PropertyNameService.getDetail(propNameId);
		System.out.println("======propNameId=======" + y23PropertyName.getTitle());
		model.addAttribute("y23PropertyName", y23PropertyName);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23PropertyValue entity) {
		ResponseInfo responseInfo = y23PropertyValueService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23PropertyValue entity = y23PropertyValueService.getDetail(id);
		model.addAttribute("entity", entity);
		
		Y23PropertyName y23PropertyName = y23PropertyNameService.getDetail(entity.getPropNameId());
		model.addAttribute("y23PropertyName", y23PropertyName);
		
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23PropertyValue entity) {
		ResponseInfo responseInfo = y23PropertyValueService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23PropertyValueService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23PropertyValueService.deleteMul(ids);
		return responseInfo;
	}
	
}
