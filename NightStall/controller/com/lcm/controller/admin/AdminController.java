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
import com.google.zxing.common.BitMatrix;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Admin;
import com.lcm.entity.pojo.Role;
import com.lcm.service.business.AdminService;
import com.lcm.service.business.RoleService;
import com.lcm.utils.file.MatrixToImageWriter;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/admin/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Admin> list = adminService.getAdminList(param);
		PageResponse response = new PageResponse(adminService.getAdminCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Role> roles = roleService.getRoleList();
		model.addAttribute("roles", roles);
		return ADMIN_PG_PREFIX + "/admin/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Admin admin) {
		ResponseInfo responseInfo = adminService.add(admin);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Admin entity = adminService.getAdminDetail(id);
		model.addAttribute("entity", entity);
		List<Role> roles = roleService.getRoleList();
		model.addAttribute("roles", roles);
		return ADMIN_PG_PREFIX + "/admin/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Admin admin) {
		ResponseInfo responseInfo = adminService.update(admin);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = adminService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = adminService.deleteMul(ids);
		return responseInfo;
	}
	
	@RequestMapping("/generate/ewm")
	public String generateEWM(HttpServletRequest request,Model model, @RequestParam(required=false) Long id) {
		if(StringUtils.isEmpty(id)) {
			id = getSessionAdmin().getId();
		}
		
		Admin entity = adminService.getAdminDetail(id);
//		if(StringUtils.isEmpty(entity.getEwm())) { //为空的话进行二维码生成
			try {
				String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3bfc1374f90fa33e&redirect_uri=http%3a%2f%2f2nd.udpei.com%2fmobile%2fregister%2f" + id + ".html&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//				String path = "D:/upload";
				String path = "upload/";
				String ewm = "ewm_" + entity.getId() + ".jpg";
				
				File file1 = new File(path, ewm);
//				if(!file1.exists()) {
					MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
					Map hints = new HashMap();
					hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
					BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
					MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
//				}
				
				entity.setEwm(ewm);
				adminService.update(entity);
				
			} catch (Exception e) {
			     e.printStackTrace();
			 }
//		}
		
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/admin/ewm";
	}
	
}
