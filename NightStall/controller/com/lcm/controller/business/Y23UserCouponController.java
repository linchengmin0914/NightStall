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
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCoupon;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23CouponService;
import com.lcm.service.business.Y23UserCartService;
import com.lcm.service.business.Y23UserCouponService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23SystemParamService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.Y23UserCouponListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@Controller(value="bY23UserCouponController")
@RequestMapping("/y23/api/user/coupon")
public class Y23UserCouponController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23UserCouponController.class);
	
	@Autowired
	private Y23UserCouponService y23UserCouponService;
	@Autowired
	private Y23CouponService y23CouponService;
	@Autowired
	private Y23UserCartService y23UserCartService;
	@Autowired
	private Y23UserService y23UserService;
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Map param = searchParam.getSearchMap();
		if(searchParam.getStatus().equals("1")) {
			param.remove("status");
			param.put("statuses","0,1");
		}
		
		param.put("userId", userId);
		param.put("orderBy", " a.createTime desc");
		List<Y23UserCoupon> list = y23UserCouponService.getList(param);
		Y23UserCouponListResponse responseInfo = new Y23UserCouponListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/calcoupon")
	public ResponseInfo calcoupon(HttpServletRequest request,Model model,@RequestParam Long userCouponId, 
			@RequestParam Double totalPrice,
			@RequestParam Long userId) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "SUCCESS");
		Y23User y23User = y23UserService.getDetail(userId);
		if(y23User == null) return new ResponseInfo(ResCode.FAIL, "请您先登录再使用");
		Y23UserCoupon y23UserCoupon = y23UserCouponService.getDetail(userCouponId);
		
		BigDecimal bg = new BigDecimal(0);
		if(y23UserCoupon.getType() == 1) {
			bg = new BigDecimal(y23UserCoupon.getFace());
		} else {
			bg = new BigDecimal((1 - y23UserCoupon.getFace()) * totalPrice);
		}
		
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new ResponseInfo(ResCode.SUCCESS, f1 + "");
	}
}
