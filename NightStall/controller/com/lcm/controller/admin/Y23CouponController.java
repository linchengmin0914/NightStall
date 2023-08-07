package com.lcm.controller.admin;

import java.text.SimpleDateFormat;
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
import com.lcm.entity.pojo.Y23Coupon;
import com.lcm.entity.pojo.Y23CouponGoods;
import com.lcm.entity.pojo.Y23CouponStore;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23Store;
import com.lcm.service.business.Y23CouponGoodsService;
import com.lcm.service.business.Y23CouponService;
import com.lcm.service.business.Y23CouponStoreService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23CouponController")
@RequestMapping("/admin/01/coupon")
public class Y23CouponController extends BaseController {
	@Autowired
	private Y23CouponService y23CouponService;
	@Autowired
	private Y23CouponGoodsService y23CouponGoodsService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23StoreService y23StoreService;
	@Autowired
	private Y23CouponStoreService y23CouponStoreService;
	
	private String path = "01/coupon";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23Coupon> list = y23CouponService.getList(param);
		PageResponse response = new PageResponse(y23CouponService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		Map param = new HashMap();
		param.put("isSale", "1");
		param.put("orderBy", " a.create_time desc");
		List<Y23Goods> goodsList = y23GoodsService.getList(param);
		model.addAttribute("goodsList", goodsList);
		
		Map param2 = new HashMap();
		param2.put("orderBy", " a.createTime desc");
		List<Y23Store> storeList = y23StoreService.getList(param2);
		model.addAttribute("storeList", storeList);
		
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23Coupon entity,
			@RequestParam String getstartStr,@RequestParam String getendStr,
			@RequestParam String validstartStr,@RequestParam String validendStr,
			@RequestParam(required=false) String goodsIds,
			@RequestParam(required=false) String storeIds
			) throws Exception {
		
		entity.setGetstart("".equals(getstartStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getstartStr));
		entity.setGetend("".equals(getendStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getendStr));
		entity.setValidstart("".equals(validstartStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validstartStr));
		entity.setValidend("".equals(validendStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validendStr));
		ResponseInfo responseInfo = y23CouponService.add(entity);
		
		if(entity.getScope() == 2) {
			String[] goodsArr = goodsIds.split(",");
			for (int i = 0; i < goodsArr.length; i++) {
				Y23CouponGoods y23CouponGoods = new Y23CouponGoods();
				y23CouponGoods.setCouponId(entity.getId());
				y23CouponGoods.setGoodsId(Long.parseLong(goodsArr[i]));
				y23CouponGoodsService.add(y23CouponGoods);
			}
		}
		
		if(entity.getScope() == 3) {
			String[] storeArr = storeIds.split(",");
			for (int i = 0; i < storeArr.length; i++) {
				Y23CouponStore y23CouponGoods = new Y23CouponStore();
				y23CouponGoods.setCouponId(entity.getId());
				y23CouponGoods.setStoreId(Long.parseLong(storeArr[i]));
				y23CouponStoreService.add(y23CouponGoods);
			}
		}
		
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23Coupon entity = y23CouponService.getDetail(id);
		model.addAttribute("entity", entity);
		
		Map param1 = new HashMap();
		param1.put("couponId", entity.getId());
		List<Y23CouponGoods> y23CouponGoods = y23CouponGoodsService.getList(param1);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < y23CouponGoods.size(); i++) {
			if(i == y23CouponGoods.size() - 1) {
				sb.append(y23CouponGoods.get(i).getGoodsId());
			} else {
				sb.append(y23CouponGoods.get(i).getGoodsId() + ",");
			}
		}
		model.addAttribute("goodsIds", sb.toString());
		
		
		Map param3 = new HashMap();
		param3.put("couponId", entity.getId());
		List<Y23CouponStore> y23CouponStores = y23CouponStoreService.getList(param3);
		StringBuffer sb3 = new StringBuffer();
		for (int i = 0; i < y23CouponStores.size(); i++) {
			if(i == y23CouponStores.size() - 1) {
				sb3.append(y23CouponStores.get(i).getStoreId());
			} else {
				sb3.append(y23CouponStores.get(i).getStoreId() + ",");
			}
		}
		model.addAttribute("storeIds", sb3.toString());
		
		Map param = new HashMap();
		param.put("isSale", "1");
		param.put("orderBy", " a.create_time desc");
		List<Y23Goods> goodsList = y23GoodsService.getList(param);
		model.addAttribute("goodsList", goodsList);
		
		Map param2 = new HashMap();
		param2.put("orderBy", " a.createTime desc");
		List<Y23Store> storeList = y23StoreService.getList(param2);
		model.addAttribute("storeList", storeList);
		
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23Coupon entity,
			@RequestParam String getstartStr,@RequestParam String getendStr,
			@RequestParam String validstartStr,@RequestParam String validendStr,
			@RequestParam(required=false) String goodsIds,
			@RequestParam(required=false) String storeIds
			) throws Exception {
		entity.setGetstart("".equals(getstartStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getstartStr));
		entity.setGetend("".equals(getendStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getendStr));
		entity.setValidstart("".equals(validstartStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validstartStr));
		entity.setValidend("".equals(validendStr)?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validendStr));
		
		ResponseInfo responseInfo = y23CouponService.update(entity);
		
		if(entity.getScope() == 2) {
			Map param1 = new HashMap();
			param1.put("couponId", entity.getId());
			List<Y23CouponGoods> list = y23CouponGoodsService.getList(param1);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setIsDelete(true);
				y23CouponGoodsService.update(list.get(i));
			}
			
			String[] goodsArr = goodsIds.split(",");
			for (int i = 0; i < goodsArr.length; i++) {
				Y23CouponGoods y23CouponGoods = new Y23CouponGoods();
				y23CouponGoods.setCouponId(entity.getId());
				y23CouponGoods.setGoodsId(Long.parseLong(goodsArr[i]));
				y23CouponGoodsService.add(y23CouponGoods);
			}
		}
		
		if(entity.getScope() == 3) {
			Map param1 = new HashMap();
			param1.put("couponId", entity.getId());
			List<Y23CouponStore> list = y23CouponStoreService.getList(param1);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setIsDelete(true);
				y23CouponStoreService.update(list.get(i));
			}
			
			String[] storeArr = storeIds.split(",");
			for (int i = 0; i < storeArr.length; i++) {
				Y23CouponStore y23CouponGoods = new Y23CouponStore();
				y23CouponGoods.setCouponId(entity.getId());
				y23CouponGoods.setStoreId(Long.parseLong(storeArr[i]));
				y23CouponStoreService.add(y23CouponGoods);
			}
		}
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23CouponService.delete(id);
		
		//删除相关的店铺或商品优惠券
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23CouponService.deleteMul(ids);
		
		//删除相关的店铺或商品优惠券
		
		return responseInfo;
	}
	
}
