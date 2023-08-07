package com.lcm.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.UserRight;
import com.lcm.entity.pojo.Role;
import com.lcm.service.business.UserRightService;
import com.lcm.service.business.RoleService;
import com.lcm.utils.file.MatrixToImageWriter;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller
@RequestMapping("/admin/userRight")
public class UserRightController extends BaseController {
	@Autowired
	private UserRightService userRightService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model,@RequestParam Long userId) {
		model.addAttribute("userId", userId);
		return ADMIN_PG_PREFIX + "/userRight/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<UserRight> list = userRightService.getUserRightList(param);
		PageResponse response = new PageResponse(userRightService.getUserRightCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/userRight/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute UserRight userRight) {
		ResponseInfo responseInfo = userRightService.add(userRight);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		UserRight entity = userRightService.getUserRightDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/userRight/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute UserRight userRight) {
		ResponseInfo responseInfo = userRightService.update(userRight);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = userRightService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = userRightService.deleteMul(ids);
		return responseInfo;
	}
	
	
	
	@RequestMapping("/yj/index")
	public String yjindex(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/userRight/yjindex";
	}
	
	@ResponseBody
	@RequestMapping("/yj/list")
	public PageResponse yjlist(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		param.put("orderBy", " userId asc");
		List<UserRight> list = userRightService.getUserRightList(param);
		PageResponse response = new PageResponse(userRightService.getUserRightCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
}
