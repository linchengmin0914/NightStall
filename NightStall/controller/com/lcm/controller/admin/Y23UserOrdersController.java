package com.lcm.controller.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcm.controller.base.BaseController;
import com.lcm.controller.wx.WxPayController;
import com.lcm.entity.pojo.Y23DadaStore;
import com.lcm.entity.pojo.Y23UserComment;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;
import com.lcm.entity.pojo.Y23UserPayinfo;
import com.lcm.entity.pojo.Y23UserShopping;
import com.lcm.service.business.Y23DadaStoreService;
import com.lcm.service.business.Y23UserCommentService;
import com.lcm.service.business.Y23UserOrderitemService;
import com.lcm.service.business.Y23UserOrdersService;
import com.lcm.service.business.Y23UserPayinfoService;
import com.lcm.service.business.Y23UserShoppingService;
import com.lcm.utils.dada.AddAfterQuery;
import com.lcm.utils.dada.AddAfterQueryResult;
import com.lcm.utils.dada.QueryDeliverFee;
import com.lcm.utils.dada.QueryDeliverFeeResult;
import com.lcm.utils.dada.SendAddAfterQuery;
import com.lcm.utils.dada.SendQueryDeliverFee;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23UserOrdersOrders")
@RequestMapping("/admin/01/order")
public class Y23UserOrdersController extends BaseController {
	@Autowired
	private Y23UserOrdersService y23UserOrdersService;
	@Autowired
	private Y23UserOrderitemService y23UserOrderitemService;
	@Autowired
	private Y23UserShoppingService y23UserShoppingService;
	@Autowired
	private Y23UserPayinfoService y23UserPayinfoService;
	@Autowired
	private Y23UserCommentService y23UserCommentService;
	@Autowired
	private Y23DadaStoreService y23DadaStoreService;
	
