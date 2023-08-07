package com.lcm.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.service.business.Y23UserAddressService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.Y23UserAddressDetailResponse;
import com.lcm.utils.response.Y23UserAddressListResponse;
import com.lcm.utils.search.SearchParam;

@Controller(value="bY23UserAddressController")
@RequestMapping("/y23/api/user/address")
public class Y23UserAddressController extends BaseController  {
	@Autowired
	private Y23UserAddressService y23UserAddressService;
	
	@ResponseBody
	@RequestMapping("/save")
	public ResponseInfo save(HttpServletRequest request,Model model,@ModelAttribute Y23UserAddress entity, 
			@RequestParam Long userId) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "SUCCESS");
		
		if(entity.getIsDefault()) {
			Map param = new HashMap();
			param.put("userId", userId);
			param.put("isDefault", true);
			List<Y23UserAddress> list = y23UserAddressService.getList(param);
			for (int i = 0; i < list.size(); i++) {
				Y23UserAddress address = list.get(i);
				address.setIsDefault(false);
				y23UserAddressService.update(address);
			}
		}
		
		if(StringUtils.isEmpty(entity.getId())) {
			entity.setUserId(userId);
			responseInfo = y23UserAddressService.add(entity);
			
		} else {
			responseInfo = y23UserAddressService.update(entity);
		}
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/detail/{id}")
	public ResponseInfo detail(HttpServletRequest request,Model model,@PathVariable Long id, 
			@RequestParam Long userId) {
		Y23UserAddress entity = y23UserAddressService.getDetail(id);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		Y23UserAddressDetailResponse responseInfo = new Y23UserAddressDetailResponse(ResCode.SUCCESS, "SUCCESS", entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Map param = searchParam.getSearchMap();
		param.put("userId", userId);
		param.put("orderBy", " a.isDefault desc,a.create_time desc");
		List<Y23UserAddress> list = y23UserAddressService.getList(param);
		
		Y23UserAddressListResponse responseInfo = new Y23UserAddressListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public ResponseInfo delete(HttpServletRequest request,Model model,@PathVariable Long id, 
			@RequestParam Long userId) {
		Y23UserAddress entity = y23UserAddressService.getDetail(id);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		entity.setIsDelete(true);
		ResponseInfo responseInfo = y23UserAddressService.update(entity);
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/setdefault/{id}")
	public ResponseInfo setDefault(HttpServletRequest request,Model model,@PathVariable Long id, 
			@RequestParam Long userId) {
		Y23UserAddress entity = y23UserAddressService.getDetail(id);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("orderBy", " a.isDefault desc,a.create_time desc");
		List<Y23UserAddress> list = y23UserAddressService.getList(param);
		for (Y23UserAddress address:list) {
			if(address.getIsDefault()) {
				address.setIsDefault(false);
				y23UserAddressService.update(address);
				break;
			}
		}
		
		entity.setIsDefault(true);
		ResponseInfo responseInfo = y23UserAddressService.update(entity);
		
		return responseInfo;
	}
}
