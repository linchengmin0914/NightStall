package com.lcm.controller.wx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jpay.ext.kit.HttpKit;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.weixin.api.WxPayApi;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;
import com.lcm.entity.pojo.Y23UserPayinfo;
import com.lcm.entity.pojo.Y23UserShopping;
import com.lcm.service.business.Y23UserOrderitemService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserPayinfoService;
import com.lcm.service.business.Y23UserService;
import com.lcm.service.business.Y23UserShoppingService;
import com.lcm.utils.file.MatrixToImageWriter;
import com.lcm.utils.file.PropertiesUtil;
import com.lcm.utils.file.RandomUtil;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.sms.DXBSmsUtils;
import com.lcm.utils.task.TaskListener;
import com.lcm.utils.wx.common.WeixinUtil;

@Controller
@RequestMapping("/api/wx")
public class WxPayController extends BaseController  {
	private final Logger logger = LoggerFactory.getLogger(WxPayController.class);  
	
	private static String APPID = "wx86949d4fa08d57fc";
	private static String APPSECRET = "8d7111083ee13cdeab7d22e57492ce13";
	private static String MCHID = "1596814181";
	private static String PartnerKey = "8d7111083ee13cdeab7d22e57492ce13";
	private static String NotifyUrl = "https://wanjia.yifan668.com/api/wx/notify.html";
	
	@Autowired
	private Y23UserOrdersService y23UserOrdersService;
	@Autowired
	private Y23UserPayinfoService y23UserPayinfoService;
	@Autowired
	private Y23UserService y23UserService;
	@Autowired
	private Y23UserOrderitemService y23UserOrderitemService;
	@Autowired
	private Y23UserShoppingService y23UserShoppingService;
	
