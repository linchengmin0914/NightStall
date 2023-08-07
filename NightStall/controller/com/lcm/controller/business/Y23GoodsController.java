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
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.entity.pojo.Y23Categories;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23SysBanner;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsImagesService;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23PropertyNameService;
import com.lcm.service.business.Y23UserGoodsService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23SysBannerService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.Y23GoodsDetailResponse;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23CategoriesListResponse;
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

@Controller(value="bY23GoodsController")
@RequestMapping("/y23/api/goods")
public class Y23GoodsController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23GoodsController.class); 
	
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23GoodsImagesService y23GoodsImagesService;
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	@Autowired
	private Y23PropertyNameService y23PropertyNameService;
	@Autowired
	private Y23GoodsCollectService y23GoodsCollectService;
	@Autowired
	private Y23UserViewsService y23UserViewsService;
	@Autowired
	private Y23UserGoodsService y23UserGoodsService;
	
	@ResponseBody
	@RequestMapping("/detail/{id}")
	public ResponseInfo detail(HttpServletRequest request,Model model, @PathVariable Long id,
			@RequestParam Long userId) {
		Y23Goods goods = y23GoodsService.getDetail(id);
		goods.setCover(PropertiesUtil.getValue("SYS_PATH") + goods.getCover());
		
		Map param = new LinkedHashMap();
		param.put("goodsId", goods.getId());
		List<Y23GoodsImages>  imgs = y23GoodsImagesService.getList(param);
		for (int i = 0; i < imgs.size(); i++) {
			imgs.get(i).setLink(PropertiesUtil.getValue("SYS_PATH") + imgs.get(i).getLink());
		}
		
		Map propParam = new LinkedHashMap();
		propParam.put("goodsId", goods.getId());
		propParam.put("status", "1");
		List<Long> propList = new ArrayList<Long>();
		List<Y23GoodsProperty> goodsPropertyList = y23GoodsPropertyService.getList(propParam);
		Map<String,List<Y23GoodsProperty>> propMap = new LinkedHashMap<String, List<Y23GoodsProperty>>();
		for (Y23GoodsProperty y23GoodsProperty : goodsPropertyList) {
			propList.add(y23GoodsProperty.getId());
			List<Y23GoodsProperty> pList = new ArrayList<Y23GoodsProperty>();
			String key = y23GoodsProperty.getNameTitle();
			if(propMap.containsKey(key)) {
				pList = propMap.get(key);
			}
			pList.add(y23GoodsProperty);
			propMap.put(key, pList);
		}
		
		Y23GoodsDetailResponse response = new Y23GoodsDetailResponse(ResCode.SUCCESS, "success", goods);
		response.setImgs(imgs);
		response.setPropList(propList);
		response.setPropMap(propMap);
		
		//增加用户浏览记录
		Map vparam = new LinkedHashMap(); 
		vparam.put("page", 0);
		vparam.put("pageSize", 1);
		vparam.put("userId", userId);
		vparam.put("orderBy", " createTime desc");
		List<Y23UserViews> list = y23UserViewsService.getList(vparam);
		if(list.size() > 0) {
			if(list.get(0).getGoodsId().longValue() != id.longValue()) {
				Y23UserViews y23UserViews = new Y23UserViews();
				y23UserViews.setGoodsId(id);
				y23UserViews.setUserId(userId);
				y23UserViewsService.add(y23UserViews);
			}
		} else {
			Y23UserViews y23UserViews = new Y23UserViews();
			y23UserViews.setGoodsId(id);
			y23UserViews.setUserId(userId);
			y23UserViewsService.add(y23UserViews);
		}
		return response;
	}
}
