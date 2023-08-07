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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserCashout;
import com.lcm.entity.pojo.Y23UserCommission;
import com.lcm.entity.pojo.Y23UserViews;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23UserCashoutService;
import com.lcm.service.business.Y23UserCommissionService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserViewsService;
import com.lcm.service.business.Y23UserService;
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
import com.lcm.utils.response.OpenIdResponse;
import com.lcm.utils.response.OrderNumResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.UserListResponse;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.response.UserViewsListResponse;
import com.lcm.utils.response.Y23UserDetailResponse;
import com.lcm.utils.search.SearchParam;
import com.lcm.utils.sms.DXBSmsUtils;
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

@Controller(value="bY23UserController")
@RequestMapping("/y23/api/user")
public class Y23UserController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(Y23UserController.class);  
	
	//正式
	private static String APPID = "wx86949d4fa08d57fc";
	private static String APPSECRET = "8d7111083ee13cdeab7d22e57492ce13";
	
	@Autowired
	private Y23UserService y23UserService;
	@Autowired
	private Y23GoodsCollectService y23GoodsCollectService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	@Autowired
	private Y23UserOrdersService y23UserOrdersService;
	@Autowired
	private Y23UserViewsService y23UserViewsService;
	@Autowired
	private Y23UserCommissionService y23UserCommissionService;
	
	@ResponseBody
	@RequestMapping("/getUserInfo")
	public ResponseInfo getUserInfo(HttpServletRequest request,Model model,
			@RequestParam Long userId,
			@RequestParam(required=false) Integer isNotStat
			) {
		Y23User y23User = y23UserService.getDetail(userId);
		Double fxMoney = y23User.getMoney();
		Double txzMoney = 0.00;
		Double ytzMoney = 0.00;
		Double todayMoney = 0.00;
		Double saleMoney = 0.00;
		Double shareMoney = 0.00;
		Integer myTeamNum = 0;
		Integer orderNum = 0;
		Integer cashoutNum = 0;
		Integer shareNum = 0;
		
		y23User.setFxMoney(fxMoney);
		y23User.setTxzMoney(txzMoney);
		y23User.setYtzMoney(ytzMoney);
		y23User.setTodayMoney(todayMoney);
		y23User.setSaleMoney(saleMoney);
		y23User.setCashoutNum(cashoutNum);
		y23User.setMyTeamNum(myTeamNum);
		y23User.setOrderNum(orderNum);
		y23User.setShareMoney(shareMoney);
		y23User.setShareNum(shareNum);
		
		Y23UserDetailResponse responseInfo = new Y23UserDetailResponse(ResCode.SUCCESS, "SUCCESS",y23User);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/getOrderNum")
	public ResponseInfo getOrderNum(HttpServletRequest request,Model model, 
			@RequestParam Long userId,@RequestParam(required=false) String language) {
		Integer orderStatus10 = 0;
		Integer orderStatus20 = 0;
		Integer orderStatus40 = 0;
		Integer orderStatus50 = 0;
		Integer orderNum = 0;
		
		if(!StringUtils.isEmpty(userId)) {
			Map param = new HashMap();
			param.put("status", 10);
			param.put("userId", userId);
			orderStatus10 = y23UserOrdersService.getCount(param);
			
			param.put("status", 20);
			orderStatus20 = y23UserOrdersService.getCount(param);
			
			param.put("status", 40);
			orderStatus40 = y23UserOrdersService.getCount(param);
			
			param.put("status", 50);
			orderStatus50 = y23UserOrdersService.getCount(param);
			
			Map param2 = new HashMap();
			param2.put("userId", userId);
			orderNum = y23UserOrdersService.getCount(param2);
		}
		
		OrderNumResponse responseInfo = new OrderNumResponse(ResCode.SUCCESS, "");
		responseInfo.setOrderStatus10(orderStatus10);
		responseInfo.setOrderStatus20(orderStatus20);
		responseInfo.setOrderStatus40(orderStatus40);
		responseInfo.setOrderStatus50(orderStatus50);
		responseInfo.setOrderNum(orderNum);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/sendYzCode")
	public ResponseInfo sendYzCode(HttpServletRequest request,Model model, @RequestParam Long phone) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "");
		String code = RandomUtil.generateNumber(6);
		System.out.println("====code======" + code);
		
		//发送短信信息
		String username = "linchengmin27"; //在短信宝注册的用户名
		String password = "6343efc5fc27467b85fa5421007fa5cf"; //在短信宝注册的密码
		String content = "【万家鲜惠】您的验证码是{code}。如非本人操作，请忽略本短信";
		content = content.replace("{code}",  code);
		String httpUrl = "http://api.smsbao.com/sms";
		System.out.println("-------------" + content);
		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(username).append("&");
		httpArg.append("p=").append(DXBSmsUtils.md5(password)).append("&");
		httpArg.append("m=").append(phone).append("&");
		httpArg.append("c=").append(DXBSmsUtils.encodeUrlString(content, "UTF-8"));
		DXBSmsUtils.request(httpUrl, httpArg.toString());
		
		responseInfo.setResDes(code);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public ResponseInfo login(HttpServletRequest request,Model model, @RequestParam String phone) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "");
		
		Y23User y23User = y23UserService.getDetailByUsername(phone);
		if(y23User == null) { //没有账号就创建
			Y23User entity = new Y23User();
			entity.setUsername(phone);
			entity.setCover(PropertiesUtil.getValue("SYS_PATH") + "images/default-image.png");
			entity.setPwd("123456");
			entity.setName("注册用户");
			entity.setNickName("注册用户");
			entity.setPhone(phone);
			entity.setIsAdmin(false);
			responseInfo = y23UserService.add(entity);
			if(responseInfo.getResCode() == 1)responseInfo.setResDes(entity.getId() + "");
		} else { 
			responseInfo.setResDes(y23User.getId() + "");
		}
		System.out.println(responseInfo.getResCode() + "============" + responseInfo.getResDes());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/saveInfo")
	public ResponseInfo saveInfo(HttpServletRequest request,Model model, @RequestParam Long userId,
			@RequestParam(required=false) String name,
			@RequestParam String nickName,
			@RequestParam String cover
			
			) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "");
		Y23User entity = y23UserService.getDetail(userId);
		if(!StringUtils.isEmpty(name)) entity.setName(name);
		if(!StringUtils.isEmpty(nickName)) entity.setNickName(nickName);
		if(!StringUtils.isEmpty(cover)) entity.setCover(cover);
		responseInfo = y23UserService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/collect/list")
	public ResponseInfo getCollect(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			@RequestParam Long userId) {
		Map param = searchParam.getSearchMap();
		param.put("userId", userId);
		param.put("orderBy", " create_time desc");
		List<Y23GoodsCollect> list = y23GoodsCollectService.getList(param);
		for (int i = 0; i < list.size(); i++) {
			Y23Goods y23Goods = y23GoodsService.getDetail(list.get(i).getGoodsId());
			list.get(i).setGoodsName(y23Goods.getGoodsName());
			list.get(i).setEnGoodsName(y23Goods.getEnGoodName());
			list.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + y23Goods.getCover());
			list.get(i).setEnCover(y23Goods.getEnCover());
			list.get(i).setPrice(y23Goods.getPrice());
		}
		
		CollectListResponse responseInfo = new CollectListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/view/list")
	public ResponseInfo getViewList(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam,
			Long userId) {
		Map param = searchParam.getSearchMap();
		param.put("userId", userId);
		param.put("orderBy", " createTime desc");
		List<Y23UserViews> list = y23UserViewsService.getList(param);
		for (int i = 0; i < list.size(); i++) {
			Y23Goods y23Goods = y23GoodsService.getDetail(list.get(i).getGoodsId());
			list.get(i).setGoodsName(y23Goods.getGoodsName());
			list.get(i).setEnGoodsName(y23Goods.getEnGoodName());
			list.get(i).setCover(PropertiesUtil.getValue("SYS_PATH") + y23Goods.getCover());
			list.get(i).setEnCover(y23Goods.getEnCover());
			list.get(i).setPrice(y23Goods.getPrice());
		}
		
		UserViewsListResponse responseInfo = new UserViewsListResponse(ResCode.SUCCESS, "SUCCESS", list);
		responseInfo.setPage(searchParam.getPage() + 1);
		responseInfo.setPageSize(searchParam.getPageSize());
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/getQRCode")
	public ResponseInfo getQRCode(HttpServletRequest request,Model model,@RequestParam Long userId,
			@RequestParam String page, 
			@RequestParam(required=false) String scene
			) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());
		String newFileName = format + ".png";
		String path = "upload/" + newFileName;
		String filePath = request.getRealPath("/") + path;
		Boolean flag = getWXCode(filePath, page, scene);
		if(flag) {
			String fileUrl = PropertiesUtil.getValue("SYS_PATH") + path;
			System.out.println("=======page=====" + page);
			System.out.println("=======scene=====" + scene);
			return new ResponseInfo(ResCode.SUCCESS, fileUrl);
		} else {
			return new ResponseInfo(ResCode.FAIL, "");
		}
		
	}
	
	
	/**
	 * 获取小程序菊花码
	 *parm：machineNo    二维码想携带的参数
	 **/
	public Boolean getWXCode(String filePath, String page, String scene) throws Exception {
		Boolean flag = true;
		try {
			//这里调用的是上面的获取access_token方法
			String access_token = WeixinUtil.getAccessToken(APPID, APPSECRET).getToken();

			String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
			Map<String, String> param = new HashMap<String, String>();
			param.put("scene", scene);
			param.put("page", page);
			String json = JSON.toJSONString(param);
			ByteArrayInputStream inputStream = sendPost(url, json);
			//这里判断的是返回的图片还是错误信息，一般错误信息不会大于200
			if (inputStream.available() <= 200) {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int i;
				byte[] buffer = new byte[200];
				while ((i = inputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, i);
				}
				String str = new String(byteArrayOutputStream.toByteArray());
				//错误信息的格式在官方文档里有
				JSONObject jsonObject = JSONObject.parseObject(str);
				if ("41030".equals(jsonObject.getString("errcode"))) {
					log.error("所传page页面不存在，或者小程序没有发布");
					flag = false;
					throw new Exception("所传page页面不存在，或者小程序没有发布");
				} else if ("45009".equals(jsonObject.getString("errcode"))) {
					log.error("调用分钟频率受限");
					flag = false;
					throw new Exception("调用分钟频率受限");
				}
				byteArrayOutputStream.close();
			}

			//输出到本地的代码
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			int i;
			byte[] buffer = new byte[200];
			while ((i = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, i);
			}
			fileOutputStream.flush();
			fileOutputStream.close();

			inputStream.close();
			log.error("二维码生成成功");
		} catch (Exception e) {
			log.error("获取二维码异常");
			flag = false;
			throw new Exception(e.getMessage());
		}
		
		return flag;
	}
	
	 public static ByteArrayInputStream sendPost(String URL, String json) {
        InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(URL);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                // 创建一个Buffer字符串
                byte[] buffer = new byte[1024];
                // 每次读取的字符串长度，如果为-1，代表全部读取完毕
                int len = 0;
                // 使用一个输入流从buffer里把数据读取出来
                while ((len = inputStream.read(buffer)) != -1) {
                    // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                    outStream.write(buffer, 0, len);
                }
                // 关闭输入流
                inputStream.close();
                // 把outStream里的数据写入内存
                byteArrayInputStream = new ByteArrayInputStream(outStream.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayInputStream;
    }
}
