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
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserComment;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23SysBanner;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserComment;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23UserCommentService;
import com.lcm.service.business.Y23UserOrderitemService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23SysBannerService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommentService;
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
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23SysBannerDetailResponse;
import com.lcm.utils.response.Y23SysBannerListResponse;
import com.lcm.utils.response.Y23UserCommentListResponse;
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

@Controller(value="bY23UserCommentController")
@RequestMapping("/y23/api/user/comment")
public class Y23UserCommentController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23UserCommentController.class); 
	
	@Autowired
	private Y23UserCommentService y23UserCommentService;
	@Autowired
	private Y23UserOrderitemService y23UserOrderitemService;
	@Autowired
	private Y23UserOrdersService y23UserOrdersService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	
	@ResponseBody
	@RequestMapping("/save")
	public ResponseInfo save(HttpServletRequest request,Model model,@ModelAttribute Y23UserComment entity, 
			@RequestParam Long userId) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "SUCCESS");
		
		Map param = new HashMap();
		param.put("orderId", entity.getOrderId());
		List<Y23UserOrderitem> list = y23UserOrderitemService.getList(param);
		for (Y23UserOrderitem userOrderitem:list) {
			Y23UserComment y23UserComment = new Y23UserComment();
			y23UserComment.setOrderId(entity.getOrderId());
			y23UserComment.setGoodsId(userOrderitem.getGoodsId());
			y23UserComment.setUserId(userId);
			y23UserComment.setContent(entity.getContent());
			y23UserComment.setOverall(entity.getStoreScore());
			y23UserComment.setStoreScore(entity.getStoreScore());
			y23UserComment.setDelivScore(entity.getDelivScore());
			y23UserComment.setStoreId(y23GoodsService.getDetail(userOrderitem.getGoodsId()).getStoreId());
			y23UserCommentService.add(y23UserComment);
		}
		
		Y23UserOrders y23UserOrders = y23UserOrdersService.getDetail(entity.getOrderId());
		y23UserOrders.setStatus(60);
		y23UserOrders.setEndtime(new Date());
		responseInfo = y23UserOrdersService.update(y23UserOrders);
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		param.put("orderBy", " a.create_time desc");
		List<Y23UserComment> list = y23UserCommentService.getList(param);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUserCover() == null) {
				list.get(i).setUserCover(PropertiesUtil.getValue("SYS_PATH") + "images/default-image.png");
			} else {
				list.get(i).setUserCover(list.get(i).getUserCover());
			}
		}
		
		Y23UserCommentListResponse responseInfo = new Y23UserCommentListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	
}
