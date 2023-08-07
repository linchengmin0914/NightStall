package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserShopping {
	private Long id;
	private Long userId;
	private Long orderId;
	private String receivername;
	private String receivermobile;
	private String receiverarea;
	private String receiveraddress;
	private String express;
	private String expressnum;
	private Double latitude;
	private Double longitude;
	private String houseno;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String displayExpress;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getReceivermobile() {
		return receivermobile;
	}
	public void setReceivermobile(String receivermobile) {
		this.receivermobile = receivermobile;
	}
	public String getReceiverarea() {
		return receiverarea;
	}
	public void setReceiverarea(String receiverarea) {
		this.receiverarea = receiverarea;
	}
	public String getReceiveraddress() {
		return receiveraddress;
	}
	public void setReceiveraddress(String receiveraddress) {
		this.receiveraddress = receiveraddress;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpressnum() {
		return expressnum;
	}
	public void setExpressnum(String expressnum) {
		this.expressnum = expressnum;
	}
	public String getDisplayExpress() {
		if("zhaijisong".equals(this.express)) {
			displayExpress = "宅急送";
		}
		
		if("jd".equals(this.express)) {
			displayExpress = "京东";
		}
		
		if("youshuwuliu".equals(this.express)) {
			displayExpress = "优速";
		}
		
		if("tiantian".equals(this.express)) {
			displayExpress = "天天";
		}
		
		if("yunda".equals(this.express)) {
			displayExpress = "韵达";
		}
		
		if("guotongkuaidi".equals(this.express)) {
			displayExpress = "国通";
		}
		
		if("yuantong".equals(this.express)) {
			displayExpress = "圆通";
		}
		
		if("zhongtong".equals(this.express)) {
			displayExpress = "中通";
		}
		
		if("shentong".equals(this.express)) {
			displayExpress = "申通";
		}
		
		if("huitongkuaidi".equals(this.express)) {
			displayExpress = "百世汇通";
		}
		
		if("youzhengguonei".equals(this.express)) {
			displayExpress = "邮政包裹";
		}
		
		if("ems".equals(this.express)) {
			displayExpress = "EMS";
		}
		
		if("shunfeng".equals(this.express)) {
			displayExpress = "顺丰";
		}
		
		if("shop".equals(this.express)) {
			displayExpress = "商家自送";
		}
		
		if("dada".equals(this.express)) {
			displayExpress = "达达配送";
		}
		
		return displayExpress;
	}
	public void setDisplayExpress(String displayExpress) {
		this.displayExpress = displayExpress;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getHouseno() {
		return houseno;
	}
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}
    
    
}
