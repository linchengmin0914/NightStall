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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.service.business.Y23UserCartService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.GoodsCartBean;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserCartToOrderBean;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23UserCartListResponse;
import com.lcm.utils.response.Y23UserDetailResponse;
import com.lcm.utils.search.SearchParam;
import com.lcm.utils.spring.CommonInterceptor;
import com.lcm.utils.wx.common.AccessToken;
import com.lcm.utils.wx.common.WeixinUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@Controller(value="bY23UserCartController")
@RequestMapping("/y23/api/user/cart")
public class Y23UserCartController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23UserCartController.class);  
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23UserCartService y23UserCartService;
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	
	@ResponseBody
	@RequestMapping("/orderSave")
	public ResponseInfo orderSave(HttpServletRequest request,Model model,
			@RequestParam String goodsIds,
			@RequestParam String subIds,
			@RequestParam String nums,
			@RequestParam Long storeId,
			@RequestParam Long userId
		) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "添加购物车成功");
		String[] goodsIdArr = goodsIds.split(",");
		String[] subIdArr = subIds.split(",");
		String[] numArr = nums.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < goodsIdArr.length; i++) {
			Long goodsId = Long.parseLong(goodsIdArr[i]);
			Integer goodsNum = Integer.parseInt(numArr[i]);
			Y23Goods y23Goods =  y23GoodsService.getDetail(goodsId);
			int num = y23Goods.getStoreNum() - goodsNum;
			if(num < 0) return new ResponseInfo(ResCode.FAIL, "商品库存量不足，请谅解！");
			
			Y23UserCart y23UserCart = new Y23UserCart();
			y23UserCart.setUserId(userId);
			y23UserCart.setGoodsId(goodsId);
			y23UserCart.setStoreId(storeId);
			y23UserCart.setGoodsNum(goodsNum);
			y23UserCart.setProps(subIdArr[i]);
			
			y23UserCartService.add(y23UserCart);
			if(i == goodsIdArr.length - 1) {
				sb.append(y23UserCart.getId());
			} else {
				sb.append(y23UserCart.getId() + ",");
			}
		}
		
		responseInfo.setResDes(sb.toString());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public ResponseInfo save(HttpServletRequest request,Model model,@ModelAttribute Y23UserCart entity, 
			@RequestParam Long userId) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "SUCCESS");
		
		//扣除商品库存量
		Y23Goods y23Goods = new Y23Goods();
		if(StringUtils.isEmpty(entity.getId())) {
			y23Goods = y23GoodsService.getDetail(entity.getGoodsId());
		} else {
			Y23UserCart y23UserCart = y23UserCartService.getDetail(entity.getId());
			y23Goods = y23GoodsService.getDetail(y23UserCart.getGoodsId());
		}
		int num = y23Goods.getStoreNum() - entity.getGoodsNum();
		if(num < 0) {
			return new ResponseInfo(ResCode.FAIL, "商品库存量不足，请谅解！");
		}
		
		if(StringUtils.isEmpty(entity.getId())) {
			entity.setUserId(userId);
			entity.setStoreId(y23Goods.getStoreId());
			responseInfo = y23UserCartService.add(entity);
			responseInfo.setResDes(entity.getId() + "");
			
		} else {
			Y23UserCart userCart = y23UserCartService.getDetail(entity.getId());
			userCart.setGoodsNum(entity.getGoodsNum());
			responseInfo = y23UserCartService.update(userCart);
		}
		return responseInfo;
	}
	
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Map param = searchParam.getSearchMap();
		param.put("userId", userId);
		param.put("orderBy", " a.create_time desc");
		List<Y23UserCart> list = y23UserCartService.getList(param);
		
		for (int i = 0; i < list.size(); i++) {
			String[] arr = list.get(i).getProps().split(",");
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < arr.length; j++) {
				if(!StringUtils.isEmpty(arr[j])) {
					Y23GoodsProperty y23GoodsProperty = y23GoodsPropertyService.getDetail(Long.parseLong(arr[j]));
					sb.append(y23GoodsProperty.getValueTitle() + ",");
					list.get(i).setPrice(y23GoodsProperty.getPrice());
				}
				
			}
			String propsStr = sb.toString();
			if(propsStr.endsWith(",")) {
				propsStr = propsStr.substring(0,propsStr.length() - 1);
			}
			list.get(i).setPropsStr(propsStr);
			list.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + list.get(i).getCover());
		}
		
		Y23UserCartListResponse responseInfo = new Y23UserCartListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public ResponseInfo delete(HttpServletRequest request,Model model,@PathVariable Long id, 
			@RequestParam Long userId) {
		Y23UserCart entity = y23UserCartService.getDetail(id);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		entity.setIsDelete(true);
		ResponseInfo responseInfo = y23UserCartService.update(entity);
		
		return responseInfo;
	}
}
