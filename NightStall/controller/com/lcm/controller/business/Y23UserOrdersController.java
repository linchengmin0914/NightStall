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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.springframework.web.client.RestTemplate;

import sun.misc.BASE64Decoder;

import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Categories;
import com.lcm.entity.pojo.Y23DadaStore;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23PostageArea;
import com.lcm.entity.pojo.Y23PostagePiece;
import com.lcm.entity.pojo.Y23PostageTemplete;
import com.lcm.entity.pojo.Y23Store;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserCoupon;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;
import com.lcm.entity.pojo.Y23UserShopping;
import com.lcm.entity.pojo.Y23CouponGoods;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23UserCoupon;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23CategoriesService;
import com.lcm.service.business.Y23CouponGoodsService;
import com.lcm.service.business.Y23CouponService;
import com.lcm.service.business.Y23DadaStoreService;
import com.lcm.service.business.Y23GoodsPropertyService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23PostageAreaService;
import com.lcm.service.business.Y23PostagePieceService;
import com.lcm.service.business.Y23PostageTempleteService;
import com.lcm.service.business.Y23StoreService;
import com.lcm.service.business.Y23SystemParamService;
import com.lcm.service.business.Y23UserAddressService;
import com.lcm.service.business.Y23UserCartService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserCouponService;
import com.lcm.service.business.Y23UserOrderitemService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserPayinfoService;
import com.lcm.service.business.Y23UserShoppingService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23UserCartService;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.utils.dada.AddAfterQuery;
import com.lcm.utils.dada.AddAfterQueryResult;
import com.lcm.utils.dada.OrderDetail;
import com.lcm.utils.dada.OrderDetailResult;
import com.lcm.utils.dada.QueryDeliverFee;
import com.lcm.utils.dada.QueryDeliverFeeResult;
import com.lcm.utils.dada.QueryOrder;
import com.lcm.utils.dada.QueryOrderRequest;
import com.lcm.utils.dada.SendAddAfterQuery;
import com.lcm.utils.dada.SendQueryDeliverFee;
import com.lcm.utils.file.DateUtils;
import com.lcm.utils.file.LocationUtils;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.request.UserOrderBean;
import com.lcm.utils.request.WxUserInfoBean;
import com.lcm.utils.response.CollectListResponse;
import com.lcm.utils.response.GoodsCartBean;
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.OrderSendInfoResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserCartToOrderBean;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23DadaStoreListResponse;
import com.lcm.utils.response.Y23UserDetailResponse;
import com.lcm.utils.response.Y23UserOrderConfirmResponse;
import com.lcm.utils.response.Y23UserOrderDetailResponse;
import com.lcm.utils.response.Y23UserOrdersListResponse;
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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@Controller(value="bY23UserOrdersController")
@RequestMapping("/y23/api/user/order")
public class Y23UserOrdersController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23UserOrdersController.class);  
	
	//正式
	private static String APPID = "wx86949d4fa08d57fc";
	private static String APPSECRET = "8d7111083ee13cdeab7d22e57492ce13";
	
	@Autowired
	private Y23UserService y23UserService;
	@Autowired
	private Y23UserOrdersService y23UserOrdersService;
	@Autowired
	private Y23UserOrderitemService y23UserOrderitemService;
	@Autowired
	private Y23UserCartService y23UserCartService;
	@Autowired
	private Y23UserAddressService y23UserAddressService;
	@Autowired
	private Y23GoodsPropertyService y23GoodsPropertyService;
	@Autowired
	private Y23UserShoppingService y23UserShoppingService;

	@Autowired
	private Y23PostageTempleteService y23PostageTempleteService;
	@Autowired
	private Y23PostageAreaService y23PostageAreaService;
	@Autowired
	private Y23PostagePieceService y23PostagePieceService;
	@Autowired
	private Y23UserPayinfoService y23UserPayinfoService;
	@Autowired
	private Y23UserCouponService y23UserCouponService;
	@Autowired
	private Y23CouponGoodsService y23CouponGoodsService;
	@Autowired
	private Y23CouponService y23CouponService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23SystemParamService y23SystemParamService;
	@Autowired
	private Y23UserCommissionService y23UserCommissionService;
	@Autowired
	private Y23DadaStoreService y23DadaStoreService;
	@Autowired
	private Y23StoreService y23StoreService;
	@Autowired
	private Y23CategoriesService y23CategoriesService;
	
	@ResponseBody
	@RequestMapping("/confirm")
	public ResponseInfo confirm(HttpServletRequest request,Model model,@RequestParam String cartIds, 
			@RequestParam Long userId) {
		Y23UserAddress defaultAddr = new Y23UserAddress();
		if(userId != null) {
			Map param = new HashMap();
			param.put("userId", userId);
			param.put("orderBy", " a.isDefault desc,a.create_time desc");
			List<Y23UserAddress> list = y23UserAddressService.getList(param);
			for (Y23UserAddress address:list) {
				defaultAddr = address;
				break;
			}
		} 
		
		String[] cartArr = cartIds.split(",");
		List<Y23UserCart> userCarts = new LinkedList<Y23UserCart>();
		Double totalPrice = 0.0;
		
		for (int i = 0; i < cartArr.length; i++) {
			Y23UserCart y23UserCart = y23UserCartService.getDetail(Long.parseLong(cartArr[i]));
			StringBuffer sb = new StringBuffer();
			String[] arr = y23UserCart.getProps().split(",");
			for (int j = 0; j < arr.length; j++) {
				Y23GoodsProperty y23GoodsProperty = y23GoodsPropertyService.getDetail(Long.parseLong(arr[j]));
				sb.append(y23GoodsProperty.getValueTitle() + ",");
				y23UserCart.setPrice(y23GoodsProperty.getPrice());
			}
			String propsStr = sb.toString();
			if(propsStr.endsWith(",")) {
				propsStr = propsStr.substring(0,propsStr.length() - 1);
			}
			y23UserCart.setCover(PropertiesUtil.getValue("SYS_PATH") + y23UserCart.getCover());
			y23UserCart.setPropsStr(propsStr);
			userCarts.add(y23UserCart);
			
			totalPrice = totalPrice +  y23UserCart.getPrice()*y23UserCart.getGoodsNum();
		}
		
		Map param = new HashMap();
		param.put("status", 1);
		param.put("userId", userId);
		param.put("orderBy", " a.createTime desc");
		List<Y23UserCoupon> y23UserCoupons = y23UserCouponService.getList(param);
		
		//去除不满足消费门槛
		List<Y23UserCoupon> userCouponList1 = new LinkedList<Y23UserCoupon>();
		for (Y23UserCoupon y23UserCoupon:y23UserCoupons) {
			if(y23UserCoupon.getUserequire() <= totalPrice) {
				userCouponList1.add(y23UserCoupon);
			}
		}
		
		//去除不在使用范围内
		List<Y23UserCoupon> userCouponList = new LinkedList<Y23UserCoupon>();
		for (Y23UserCoupon y23UserCoupon:userCouponList1) {
			if(y23UserCoupon.getScope() == 2) {
				Map param1 = new HashMap();
				param1.put("couponId", y23UserCoupon.getCouponId());
				List<Y23CouponGoods> y23CouponGoodsList = y23CouponGoodsService.getList(param1);
				for (Y23CouponGoods y23CouponGoods:y23CouponGoodsList) {
					for (Y23UserCart y23UserCart:userCarts) {
						if(y23CouponGoods.getGoodsId() == y23UserCart.getGoodsId()) {
							userCouponList.add(y23UserCoupon);
							break;
						}
					}
				}
			} else {
				userCouponList.add(y23UserCoupon);
			}
		}
		
		
		Y23UserOrderConfirmResponse responseInfo = new Y23UserOrderConfirmResponse(ResCode.SUCCESS, "SUCCESS", userCarts);
		responseInfo.setUserAddress(defaultAddr);
		responseInfo.setTotalPrice(totalPrice);
		responseInfo.setCartIds(cartIds);
		responseInfo.setUserCouponList(userCouponList);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/postage")
	public ResponseInfo postage(HttpServletRequest request,Model model,@RequestParam Long addressId,
			@RequestParam String cartIds,
			@RequestParam Long userId) {
		Double postage = 0.0;
		Double amount = 0.0;
		
		Y23SystemParam baoyou = y23SystemParamService.getY23SystemParamDetail("baoyou");
		Double baoyouDou = Double.parseDouble(baoyou.getValue());
		
		String[] cartArr = cartIds.split(",");
		for (int i = 0; i < cartArr.length; i++) {
			Y23UserCart y23UserCart = y23UserCartService.getDetail(Long.parseLong(cartArr[i]));
			amount += y23UserCart.getGoodsNum() * y23UserCart.getPrice();
		}
		
		if(amount < baoyouDou) {
			Y23SystemParam peisong = y23SystemParamService.getY23SystemParamDetail("peisong");
			Double peisongDou = Double.parseDouble(peisong.getValue());
			postage = peisongDou;
		}
		
		BigDecimal bg = new BigDecimal(postage);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new ResponseInfo(ResCode.SUCCESS, f1 + "");
	}
	
	@ResponseBody
	@RequestMapping("/topay")
	public ResponseInfo topay(HttpServletRequest request,Model model,@ModelAttribute UserOrderBean bean, 
			@RequestParam Long userCouponId,
			@RequestParam Long userId) {
		
		String[] cartArr = bean.getCartIds().split(",");
		List<Y23UserCart> userCarts = new LinkedList<Y23UserCart>();
		Double totalPrice = 0.0;
		
		Long storeId = null;
		for (int i = 0; i < cartArr.length; i++) {
			Y23UserCart y23UserCart = y23UserCartService.getDetail(Long.parseLong(cartArr[i]));
			if(i == 0) storeId = y23UserCart.getStoreId();
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			String[] arr = y23UserCart.getProps().split(",");
			for (int j = 0; j < arr.length; j++) {
				Y23GoodsProperty y23GoodsProperty = y23GoodsPropertyService.getDetail(Long.parseLong(arr[j]));
				sb.append(y23GoodsProperty.getValueTitle() + ",");
				sb2.append(y23GoodsProperty.getEnValueTitle() + ",");
			}
			String propsStr = sb.toString();
			if(propsStr.endsWith(",")) {
				propsStr = propsStr.substring(0,propsStr.length() - 1);
			}
			y23UserCart.setPropsStr(propsStr);
			
			String propsStr2 = sb2.toString();
			if(propsStr2.endsWith(",")) {
				propsStr2 = propsStr2.substring(0,propsStr2.length() - 1);
			}
			y23UserCart.setEnPropsStr(propsStr2);
			userCarts.add(y23UserCart);
			
			totalPrice = totalPrice +  y23UserCart.getPrice()*y23UserCart.getGoodsNum();
		}
		
		//超出派送距离则提示
		Y23UserAddress y23UserAddress = y23UserAddressService.getDetail(bean.getAddressId());
		Y23Store y23Store = y23StoreService.getDetail(storeId);
		Double distance = LocationUtils.getDistance(y23UserAddress.getLatitude(), y23UserAddress.getLongitude(), y23Store.getLatitude(), y23Store.getLongitude());
		if(distance > y23Store.getDistanceFormat()) {
			return new ResponseInfo(ResCode.FAIL, "您的地址已超出该门店的配送距离！");
		}
		
		double f1 = 0;
		Y23UserCoupon y23UserCoupon = new Y23UserCoupon();
		if(userCouponId != null) {
			y23UserCoupon = y23UserCouponService.getDetail(userCouponId);
			BigDecimal bg = new BigDecimal(0);
			if(y23UserCoupon.getType() == 1) {
				bg = new BigDecimal(y23UserCoupon.getFace());
			} else {
				bg = new BigDecimal((1 - y23UserCoupon.getFace()) * totalPrice);
			}
			
			f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		
		totalPrice = totalPrice + bean.getPostage() - f1;
		
		//判断是否有库存量
		for (Y23UserCart y23UserCart:userCarts) {
			//扣除商品库存量
			Y23Goods y23Goods = y23GoodsService.getDetail(y23UserCart.getGoodsId());
			int num = y23Goods.getStoreNum() - y23UserCart.getGoodsNum();
			if(num < 0) {
				return new ResponseInfo(ResCode.FAIL, "商品库存量不足，请谅解！");
			}
		}
		
		//订单佣金
		Double orderCommission = 0.0;
		
		//创建订单
		Y23UserOrders y23UserOrders = new Y23UserOrders();
		y23UserOrders.setUserId(userId);
		y23UserOrders.setOrderno(createNewBidNumber());
		y23UserOrders.setStoreId(storeId);
		y23UserOrders.setPayment(totalPrice);
		y23UserOrders.setPaymenttype(1);
		y23UserOrders.setPostage(bean.getPostage());
		y23UserOrders.setStatus(10);
		if(userCouponId != null) {
			y23UserOrders.setUserCouponId(y23UserCoupon.getId());
			y23UserOrders.setCouponId(y23UserCoupon.getCouponId());
		} 
		y23UserOrders.setReduceAmount(f1);
		y23UserOrders.setRemark(bean.getRemark());
		y23UserOrdersService.add(y23UserOrders);
		
		if(userCouponId != null) {
			y23UserCoupon.setStatus(2);
			y23UserCouponService.update(y23UserCoupon);
		}
		
		
		//存储订单收货信息
		Y23UserShopping y23UserShopping = new Y23UserShopping();
		y23UserShopping.setUserId(userId);
		y23UserShopping.setOrderId(y23UserOrders.getId());
		y23UserShopping.setReceivername(y23UserAddress.getName());
		y23UserShopping.setReceivermobile(y23UserAddress.getPhone());
		y23UserShopping.setReceiverarea(y23UserAddress.getArea());
		y23UserShopping.setReceiveraddress(y23UserAddress.getAddress());
		y23UserShopping.setLatitude(y23UserAddress.getLatitude());
		y23UserShopping.setLongitude(y23UserAddress.getLongitude());
		y23UserShopping.setHouseno(y23UserAddress.getHouseno());
		y23UserShoppingService.add(y23UserShopping);
		
		Double orderWeight = 0.0;
		
		//存储订单商品信息
		for (Y23UserCart y23UserCart:userCarts) {
			Y23UserOrderitem y23UserOrderitem = new Y23UserOrderitem();
			y23UserOrderitem.setOrderId(y23UserOrders.getId());
			y23UserOrderitem.setUserId(userId);
			y23UserOrderitem.setGoodsId(y23UserCart.getGoodsId());
			y23UserOrderitem.setGoodsName(y23UserCart.getGoodsName());
			y23UserOrderitem.setCover(y23UserCart.getCover());
			y23UserOrderitem.setCurrentunitprice(y23UserCart.getPrice());
			y23UserOrderitem.setQuantity(y23UserCart.getGoodsNum());
			y23UserOrderitem.setPrice(y23UserCart.getPrice());
			y23UserOrderitem.setTotalprice(y23UserCart.getPrice() * y23UserCart.getGoodsNum());
			y23UserOrderitem.setProps(y23UserCart.getProps());
			y23UserOrderitem.setPropsStr(y23UserCart.getPropsStr());
			y23UserOrderitem.setEnPropsStr(y23UserCart.getEnPropsStr());
			
			Y23Goods y23Goods = y23GoodsService.getDetail(y23UserCart.getGoodsId());
			Double goodsCommission = 0.0;
			
			y23UserOrderitem.setCommission(goodsCommission);
			orderWeight = orderWeight + y23Goods.getWeight() * y23UserCart.getGoodsNum();
			y23UserOrderitem.setWeight(y23Goods.getWeight() * y23UserCart.getGoodsNum());
			y23UserOrderitemService.add(y23UserOrderitem);
			
			int num = y23Goods.getStoreNum() - y23UserCart.getGoodsNum();
			y23Goods.setStoreNum(num);
			y23GoodsService.update(y23Goods);
			
			//删除购物车相关信息
			y23UserCart.setIsDelete(true);
			y23UserCartService.update(y23UserCart);
			
		}
		
		y23UserOrders.setWeight(orderWeight);
		y23UserOrders.setCommission(orderCommission);
		y23UserOrdersService.update(y23UserOrders);
		
		
		return new ResponseInfo(ResCode.SUCCESS, y23UserOrders.getId() + "");
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseInfo list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		
		Map param = searchParam.getSearchMap();
		if(searchParam.getStatus().equals("99")) {
			param.put("status", null);
		}
		
		param.put("userId", userId);
		param.put("orderBy", " a.create_time desc");
		List<Y23UserOrders> list = y23UserOrdersService.getList(param);
		Map<Long, List<Y23UserOrderitem>> map = new LinkedHashMap<Long, List<Y23UserOrderitem>>();
		for (Y23UserOrders y23UserOrders:list) {
			Map param2 = new HashMap();
			param2.put("orderId", y23UserOrders.getId());
			List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param2);
			for (int i = 0; i < itemList.size(); i++) {
				itemList.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + itemList.get(i).getCover());
			}
			y23UserOrders.setTotalNum(itemList.size());
			map.put(y23UserOrders.getId(), itemList);
		}
		
		Y23UserOrdersListResponse responseInfo = new Y23UserOrdersListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setMap(map);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/alist")
	public ResponseInfo alist(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		if(!y23User.getIsAdmin()) return new ResponseInfo(ResCode.FAIL, "您不是管理员，无法进行操作！");
		Map param = searchParam.getSearchMap();
		param.put("orderBy", " a.create_time desc");
		List<Y23UserOrders> list = y23UserOrdersService.getList(param);
		Map<Long, List<Y23UserOrderitem>> map = new LinkedHashMap<Long, List<Y23UserOrderitem>>();
		for (Y23UserOrders y23UserOrders:list) {
			Map param2 = new HashMap();
			param2.put("orderId", y23UserOrders.getId());
			List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param2);
			for (int i = 0; i < itemList.size(); i++) {
				itemList.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + itemList.get(i).getCover());
			}
			y23UserOrders.setTotalNum(itemList.size());
			map.put(y23UserOrders.getId(), itemList);
		}
		
		Y23UserOrdersListResponse responseInfo = new Y23UserOrdersListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setMap(map);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/dada/list")
	public ResponseInfo dadaList(HttpServletRequest request,Model model, @RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		if(!y23User.getIsAdmin()) return new ResponseInfo(ResCode.FAIL, "您不是管理员，无法进行操作！");
		
		Map param = new HashMap();
		param.put("orderBy", " a.createTime desc");
		List<Y23DadaStore> list = y23DadaStoreService.getList(param);
		
		Y23DadaStoreListResponse responseInfo = new Y23DadaStoreListResponse(ResCode.SUCCESS, "SUCCESS", list);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/send/done")
	public ResponseInfo sendDone(HttpServletRequest request,Model model, 
			@RequestParam Long orderId,
			@RequestParam String express,
			@RequestParam(required=false) String expressnum
			) {
		Y23UserShopping entity = y23UserShoppingService.getDetailByOrderId(orderId);
		entity.setExpress(express);
		entity.setExpressnum(expressnum);
		y23UserShoppingService.update(entity);
		
		//更新订单状态
		Y23UserOrders order = y23UserOrdersService.getDetail(entity.getOrderId());
		
		if(entity.getExpress().equals("dada")) { //达达配送则发起达达派送下单
			Y23UserShopping y23UserShopping = y23UserShoppingService.getDetail(entity.getId());
			
			Map<String,String> ddparams = getDaDaParams();
			QueryDeliverFee queryDeliverFee = new QueryDeliverFee();
			queryDeliverFee.setShop_no(y23UserShopping.getExpressnum());
			queryDeliverFee.setOrigin_id(order.getOrderno());
			queryDeliverFee.setCargo_price(order.getPayment());
			queryDeliverFee.setReceiver_name(y23UserShopping.getReceivername());
			String receiveraddress = y23UserShopping.getReceiveraddress();
			if(!StringUtils.isEmpty(y23UserShopping.getHouseno())) {
				receiveraddress += y23UserShopping.getHouseno();
			}
			queryDeliverFee.setReceiver_address(receiveraddress);
			queryDeliverFee.setReceiver_lat(y23UserShopping.getLatitude());
			queryDeliverFee.setReceiver_lng(y23UserShopping.getLongitude());
			queryDeliverFee.setCallback("wanjia.yifan668.com");
			queryDeliverFee.setCargo_weight(order.getWeight());
			queryDeliverFee.setReceiver_phone(y23UserShopping.getReceivermobile());
			queryDeliverFee.setInfo(order.getRemark());
			JSONObject result = SendQueryDeliverFee.sendRequest(queryDeliverFee,ddparams);
			System.out.println("===result====" + result.toJSONString());
			QueryDeliverFeeResult queryDeliverFeeResult = JSON.parseObject(result.toJSONString(), QueryDeliverFeeResult.class);
			if(queryDeliverFeeResult.getCode() == 0) {
				AddAfterQuery addAfterQuery = new AddAfterQuery();
				addAfterQuery.setDeliveryNo(queryDeliverFeeResult.getResult().getDeliveryNo());
				JSONObject result2 = SendAddAfterQuery.sendRequest(addAfterQuery,ddparams);
				System.out.println("===result2====" + result2.toJSONString());
				AddAfterQueryResult addAfterQueryResult = JSON.parseObject(result2.toJSONString(), AddAfterQueryResult.class);
				if(addAfterQueryResult.getCode() == 0) {
					//记录下达达配送的信息
					order.setDistance(queryDeliverFeeResult.getResult().getDistance());
					order.setFee(queryDeliverFeeResult.getResult().getFee());
					order.setDeliverFee(queryDeliverFeeResult.getResult().getDeliverFee());
					order.setCouponFee(queryDeliverFeeResult.getResult().getCouponFee());
					order.setInsuranceFee(queryDeliverFeeResult.getResult().getInsuranceFee());
					order.setTips(queryDeliverFeeResult.getResult().getTips());
				}
			}
		}
		
		order.setSendtime(new Date());
		order.setStatus(40);
		y23UserOrdersService.update(order);
		return new ResponseInfo(ResCode.SUCCESS, "发货成功！");
	}
	
	@ResponseBody
	@RequestMapping("/detail/{id}")
	public ResponseInfo detail(HttpServletRequest request,Model model,@PathVariable Long id, 
			@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		Y23UserOrders entity = y23UserOrdersService.getDetail(id);
		if(userId.longValue() != entity.getUserId().longValue() && !y23User.getIsAdmin()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		Map param = new HashMap();
		param.put("orderId", id);
		param.put("isDelete", "isDelete");
		List<Y23UserShopping> userShoppings = y23UserShoppingService.getList(param);
		
		Map param2 = new HashMap();
		param2.put("orderId", id);
		List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param2);
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + itemList.get(i).getCover());
		}
		entity.setTotalNum(itemList.size());
		Y23UserOrderDetailResponse responseInfo = new Y23UserOrderDetailResponse(ResCode.SUCCESS, "SUCCESS", entity);
		if(userShoppings.size() > 0) {
			responseInfo.setUserShopping(userShoppings.get(0));
		}
		
		responseInfo.setUserOrderitems(itemList);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/pay/detail")
	public ResponseInfo payDetail(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam Long userId) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		Y23UserOrderDetailResponse responseInfo = new Y23UserOrderDetailResponse(ResCode.SUCCESS, "SUCCESS", entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/confirm/goods")
	public ResponseInfo confirmGoods(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		if(entity.getStatus().intValue() != 40) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		entity.setStatus(50);
		ResponseInfo responseInfo =  y23UserOrdersService.update(entity);
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/cancel")
	public ResponseInfo cancel(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam Long userId) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
	
		entity.setStatus(0);
		entity.setCancelTime(new Date());

		ResponseInfo responseInfo =  y23UserOrdersService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/refund")
	public ResponseInfo refund(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam String content,
			@RequestParam Long userId) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		
		entity.setStatus(-1);
		entity.setCancelDesc(content);
		entity.setCancelTime(new Date());

		ResponseInfo responseInfo =  y23UserOrdersService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/pay")
	public ResponseInfo pay(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam Long userId) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		if(userId.longValue() != entity.getUserId().longValue()) {
			return new ResponseInfo(ResCode.FAIL, "非法操作");
		}
		return new ResponseInfo(ResCode.SUCCESS, "");
	}
	
	@ResponseBody
	@RequestMapping("/getSendInfo")
	public ResponseInfo getSendInfo(HttpServletRequest request,Model model,@RequestParam Long orderId, 
			@RequestParam Long userId) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		
		Y23UserShopping y23UserShopping = y23UserShoppingService.getDetail(entity.getId());
		Map param = new HashMap();
		param.put("no", y23UserShopping.getExpressnum());
		List<Y23DadaStore> storeList = y23DadaStoreService.getList(param);
		
		QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
		queryOrderRequest.setOrder_id(entity.getOrderno());
		JSONObject result = QueryOrder.sendRequest(queryOrderRequest,getDaDaParams());
		OrderDetailResult orderDetailResult = JSON.parseObject(result.toJSONString(), OrderDetailResult.class);
		OrderDetail orderDetail = orderDetailResult.getResult();
		if(orderDetail == null) orderDetail = new OrderDetail();
		OrderSendInfoResponse responseInfo = new OrderSendInfoResponse(ResCode.SUCCESS, "SUCCESS", orderDetail);
		if(storeList.size() > 0) {
			responseInfo.setStore(storeList.get(0));
		}
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/gddetail")
	public ResponseInfo gddetail(HttpServletRequest request,Model model,@RequestParam Long userId) {
		Y23User y23User = y23UserService.getDetail(userId);
		if(!y23User.getIsAdmin()) return new ResponseInfo(ResCode.FAIL, "您不是管理员，无法进行操作！");
		
		Map param = new HashMap();
		param.put("isPrint", "0");
		param.put("status", "20");
		param.put("page", 0);
		param.put("pageSize", 1);
		param.put("orderBy", " a.modify_time asc");
		List<Y23UserOrders> y23UserOrdersList = y23UserOrdersService.getList(param);
		
		if(y23UserOrdersList.size() > 0) {
			Y23UserOrders entity = y23UserOrdersList.get(0);
			Map param2 = new HashMap();
			param2.put("orderId", entity.getId());
			List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param2);
			
			Map<String,List<Y23UserOrderitem>> itemMap = new HashMap<String, List<Y23UserOrderitem>>();
			for (int i = 0; i < itemList.size(); i++) {
				List<Y23UserOrderitem> list = new ArrayList<Y23UserOrderitem>();
				Y23Goods y23Goods = y23GoodsService.getDetail(itemList.get(i).getGoodsId());
				if(itemMap.containsKey(y23Goods.getCateName())) {
					list = itemMap.get(y23Goods.getCateName());
				}
				
				list.add(itemList.get(i));
				itemMap.put(y23Goods.getCateName(), list);
			}
			
			Map sparam = new HashMap();
			sparam.put("orderId", entity.getId());
			List<Y23UserShopping> userShoppings = y23UserShoppingService.getList(sparam);
			
			Y23Store y23Store = y23StoreService.getDetail(entity.getStoreId());
			entity.setTotalNum(itemList.size());
			Y23UserOrderDetailResponse responseInfo = new Y23UserOrderDetailResponse(ResCode.SUCCESS, "SUCCESS", entity);
			responseInfo.setUserOrderitems(itemList);
			responseInfo.setNowTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
			responseInfo.setStore(y23Store);
			responseInfo.setItemMap(itemMap);
			responseInfo.setUserShopping(userShoppings.get(0));
			return responseInfo;
		} else {
			return new ResponseInfo(ResCode.FAIL, "没有需要打印的订单！");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/updatePrint")
	public ResponseInfo updatePrint(HttpServletRequest request,Model model,@RequestParam Long userId,
			@RequestParam Long orderId) {
		Y23User y23User = y23UserService.getDetail(userId);
		if(!y23User.getIsAdmin()) return new ResponseInfo(ResCode.FAIL, "您不是管理员，无法进行操作！");
		
		Y23UserOrders entity = y23UserOrdersService.getDetail(orderId);
		
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改打印状态完成！");
		entity.setIsPrint(true);
		y23UserOrdersService.update(entity);
		
		return responseInfo;
	}
	
	private  String createNewBidNumber() {
        //格式说明 CODE20201111xxx CODE+当前年月日+编号（具体长度看需求）
        String front="WJXH";//前缀
        //当前时间编码
        Date date = new Date();
        String bidDate = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        Map param = new HashMap();
    	param.put("startTime", new SimpleDateFormat("yyyy-MM-dd").format(date));
    	param.put("endTime", new SimpleDateFormat("yyyy-MM-dd").format(date));
    	int num = y23UserOrdersService.getCount(param);
        num ++;  // 加1
        if(num<10){
            String bidNum = String.format("%03d", num);//%03d 只是三位，不足补0
            String code = front+bidDate+bidNum;
            return code;
        }
        else if(num<100){
            String bidNum = String.format("%03d", num);//num<100,说明是两位数，前面要补一个0
            String code = front+bidDate+bidNum;
            return code;
        }
        else {
            String bidNum = String.valueOf(num);
            String code =front+bidDate+bidNum;
            return code;
        }
    }
}
