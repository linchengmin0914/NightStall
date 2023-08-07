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
import com.lcm.entity.pojo.Y23Coupon;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserCoupon;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23CouponGoodsService;
import com.lcm.service.business.Y23CouponService;
import com.lcm.service.business.Y23UserCouponService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23SystemParamService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.file.DateUtils;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.Y23CouponDetailResponse;
import com.lcm.utils.response.Y23UserAddressDetailResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23CouponListResponse;
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

@Controller(value="bY23CouponController")
@RequestMapping("/y23/api/coupon")
public class Y23CouponController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23CouponController.class);
	
	@Autowired
	private Y23CouponService y23CouponService;
	@Autowired
	private Y23CouponGoodsService y23CouponGoodsService;
	@Autowired
	private Y23UserCouponService y23UserCouponService;
	@Autowired
	private Y23UserService y23UserService;
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam(required=false) Long userId) {
		Map param = searchParam.getSearchMap();
		param.put("notPrivate", "4");
		param.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		param.put("orderBy", " a.getstart desc,a.createTime desc");
		List<Y23Coupon> list = y23CouponService.getList(param);
		
		if(!StringUtils.isEmpty(userId)) {
			Map uparam = new HashMap();
			uparam.put("userId", userId);
			List<Y23UserCoupon> y23UserCoupons = y23UserCouponService.getList(uparam);
			
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < y23UserCoupons.size(); j++) {
					if(list.get(i).getId().longValue() == y23UserCoupons.get(j).getCouponId().longValue()) {
						list.get(i).setIsGeted(1);
						break;
					}
				}
			}
		}
		
		Y23CouponListResponse response = new Y23CouponListResponse(ResCode.SUCCESS, "success", list);
		response.setPage(searchParam.getPage() + 1);
		response.setPageSize(searchParam.getPageSize());
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/private/list")
	public ResponseInfo privateList(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		param.put("isPrivate", "4");
		param.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		param.put("orderBy", " a.getstart desc,a.createTime desc");
		List<Y23Coupon> list = y23CouponService.getList(param);
		
		Y23CouponListResponse response = new Y23CouponListResponse(ResCode.SUCCESS, "success", list);
		response.setPage(searchParam.getPage() + 1);
		response.setPageSize(searchParam.getPageSize());
		return response;
	}
	 
	@ResponseBody
	@RequestMapping("/receive")
	public ResponseInfo receive(HttpServletRequest request,Model model,@RequestParam Long couponId, 
			@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		if(y23User == null) return new ResponseInfo(ResCode.FAIL, "请您先登录再领取");
		Y23Coupon y23Coupon = y23CouponService.getDetail(couponId);
		Map param = new HashMap();
		param.put("userId", y23User.getId());
		param.put("couponId", couponId);
		Integer num = y23UserCouponService.getCount(param);
		if(num >= y23Coupon.getMoreget()) return new ResponseInfo(ResCode.FAIL, "您已经领取过了，不能再领取了");
		if(y23Coupon.getSkunum() <= 0) return new ResponseInfo(ResCode.FAIL, "您来晚了，优惠券已经被领完");
		
		Y23UserCoupon y23UserCoupon = new Y23UserCoupon();
		y23UserCoupon.setUserId(y23User.getId());
		y23UserCoupon.setCouponId(couponId);
		y23UserCoupon.setScope(y23Coupon.getScope());
		y23UserCoupon.setType(y23Coupon.getType());
		y23UserCoupon.setFace(y23Coupon.getFace());
		y23UserCoupon.setName(y23Coupon.getName());
		y23UserCoupon.setSkunum(y23Coupon.getSkunum());
		y23UserCoupon.setUserequire(y23Coupon.getUserequire());
		y23UserCoupon.setMoreget(y23Coupon.getMoreget());
		y23UserCoupon.setGetstart(y23Coupon.getGetstart());
		y23UserCoupon.setGetend(y23Coupon.getGetend());
		y23UserCoupon.setValidtype(y23Coupon.getValidtype());
		if(y23Coupon.getValidtype() == 1) {
			y23UserCoupon.setValidstart(DateUtils.getFetureToDate(y23Coupon.getValidsnum()));
			y23UserCoupon.setValidend(DateUtils.getFetureToDate(y23Coupon.getValidenum()));
		} else {
			y23UserCoupon.setValidstart(y23Coupon.getValidstart());
			y23UserCoupon.setValidend(y23Coupon.getValidend());
		}
		y23UserCoupon.setValidsnum(y23Coupon.getValidsnum());
		y23UserCoupon.setValidenum(y23Coupon.getValidenum());
		y23UserCoupon.setSummary(y23Coupon.getSummary());
		y23UserCoupon.setStatus(1);
		y23UserCouponService.add(y23UserCoupon);
		
		y23Coupon.setSkunum(y23Coupon.getSkunum() - 1);
		y23CouponService.update(y23Coupon);
		return new ResponseInfo(ResCode.SUCCESS, "领取优惠券成功");
	}
	
	@ResponseBody
	@RequestMapping("/detail/{id}")
	public ResponseInfo detail(HttpServletRequest request,Model model,@PathVariable Long id) {
		Y23Coupon entity = y23CouponService.getDetail(id);
		Y23CouponDetailResponse responseInfo = new Y23CouponDetailResponse(ResCode.SUCCESS, "SUCCESS", entity);
		return responseInfo;
	}
}