	 /**
	 * 小程序微信支付的第一步,统一下单
	 */
	 @ResponseBody
	 @RequestMapping("/createUnifiedOrder")
	 public AjaxJson createUnifiedOrder(HttpServletRequest request,Model model,@RequestParam Long orderId,
			 @RequestParam String openId) {
	    AjaxJson aj = new AjaxJson();
	    aj.setSuccess(false);
	    if(StringUtils.isEmpty(orderId) || StringUtils.isEmpty(openId)) {
	    	aj.setMsg("支付失败,支付所需参数缺失");
	        return aj;
	    }
	    
	    
	    Y23UserOrders order = y23UserOrdersService.getDetail(orderId);
	    Map<String, String> mapBasic = new HashMap<String, String>();
	    mapBasic.put("payAmount", order.getPayment() + "");
	    mapBasic.put("orderNo", order.getOrderno());
	    
	    if (mapBasic == null) {
	        aj.setMsg("支付失败,暂时无法获取到您的订单数据,请稍后再试");
	        return aj;
	    }
	    
	    String return_msg = "统一订单失败";
		try {
		
	        //支付金额 **金额不能有小数点,单位是分!!**
	        BigDecimal price = new BigDecimal(mapBasic.get("payAmount").toString());
	        BigDecimal beishu = new BigDecimal("100");
	        BigDecimal priceFee = price.multiply(beishu);
	        //商家订单号
	        String orderNo = mapBasic.get("orderNo").toString();
	
	        //创建 时间戳
	        String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();
	        //生成32位随机数
	        UUID uuid = UUID.randomUUID();
	        String nonceStr = uuid.toString().replaceAll("-","");
	        //商品描述
	        String body = "“" + order.getOrderno() + "”-支付订单";
	        //创建hashmap(用户获得签名)
	        SortedMap<String, String> paraMap = new TreeMap<String, String>();
	        //设置请求参数(小程序ID)
	        paraMap.put("appid", APPID);
	        //设置请求参数(商户号)
	        paraMap.put("mch_id", MCHID);
	        //设置请求参数(随机字符串)
	        paraMap.put("nonce_str", nonceStr);
	        //设置请求参数(商品描述)
	        paraMap.put("body", body);
	        //设置请求参数(商户订单号)
	        paraMap.put("out_trade_no", orderNo);
	        //设置请求参数(总金额)
	        paraMap.put("total_fee", priceFee.toBigInteger().toString());
	        //设置请求参数(终端IP) 如果是springmvc,或者能获取到request的servlet,用下面这种
	        paraMap.put("spbill_create_ip", request.getRemoteAddr());
	        //设置请求参数(通知地址)
	        paraMap.put("notify_url",NotifyUrl);
	        //设置请求参数(交易类型)
	        paraMap.put("trade_type", String.valueOf(WxPayApi.TradeType.JSAPI));
	        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
	        paraMap.put("openid", openId);
	        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
	        String sign = PaymentKit.createSign(paraMap, PartnerKey);
	        paraMap.put("sign", sign);
	        //统一下单,向微信api发送数据
	        logger.info("微信小程序统一下单发送的数据: "+paraMap.toString());
	        String xmlResult = WxPayApi.pushOrder(false, paraMap);
	        logger.info("微信小程序统一下单接受返回的结果: "+xmlResult);
	        //转成xml
	        Map<String, String> map = PaymentKit.xmlToMap(xmlResult);
	        //返回状态码
	        String return_code = (String) map.get("return_code");
	        return_msg = return_msg+", "+ (String) map.get("return_msg");
	        logger.info("return_code: "+return_code);
	        //返回给小程序端需要的参数
	        Map<String, String> returnMap = new HashMap<String, String>();
	        if("SUCCESS".equals(return_code)){
	            //返回的预付单信息
	            returnMap.put("appId",APPID);
	            returnMap.put("nonceStr", nonceStr);
	            String prepay_id = (String) map.get("prepay_id");
	            returnMap.put("package", "prepay_id=" + prepay_id);
	            returnMap.put("signType","MD5");
	            //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
	            returnMap.put("timeStamp", timeStamp);
	            //拼接签名需要的参数
	            //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
	            String paySign = PaymentKit.createSign(returnMap, PartnerKey).toUpperCase();
	            returnMap.put("paySign", paySign);
	            
	            aj.setObj(returnMap);
	            aj.setMsg("操作成功");
	            aj.setSuccess(true);
	            return aj;
	        }else{
	            aj.setMsg(getMsgByCode(return_code));
	            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName()+">>>"+return_msg);
	        }
	    } catch (Exception e) {
	        logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() +"发生的异常是: ",e);
	        aj.setMsg("微信支付下单失败,请稍后再试");
	    }
	    return aj;
	}
	
	 
	 /**
	 * 小程序微信支付的第一步,统一下单
	 */
	 @ResponseBody
	 @RequestMapping("/createWebUnifiedOrder")
	 public AjaxJson createWebUnifiedOrder(HttpServletRequest request,Model model,@RequestParam Long orderId) {
	    AjaxJson aj = new AjaxJson();
	    aj.setSuccess(false);
	    
	    Y23UserOrders order = y23UserOrdersService.getDetail(orderId);
	    Map<String, String> mapBasic = new HashMap<String, String>();
	    mapBasic.put("payAmount", order.getPayment() + "");
	    mapBasic.put("orderNo", order.getOrderno());
	    
	    if (mapBasic == null) {
	        aj.setMsg("支付失败,暂时无法获取到您的订单数据,请稍后再试");
	        return aj;
	    }
	    
	    String return_msg = "统一订单失败";
		try {
		
	        //支付金额 **金额不能有小数点,单位是分!!**
	        BigDecimal price = new BigDecimal(mapBasic.get("payAmount").toString());
	        BigDecimal beishu = new BigDecimal("100");
	        BigDecimal priceFee = price.multiply(beishu);
	        //商家订单号
	        String orderNo = mapBasic.get("orderNo").toString();
	
	        //创建 时间戳
	        String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();
	        //生成32位随机数
	        UUID uuid = UUID.randomUUID();
	        String nonceStr = uuid.toString().replaceAll("-","");
	        //商品描述
	        String body = "“" + order.getOrderno() + "”-支付订单";
	        //创建hashmap(用户获得签名)
	        SortedMap<String, String> paraMap = new TreeMap<String, String>();
	        //设置请求参数(小程序ID)
	        paraMap.put("appid", APPID);
	        //设置请求参数(商户号)
	        paraMap.put("mch_id", MCHID);
	        //设置请求参数(随机字符串)
	        paraMap.put("nonce_str", nonceStr);
	        //设置请求参数(商品描述)
	        paraMap.put("body", body);
	        //设置请求参数(商户订单号)
	        paraMap.put("out_trade_no", orderNo);
	        //设置请求参数(总金额)
	        paraMap.put("total_fee", priceFee.toBigInteger().toString());
	        //设置请求参数(终端IP) 如果是springmvc,或者能获取到request的servlet,用下面这种
	        paraMap.put("spbill_create_ip", request.getRemoteAddr());
	        //设置请求参数(通知地址)
	        paraMap.put("notify_url",NotifyUrl);
	        //设置请求参数(交易类型)
	        paraMap.put("trade_type", String.valueOf(WxPayApi.TradeType.NATIVE));
	        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
	        String sign = PaymentKit.createSign(paraMap, PartnerKey);
	        paraMap.put("sign", sign);
	        //统一下单,向微信api发送数据
	        logger.info("微信小程序统一下单发送的数据: "+paraMap.toString());
	        String xmlResult = WxPayApi.pushOrder(false, paraMap);
	        logger.info("微信小程序统一下单接受返回的结果: "+xmlResult);
	        //转成xml
	        Map<String, String> map = PaymentKit.xmlToMap(xmlResult);
	        //返回状态码
	        String return_code = (String) map.get("return_code");
	        return_msg = return_msg+", "+ (String) map.get("return_msg");
	        logger.info("return_code: "+return_code);
	        //TODO code_url 
	        //返回给小程序端需要的参数
	        Map<String, String> returnMap = new HashMap<String, String>();
	        if("SUCCESS".equals(return_code)){
	            //返回的预付单信息
	            returnMap.put("appId",APPID);
	            returnMap.put("nonceStr", nonceStr);
	            String prepay_id = (String) map.get("prepay_id");
	            returnMap.put("package", "prepay_id=" + prepay_id);
	            returnMap.put("signType","MD5");
	            //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
	            returnMap.put("timeStamp", timeStamp);
	            //拼接签名需要的参数
	            //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
	            String paySign = PaymentKit.createSign(returnMap, PartnerKey).toUpperCase();
	            returnMap.put("paySign", paySign);
	            
	            //生成二维码
	            String path = request.getRealPath("/") + "upload/";
	            String content = map.get("code_url");
	            System.out.println("=======content====" + content);
	            String codeName = UUID.randomUUID().toString();// 二维码的图片名
	            String imageType = "jpg";// 图片类型
	            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
	            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
	            String filename = codeName + "." + imageType;
	            File file1 = new File(path, filename);
	            MatrixToImageWriter.writeToFile(bitMatrix, imageType, file1);
	            
	            aj.setObj(returnMap);
	            aj.setMsg("upload/" + filename);
	            aj.setSuccess(true);
	            return aj;
	        }else{
	            aj.setMsg(getMsgByCode(return_code));
	            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName()+">>>"+return_msg);
	        }
	    } catch (Exception e) {
	        logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() +"发生的异常是: ",e);
	        aj.setMsg("微信支付下单失败,请稍后再试");
	    }
	    return aj;
	}

	@RequestMapping("/notify")
    public ResponseInfo notify(HttpServletRequest request){
        //支付结果通用通知文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
        String xmlMsg = HttpKit.readData(request);
        logger.info("支付通知="+xmlMsg);
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
        String result_code  = params.get("result_code");
        logger.info("out_trade_no="+params.get("out_trade_no"));
        
        String orderno = params.get("out_trade_no");
        Map param = new HashMap();
        param.put("orderno", orderno);
        List<Y23UserOrders> list = y23UserOrdersService.getList(param);
        List<Y23UserPayinfo> payinfos = y23UserPayinfoService.getList(param);
        if(list.size() > 0 && payinfos.size() == 0) {
        	Y23UserPayinfo y23UserPayinfo = new Y23UserPayinfo();
        	y23UserPayinfo.setOrderId(list.get(0).getId());
        	y23UserPayinfo.setOrderno(list.get(0).getOrderno());
        	y23UserPayinfo.setUserId(list.get(0).getUserId());
        	y23UserPayinfo.setPayplatform(2);
            
            //校验返回来的支付结果
            if(PaymentKit.verifyNotify(params, PartnerKey)){
                 if (("SUCCESS").equals(result_code)) {
                	 y23UserPayinfo.setPlatformnumber(params.get("transaction_id"));
                	 y23UserPayinfo.setPlatformstatus(result_code);
                	 y23UserPayinfoService.add(y23UserPayinfo);
                	 
                	 //更新订单的状态
                	 Y23UserOrders order = list.get(0);
                	 order.setStatus(20);
                	 order.setPaymenttime(new Date());
                	 y23UserOrdersService.update(order);
                	 
                	 StringBuffer sb = new StringBuffer();
                	 Map param2 = new HashMap();
             		 param2.put("orderId", order.getId());
             		 List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param2);
             		 for (int i = 0; i < itemList.size(); i++) {
             			if(i == itemList.size() - 1) {
             				sb.append(itemList.get(i).getGoodsName() );
             			} else {
             				sb.append(itemList.get(i).getGoodsName() + ",");
             			}
             			
             		 }
             		 
             		Y23UserShopping y23UserShopping = y23UserShoppingService.getDetailByOrderId(order.getId());
                	 
                	 //发送消息给管理员
                	 Map uparam = new HashMap();
                	 uparam.put("isAdmin", true);
                	 List<Y23User> y23Users = y23UserService.getList(uparam);
                	 for (Y23User y23User:y23Users) {
                		//发送短信信息
                		String username = "linchengmin27"; //在短信宝注册的用户名
            	        String password = "6343efc5fc27467b85fa5421007fa5cf"; //在短信宝注册的密码
            	        String phone = y23User.getPhone();
            	        String content = "【万家鲜惠】您好，新订单来了，订单号：{orderno}。";
            	        content = content.replace("{orderno}",  order.getOrderno() + "，请及时处理请，以免过单");
            	        String httpUrl = "http://api.smsbao.com/sms";
            	        System.out.println("-------------" + content);
            	        StringBuffer httpArg = new StringBuffer();
            	        httpArg.append("u=").append(username).append("&");
            	        httpArg.append("p=").append(DXBSmsUtils.md5(password)).append("&");
            	        httpArg.append("m=").append(phone).append("&");
            	        httpArg.append("c=").append(DXBSmsUtils.encodeUrlString(content, "UTF-8"));
            	        DXBSmsUtils.request(httpUrl, httpArg.toString());
					}
                	 
//                   校验通过. 更改订单状态为已支付, 修改库存
                	 return new ResponseInfo(ResCode.SUCCESS, "支付成功");
                 } else {
                	 y23UserPayinfo.setPlatformstatus("0");
                	 y23UserPayinfoService.add(y23UserPayinfo);
                	 
                	 //更新订单的状态
                	 Y23UserOrders order = list.get(0);
                	 order.setStatus(0);
                	 order.setPaymenttime(new Date());
                	 y23UserOrdersService.update(order);
                 }
            }
        }
        
        return new ResponseInfo(ResCode.FAIL, "支付失败");
    }
	
	/**
     * 发送具体的消息  调用此方法  需要传入 在公众平台选用好的消息模版
     *
     *  并且 需要前端调用微信 授权接口后 才能有权限发送订阅消息
     * @param templateId
     * @return
     */
    public String sendMessage(String templateId, Map<String, TemplateData> dataVoMap, String openId) {
    	String accessToken = WeixinUtil.getAccessToken(APPID, APPSECRET).getToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openId);
        wxMssVo.setTemplate_id(templateId);
      
        wxMssVo.setData(dataVoMap);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //设置编码  不设置转码 可能会出现 乱码问题
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        String string = JSONObject.toJSONString(wxMssVo);
        HttpEntity requestEntity = new HttpEntity(string, headers);
        ResponseEntity stringResponseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        return (String) stringResponseEntity.getBody();
    }
	
	 /**
     * 判断返回的returnCode,给前端相应的提示
     * @param return_code
     * @return
     * @author zgd
     * @time 2018年7月9日17:53:13
     */
    private String getMsgByCode(String returnCode) {
    	if(returnCode.equals("NOTENOUGH")) {
    		return "您的账户余额不足";
    	} else if(returnCode.equals("ORDERPAID")) {
    		return "该订单已支付完成,请勿重复支付";
    	} else if(returnCode.equals("ORDERCLOSED")) {
    		return "当前订单已关闭，请重新下单";
    	} else if(returnCode.equals("SYSTEMERROR")) {
    		return "系统超时，请重新支付";
    	} else if(returnCode.equals("OUT_TRADE_NO_USED")) {
    		return "请勿重复提交该订单";
    	} else {
    		return  "网络正在开小差,请稍后再试";
    	}
        
    }
    
    public ResponseInfo refund(String buyId, String payOutMoney) {
    	MyConfig myConfig = new MyConfig();
	    WXPay wxpay = null;
	    try {
	        myConfig.initCert();//初始化证书
	        wxpay = new WXPay(myConfig);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //生成的随机字符串
	    String nonce_str = WXPayUtil.generateNonceStr();
	    
	    //商户订单号
	    String out_trade_no= WXPayUtil.generateNonceStr();
	    //统一下单接口参数
	    HashMap<String, String> data = new HashMap<String, String>();
	    
	    try {
	        data.put("appid", myConfig.getAppID());
	        data.put("mch_id", myConfig.getMchID());
	        data.put("nonce_str", nonce_str);
	        data.put("sign_type", "MD5");
	        data.put("out_trade_no",buyId);//微信订单号
	        data.put("out_refund_no", out_trade_no);//商户退款单号
	        data.put("total_fee",payOutMoney);//支付金额，微信支付提交的金额是不能带小数点的，且是以分为单位,这边需要转成字符串类型，否则后面的签名会失败
	        data.put("refund_fee",payOutMoney);//退款总金额,订单总金额,单位为分,只能为整数
	        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
	        logger.info("退款接口请求参数KEY: "+myConfig.getKey());
	        logger.info("退款接口请求参数data: "+data);
	        String sign = WXPayUtil.generateSignature(data, myConfig.getKey());
	        data.put("sign", sign);
	        logger.info("退款接口参数信息: "+data.toString());
	        Map<String, String> rMap = wxpay.refund(data);
	        logger.info("微信小程序退款接口返回值: "+rMap.toString());
	        String return_code = (String) rMap.get("return_code");
	        String result_code = (String) rMap.get("result_code");
	
	        if ("SUCCESS".equals(return_code) && return_code.equals(result_code)) {
	        	ResponseInfo responseBean = new ResponseInfo(ResCode.SUCCESS,"微信退款成功");
	        	return responseBean;
	        }else{
	        	ResponseInfo responseBean = new ResponseInfo(ResCode.FAIL,"微信退款失败");
	        	return responseBean;
	        }
	    } catch (Exception e) {
	    	ResponseInfo responseBean = new ResponseInfo(ResCode.FAIL,"微信退款失败");
	        e.printStackTrace();
	        return responseBean;
	    }
    }
    
    
}