	private String path = "01/order";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23UserOrders> list = y23UserOrdersService.getList(param);
		PageResponse response = new PageResponse(y23UserOrdersService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		param.put("page", 0);
		param.put("pageSize", 99999);
		List<Y23UserOrders> list = y23UserOrdersService.getList(param);
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet userTable = wb.createSheet("用户订单表");
        
        //设置列宽度
        userTable.setColumnWidth(0, 19 * 256);
        userTable.setColumnWidth(1, 16 * 256);
        userTable.setColumnWidth(2, 16 * 256);
        userTable.setColumnWidth(3, 13 * 256);
        userTable.setColumnWidth(4, 18 * 256);
        userTable.setColumnWidth(5, 20 * 256);
        userTable.setColumnWidth(6, 45 * 256);
        userTable.setColumnWidth(7, 10 * 256);
        userTable.setColumnWidth(8, 22 * 256);
        
        //设置单元列名
	    HSSFRow row1 = userTable.createRow(0);
	    row1.createCell(0).setCellValue("订单编号");
	    row1.createCell(1).setCellValue("订单金额（元）");
	    row1.createCell(2).setCellValue("订单运费（元）");
	    row1.createCell(3).setCellValue("收货姓名");
	    row1.createCell(4).setCellValue("收货移动电话");
	    row1.createCell(5).setCellValue("所在区域");
	    row1.createCell(6).setCellValue("详细地址");
	    row1.createCell(7).setCellValue("订单状态");
	    row1.createCell(8).setCellValue("下单时间");
	    
	    
	    //循环内容
        for (int i = 0; i < list.size(); i++) {
        	Y23UserOrders order = list.get(i);
            HSSFRow row = userTable.createRow(i + 1);
            row.createCell(0).setCellValue(order.getOrderno());
            row.createCell(1).setCellValue(order.getPayment());
            row.createCell(2).setCellValue(order.getPostage());
            row.createCell(3).setCellValue(order.getReceivername());
            row.createCell(4).setCellValue(order.getReceivermobile());
            row.createCell(5).setCellValue(order.getReceiverarea());
            row.createCell(6).setCellValue(order.getReceiveraddress());
            row.createCell(7).setCellValue(order.getDisplayStatus());
            row.createCell(8).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateTime()));
        }
        
        try {
        	response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            String attachName = "用户订单表(" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ").xls";
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(attachName, "UTF-8"));//默认Excel名称
            response.flushBuffer();
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserOrders entity) {
		ResponseInfo responseInfo = y23UserOrdersService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@RequestMapping("/updateprice")
	public String updateprice(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/updateprice";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserOrders entity) {
		SimpleDateFormat sd = new SimpleDateFormat("HHmmss");
		Y23UserOrders y23UserOrders = y23UserOrdersService.getDetail(entity.getId());
		y23UserOrders.setPayment(entity.getPayment());
		y23UserOrders.setOldPayment(entity.getOldPayment());
		y23UserOrders.setOrderno(y23UserOrders.getOrderno() + "-" + sd.format(new Date()));
		ResponseInfo responseInfo = y23UserOrdersService.update(y23UserOrders);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23UserOrdersService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23UserOrdersService.deleteMul(ids);
		return responseInfo;
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(id);
		model.addAttribute("entity", entity);
		
		Map param = new HashMap();
		param.put("orderId", entity.getId());
		List<Y23UserOrderitem> itemList = y23UserOrderitemService.getList(param);
		model.addAttribute("itemList", itemList);
		
		Map param2 = new HashMap();
		param2.put("orderId", entity.getId());
		List<Y23UserShopping> userShoppingList = y23UserShoppingService.getList(param2);
		if(userShoppingList.size() > 0) {
			model.addAttribute("userShopping", userShoppingList.get(0));
		}
		
		Map param3 = new HashMap();
		param3.put("orderId", entity.getId());
		List<Y23UserPayinfo> userPayinfoList = y23UserPayinfoService.getList(param3);
		if(userPayinfoList.size() > 0) {
			model.addAttribute("userPayinfo", userPayinfoList.get(0));
		}
		
		Map param4 = new HashMap();
		param4.put("orderId", entity.getId());
		List<Y23UserComment> userCommentList = y23UserCommentService.getList(param4);
		if(userCommentList.size() > 0) {
			model.addAttribute("userComment", userCommentList.get(0));
		}
		
		return ADMIN_PG_PREFIX + "/" + path + "/detail";
	}
	
	@RequestMapping("/send")
	public String send(HttpServletRequest request,Model model, @RequestParam Long orderId) {
		Map param2 = new HashMap();
		param2.put("orderId", orderId);
		List<Y23UserShopping> userShoppingList = y23UserShoppingService.getList(param2);
		if(userShoppingList.size() > 0) {
			model.addAttribute("userShopping", userShoppingList.get(0));
		}
		
		Map param = new HashMap();
		param.put("orderBy", " a.createTime desc");
		List<Y23DadaStore> y23DadaStoreList = y23DadaStoreService.getList(param);
		model.addAttribute("y23DadaStoreList", y23DadaStoreList);
		return ADMIN_PG_PREFIX + "/" + path + "/send";
	}
	
	@ResponseBody
	@RequestMapping("/send/done")
	public ResponseInfo sendDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserShopping entity) {
		ResponseInfo responseInfo = y23UserShoppingService.update(entity);
		
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
			queryDeliverFee.setReceiver_address(y23UserShopping.getReceiveraddress());
			queryDeliverFee.setReceiver_lat(y23UserShopping.getLatitude());
			queryDeliverFee.setReceiver_lng(y23UserShopping.getLongitude());
			queryDeliverFee.setCallback("xmyj.yifan668.com");
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
		return responseInfo;
	}
	
	
	@RequestMapping("/sends")
	public String sends(HttpServletRequest request,Model model, @RequestParam String orderIds) {
		model.addAttribute("orderIds", orderIds);
		return ADMIN_PG_PREFIX + "/" + path + "/sends";
	}
	
	@ResponseBody
	@RequestMapping("/sends/done")
	public ResponseInfo sendsDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserShopping entity
			, @RequestParam String orderIds) {
		String[] orderIdArr = orderIds.split(",");
		for (int i = 0; i < orderIdArr.length; i++) {
			Map param2 = new HashMap();
			param2.put("orderId", Long.parseLong(orderIdArr[i]));
			List<Y23UserShopping> userShoppingList = y23UserShoppingService.getList(param2);
			if(userShoppingList.size() > 0) {
				Y23UserShopping y23UserShopping = userShoppingList.get(0);
				y23UserShopping.setExpress(entity.getExpress());
				y23UserShopping.setExpressnum(entity.getExpressnum());
				y23UserShoppingService.update(y23UserShopping);
			}
			
			//更新订单状态
			Y23UserOrders order = y23UserOrdersService.getDetail(Long.parseLong(orderIdArr[i]));
			order.setSendtime(new Date());
			order.setStatus(40);
			y23UserOrdersService.update(order);
		}
	
		return new ResponseInfo(ResCode.SUCCESS,"订单发货信息已修改");
	}
	
	@RequestMapping("/updateaddress")
	public String updateaddress(HttpServletRequest request,Model model, @RequestParam Long orderId) {
		Map param2 = new HashMap();
		param2.put("orderId", orderId);
		List<Y23UserShopping> userShoppingList = y23UserShoppingService.getList(param2);
		if(userShoppingList.size() > 0) {
			model.addAttribute("userShopping", userShoppingList.get(0));
			model.addAttribute("area", userShoppingList.get(0).getReceiverarea().replace(",", "/"));
		}
		
		return ADMIN_PG_PREFIX + "/" + path + "/updateaddress";
	}
	
	@ResponseBody
	@RequestMapping("/updateaddress/done")
	public ResponseInfo updateaddressDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserShopping entity) {
		entity.setReceiverarea(entity.getReceiverarea().replace("/", ","));
		ResponseInfo responseInfo = y23UserShoppingService.update(entity);
		return responseInfo;
	}
	
	@RequestMapping("/cancel")
	public String cancel(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23UserOrders entity = y23UserOrdersService.getDetail(id);
		if(entity.getCancelTime() == null) {
			model.addAttribute("cancelTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/cancel";
	}
	
	@ResponseBody
	@RequestMapping("/cancel/done")
	public ResponseInfo cancelDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserOrders entity) {
		//发起微信退款流程
//		if(entity.getStatus().intValue() == -1) {
			Y23UserOrders y23UserOrders = y23UserOrdersService.getDetail(entity.getId());
			if(!StringUtils.isEmpty(entity.getCancelDesc())) y23UserOrders.setCancelDesc(entity.getCancelDesc());
			WxPayController wxPayController = new WxPayController();
			Map param = new HashMap();
		    param.put("orderno", y23UserOrders.getOrderno());
		    List<Y23UserPayinfo> payinfos = y23UserPayinfoService.getList(param);
		    if(payinfos.size() > 0) {
		    	String buyId = y23UserOrders.getOrderno();
		        BigDecimal priceFee = new BigDecimal(y23UserOrders.getPayment() * 100);
//		        BigDecimal priceFee = new BigDecimal(0.01 * 100);
		        ResponseInfo responseInfo = wxPayController.refund(buyId, priceFee + "");
		        if(responseInfo.getResCode() == 0) {
		        	return responseInfo;
		        }
		        
		        y23UserOrders.setStatus(70);
		        y23UserOrders.setClosetime(new Date());
		        y23UserOrdersService.update(y23UserOrders);
		    }
//		}
		return new ResponseInfo(ResCode.SUCCESS, "操作成功");
	}
}
