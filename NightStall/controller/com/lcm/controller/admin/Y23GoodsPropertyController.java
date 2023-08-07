package com.lcm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23PropertyName;
import com.lcm.entity.pojo.Y23PropertyValue;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23PropertyNameService;
import com.lcm.service.business.Y23PropertyValueService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23GoodsPropertyController")
@RequestMapping("/admin/01/goods/property")
public class Y23GoodsPropertyController extends BaseController {
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	@Autowired
	private Y23PropertyNameService y23PropertyNameService;
	@Autowired
	private Y23PropertyValueService y23PropertyValueService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	
	private String path = "01/goods-property";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model, @RequestParam Long goodsId) {
		model.addAttribute("goodsId", goodsId);
		Y23Goods y23Goods = y23GoodsService.getDetail(goodsId);
		model.addAttribute("y23Goods", y23Goods);
		
		Map param2 = new HashMap();
		param2.put("goodsId", goodsId);
		List<Y23GoodsProperty> y23GoodsProperties = y23GoodsPropertyService.getList(param2);
		
		Map<Y23PropertyName, List<Y23PropertyValue>> map = new LinkedHashMap<Y23PropertyName, List<Y23PropertyValue>>();
		List<Y23PropertyName> nameList = y23PropertyNameService.getList();
		for (Y23PropertyName propertyName:nameList) {
			Map param = new HashMap();
			param.put("propNameId", propertyName.getId());
			List<Y23PropertyValue> valueList = y23PropertyValueService.getList(param);
			for (int i = 0; i < valueList.size(); i++) {
				for (int j = 0; j < y23GoodsProperties.size(); j++) {
					if(y23GoodsProperties.get(j).getPropValueId().longValue() == valueList.get(i).getId().longValue()) {
						valueList.get(i).setPrice(y23GoodsProperties.get(j).getPrice());
					}
				}
			}
			map.put(propertyName, valueList);
		}
		model.addAttribute("propertyMap", map);
		
		List<Long> nameIds = new ArrayList<Long>();
		List<Long> valueIds = new ArrayList<Long>();
		for (Y23GoodsProperty entity:y23GoodsProperties) {
			nameIds.add(entity.getPropNameId());
			valueIds.add(entity.getPropValueId());
		}
		model.addAttribute("nameIds", nameIds);
		model.addAttribute("valueIds", valueIds);
		
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23GoodsProperty> list = y23GoodsPropertyService.getList(param);
		PageResponse response = new PageResponse(y23GoodsPropertyService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23GoodsProperty entity) {
		ResponseInfo responseInfo = y23GoodsPropertyService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23GoodsProperty entity = y23GoodsPropertyService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @RequestParam Long goodsId, 
			@RequestParam String propValueIds, @RequestParam String prices) {
		ResponseInfo responseInfo = null;
		
		try {
			Y23GoodsProperty goodsProperty = new Y23GoodsProperty();
			goodsProperty.setGoodsId(goodsId);
			goodsProperty.setIsDelete(true);
			y23GoodsPropertyService.update(goodsProperty);
			
			String[] propValueArr = propValueIds.split(",");
			String[] priceArr = prices.split(",");
			for (int i = 0; i < propValueArr.length; i++) {
				Y23PropertyValue y23PropertyValue = y23PropertyValueService.getDetail(Long.parseLong(propValueArr[i]));
				
				Y23GoodsProperty y23GoodsProperty = new Y23GoodsProperty();
				y23GoodsProperty.setGoodsId(goodsId);
				y23GoodsProperty.setPropNameId(y23PropertyValue.getPropNameId());
				y23GoodsProperty.setPropValueId(y23PropertyValue.getId());
				y23GoodsProperty.setPrice(Double.parseDouble(priceArr[i]));
				y23GoodsPropertyService.add(y23GoodsProperty);
			}
			
			responseInfo = new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "产品属性修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23GoodsPropertyService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23GoodsPropertyService.deleteMul(ids);
		return responseInfo;
	}
	
	
}
