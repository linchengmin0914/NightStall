package com.lcm.controller.admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Categories;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23PostageTemplete;
import com.lcm.entity.pojo.Y23Store;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23GoodsImagesService;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23PostageTempleteService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23GoodsController")
@RequestMapping("/admin/01/goods")
public class Y23GoodsController extends BaseController {
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23CategoriesService y23CategoriesService;
	@Autowired
	private Y23GoodsImagesService y23GoodsImagesService;
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	@Autowired
	private Y23PostageTempleteService y23PostageTempleteService;
	@Autowired
	private Y23StoreService y23StoreService;
	
	private String path = "01/goods";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		
		Map pparam = new HashMap();
		pparam.put("pid", 0);
		List<Y23Categories> parentList = y23CategoriesService.getList(pparam);
		Map<Y23Categories, List<Y23Categories>> categoryMap = new LinkedHashMap<Y23Categories, List<Y23Categories>>();
		for (Y23Categories category: parentList) {
			Map param = new HashMap();
			param.put("pid", category.getId());
			List<Y23Categories> cateList = y23CategoriesService.getList(param);
			categoryMap.put(category, cateList);
		}
		model.addAttribute("categoryMap", categoryMap);
		System.out.println("=========" + categoryMap.size());
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@RequestMapping("/fxindex")
	public String fxindex(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/fxindex";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23Goods> list = y23GoodsService.getList(param);
		PageResponse response = new PageResponse(y23GoodsService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		
		Map pparam = new HashMap();
		pparam.put("pid", 0);
		List<Y23Categories> parentList = y23CategoriesService.getList(pparam);
		Map<Y23Categories, List<Y23Categories>> categoryMap = new LinkedHashMap<Y23Categories, List<Y23Categories>>();
		for (Y23Categories category: parentList) {
			Map param = new HashMap();
			param.put("pid", category.getId());
			List<Y23Categories> cateList = y23CategoriesService.getList(param);
			categoryMap.put(category, cateList);
		}
		model.addAttribute("categoryMap", categoryMap);
		
		Map param = new HashMap();
		param.put("orderBy", " a.create_time desc");
		List<Y23PostageTemplete> postageTempletes = y23PostageTempleteService.getList(param);
		model.addAttribute("postageTempletes", postageTempletes);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23Goods entity) {
		ResponseInfo responseInfo = y23GoodsService.add(entity);
		
		Map param = new HashMap();
		param.put("goodsId", entity.getId());
		int rowNum = y23GoodsImagesService.getCount(param);
		if(rowNum <= 0) {
			Y23GoodsImages y23GoodsImages = new Y23GoodsImages();
			y23GoodsImages.setGoodsId(entity.getId());
			y23GoodsImages.setLink(entity.getCover());
			y23GoodsImages.setPosition(1);
			y23GoodsImages.setIsMaster(1);
			y23GoodsImagesService.add(y23GoodsImages);
		}
		
		Map param2 = new HashMap();
		param2.put("goodsId", entity.getId());
		int rowNum2 = y23GoodsPropertyService.getCount(param2);
		if(rowNum2 <= 0) {
			Y23GoodsProperty y23GoodsProperty = new Y23GoodsProperty();
			y23GoodsProperty.setGoodsId(entity.getId());
			y23GoodsProperty.setPropNameId(1L);
			y23GoodsProperty.setPropValueId(1L);
			y23GoodsProperty.setPrice(entity.getPrice());
			y23GoodsPropertyService.add(y23GoodsProperty);
		}
		
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		List<Y23Store> y23Stores = y23StoreService.getList();
		model.addAttribute("y23Stores", y23Stores);
		
		Y23Goods entity = y23GoodsService.getDetail(id);
		model.addAttribute("entity", entity);
		
		Map pparam = new HashMap();
		pparam.put("pid", 0);
		List<Y23Categories> parentList = y23CategoriesService.getList(pparam);
		Map<Y23Categories, List<Y23Categories>> categoryMap = new LinkedHashMap<Y23Categories, List<Y23Categories>>();
		for (Y23Categories category: parentList) {
			Map param = new HashMap();
			param.put("pid", category.getId());
			List<Y23Categories> cateList = y23CategoriesService.getList(param);
			categoryMap.put(category, cateList);
		}
		model.addAttribute("categoryMap", categoryMap);
		
		Map param = new HashMap();
		param.put("orderBy", " a.create_time desc");
		List<Y23PostageTemplete> postageTempletes = y23PostageTempleteService.getList(param);
		model.addAttribute("postageTempletes", postageTempletes);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23Goods entity) {
		if(!StringUtils.isEmpty(entity.getIsSale())) {
			if(entity.getIsSale()  == 1) {
				Map param = new LinkedHashMap();
				param.put("goodsId", entity.getId());
				Integer num = y23GoodsImagesService.getCount(param);
				if(num == 0) {
					return new ResponseInfo(ResCode.FAIL, "请先完善商品图片再进行启用，谢谢！");
				}
				
				param.put("goodsId", entity.getId());
				param.put("status", "1");
				num = y23GoodsPropertyService.getCount(param);
				if(num == 0) {
					return new ResponseInfo(ResCode.FAIL, "请先完善商品属性再进行启用，谢谢！");
				}
			}
		}
		
		ResponseInfo responseInfo = y23GoodsService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23GoodsService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23GoodsService.deleteMul(ids);
		return responseInfo;
	}
	

	@ResponseBody
	@RequestMapping("/sale/mul/done")
	public ResponseInfo saleMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23GoodsService.saleMul(ids);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/notsale/mul/done")
	public ResponseInfo notSaleMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23GoodsService.notSaleMul(ids);
		return responseInfo;
	}
	
	@RequestMapping("/setcommission")
	public String setcommission(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23Goods entity = y23GoodsService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/setcommission";
	}
	
}
