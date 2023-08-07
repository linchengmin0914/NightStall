package com.lcm.controller.business;

import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Categories;
import com.lcm.entity.pojo.Y23Coupon;
import com.lcm.entity.pojo.Y23CouponStore;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23Store;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23SysBanner;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23CouponService;
import com.lcm.service.business.Y23CouponStoreService;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23PropertyValueService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23SysBannerService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.file.LocationUtils;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.MenuGoodsBean;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.PromotionBean;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.SubGoodsBean;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23StoreDetailResponse;
import com.lcm.utils.response.Y23StoreListResponse;
import com.lcm.utils.response.Y23SysBannerDetailResponse;
import com.lcm.utils.response.Y23SysBannerListResponse;
import com.lcm.utils.response.Y23UserDetailResponse;
import com.lcm.utils.search.SearchParam;
import com.lcm.utils.spring.CommonInterceptor;
import com.lcm.utils.wx.common.AccessToken;
import com.lcm.utils.wx.common.WeixinUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.commons.collections.Buffer;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@Controller(value="bY23StoreController")
@RequestMapping("/y23/api/store")
public class Y23StoreController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23StoreController.class); 
	
	@Autowired
	private Y23StoreService y23StoreService;
	@Autowired
	private Y23CategoriesService y23CategoriesService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23CouponService y23CouponService;
	@Autowired
	private Y23CouponStoreService y23CouponStoreService;
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Double latitude,@RequestParam Double longitude) {
		Map param = searchParam.getSearchMap();
//		param.put("status", "1");
		param.put("orderBy", " a.createTime asc");
		List<Y23Store> list = y23StoreService.getList(param);
		
		Double lastDistance = 10000D;
		Long defaultStoreId = 0L;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + list.get(i).getCover());
			Double distance = LocationUtils.getDistance(latitude, longitude, list.get(i).getLatitude(), list.get(i).getLongitude());
			list.get(i).setDistance(distance);
			if(distance < lastDistance && list.get(i).getStatus() == 1) {
				lastDistance = distance;
				defaultStoreId = list.get(i).getId();
			}
		}
		Y23StoreListResponse responseInfo = new Y23StoreListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setDefaultStoreId(defaultStoreId);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/detail/{id}")
	public ResponseInfo detail(HttpServletRequest request,Model model,@PathVariable Long id,
			@RequestParam(required=false) Long cateId,
			@RequestParam Double latitude,@RequestParam Double longitude,
			@RequestParam(required=false) String key) {
		Y23Store entity = y23StoreService.getDetail(id);
		entity.setCover(PropertiesUtil.getValue("SYS_PATH") + entity.getCover());
		Double distance = LocationUtils.getDistance(latitude, longitude, entity.getLatitude(), entity.getLongitude());
		entity.setDistance(distance);
		
		List<Y23Categories> categories = new ArrayList<Y23Categories>();
		Y23Categories allCategory = new Y23Categories();
		allCategory.setId(0L);
		allCategory.setCateName("全部");
		categories.add(allCategory);
		
		Map param = new HashMap();
		param.put("orderBy", " a.sort asc");
		param.put("page", 0);
		param.put("pageSize", 99);
		categories.addAll(y23CategoriesService.getList(param));
		
		
		Y23StoreDetailResponse responseInfo = new Y23StoreDetailResponse(ResCode.SUCCESS, "SUCCESS");
		responseInfo.setId(entity.getId());
		responseInfo.setName(entity.getName());
		responseInfo.setCover(entity.getCover());
		responseInfo.setPhone(entity.getPhone());
		responseInfo.setSummary(entity.getSummary());
		responseInfo.setAddress(entity.getAddress());
		responseInfo.setOpentime(entity.getOpentime());
		responseInfo.setStatus(entity.getStatus());
		responseInfo.setLatitude(entity.getLatitude());
		responseInfo.setLongitude(entity.getLongitude());
		responseInfo.setMsaleNum(entity.getMsaleNum());
		responseInfo.setOverall(entity.getOverall());
		responseInfo.setDistanceFormat(entity.getDistanceFormat());
		responseInfo.setMinDeliveryPrice(entity.getMinDeliveryPrice());
		responseInfo.setDeliveryTime(entity.getDeliveryTime());
		responseInfo.setDistance(distance);
		
		responseInfo.setActiveMenuIndex(0L);
		if(categories.size() > 0) {
			if(cateId != null && cateId != -1) {
				for (int i = 0; i < categories.size(); i++) {
					if(categories.get(i).getId().longValue() == cateId) {
						responseInfo.setActiveMenuIndex(Long.valueOf(i));
					}
				}
			} 
		} 
		
		Map goodParam = new HashMap();
		goodParam.put("orderBy", " a.cate_id asc,a.create_time desc");
		goodParam.put("isSale", "1");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < categories.size(); i++) {
			if(i == categories.size() - 1) {
				sb.append(categories.get(i).getId() + "");
			} else {
				sb.append(categories.get(i).getId() + ",");
			}
		}
		goodParam.put("cateIds", sb.toString());
		if(!StringUtils.isEmpty(key)) goodParam.put("key", key);
		List<Y23Goods> y23GoodsList = y23GoodsService.getList(goodParam);
	
		StringBuffer goodsIds = new StringBuffer();
		List<MenuGoodsBean> menus = new ArrayList<MenuGoodsBean>();
		
		MenuGoodsBean allMenuGoodsBean = new MenuGoodsBean();
		allMenuGoodsBean.setCateId(0L);
		allMenuGoodsBean.setCateName("全部");
		allMenuGoodsBean.setGoodsList(y23GoodsList);
		menus.add(allMenuGoodsBean);
		
		for (Y23Categories y23Categories:categories) {
			if(y23Categories.getId().longValue() != 0) {
				MenuGoodsBean menuGoodsBean = new MenuGoodsBean();
				menuGoodsBean.setCateId(y23Categories.getId());
				menuGoodsBean.setCateName(y23Categories.getCateName());
				
				List<Y23Goods> goodsList = new LinkedList<Y23Goods>();
				for (int i = 0; i < y23GoodsList.size(); i++) {
					if(y23Categories.getId().longValue() == y23GoodsList.get(i).getCateId()) {
						y23GoodsList.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + y23GoodsList.get(i).getCover());
						goodsList.add(y23GoodsList.get(i));
					}
					
					if(i == y23GoodsList.size() - 1) {
						goodsIds.append(y23GoodsList.get(i).getId() + "");
					} else {
						goodsIds.append(y23GoodsList.get(i).getId() + ",");
					}
				}
				menuGoodsBean.setGoodsList(goodsList);
				menus.add(menuGoodsBean);
			}
		}
		
		
		Map proParam = new HashMap();
		proParam.put("orderBy", " a.goods_id asc,a.create_time asc");
		proParam.put("goodsIds", goodsIds.toString());
		List<Y23GoodsProperty> y23GoodsProperties = y23GoodsPropertyService.getList(proParam);
		List<Y23Goods> goodsList = new LinkedList<Y23Goods>();
		for (int i = 0; i < y23GoodsList.size(); i++) {
			List<SubGoodsBean> subGoodsBeans = new LinkedList<SubGoodsBean>();
			for (int j = 0; j < y23GoodsProperties.size(); j++) {
				if(y23GoodsList.get(i).getId().longValue() == y23GoodsProperties.get(j).getGoodsId().longValue()) {
					SubGoodsBean subGoodsBean = new SubGoodsBean();
					subGoodsBean.setSubId(y23GoodsProperties.get(j).getId());
					subGoodsBean.setNameId(y23GoodsProperties.get(j).getPropNameId());
					subGoodsBean.setSubName(y23GoodsProperties.get(j).getValueTitle());
					subGoodsBean.setSubPrice(y23GoodsProperties.get(j).getPrice());
					subGoodsBeans.add(subGoodsBean);
				}
			}
			y23GoodsList.get(i).setSubGoods(subGoodsBeans);
		}
		
		responseInfo.setMenus(menus);
		
		List<PromotionBean> promotion = new LinkedList<PromotionBean>();
		
		Map param3 = new HashMap();
		param3.put("scope", "1");
		List<Y23Coupon> y23Coupons = y23CouponService.getList(param3);
		for (Y23Coupon y23Coupon:y23Coupons) {
			PromotionBean promotionBean = new PromotionBean();
			promotionBean.setPicUrl(PropertiesUtil.getValue("SYS_PATH") + "images/logo.png");
			promotionBean.setInfo(y23Coupon.getName());
			promotion.add(promotionBean);
		}
		
		Map param4 = new HashMap();
		param4.put("storeId", entity.getId());
		List<Y23CouponStore> y23CouponStores = y23CouponStoreService.getList(param4);
		for (Y23CouponStore y23CouponStore:y23CouponStores) {
			PromotionBean promotionBean = new PromotionBean();
			promotionBean.setPicUrl(PropertiesUtil.getValue("SYS_PATH") + "images/logo.png");
			promotionBean.setInfo(y23CouponStore.getName());
			promotion.add(promotionBean);
		}
		
		responseInfo.setPromotion(promotion);
		
		return responseInfo;
	}
	
}
