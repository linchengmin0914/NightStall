package com.lcm.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Y23UserOrders {
	private Long id;
	private Long userId;
	private Double payment;
	private Double oldPayment;
	private Integer paymenttype;
	private Double postage;
	private Integer status;
	private Date paymenttime;
	private Date sendtime;
	private Date endtime;
	private Date closetime;
	private String remark;
	private String orderno;
	private String cancelDesc;
	private Date cancelTime;
	private Long userCouponId;
	private Long couponId;
	private Double reduceAmount;
	private Double weight;
	private Double distance;
	private Double fee;
	private Double deliverFee;
	private Double couponFee;
	private Double insuranceFee;
	private Double tips;
	private Boolean isPrint;
	private Long storeId;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String displayStatus;
    private String enDisplayStatus;
    private Integer totalNum;
    
    private String receivername;
	private String receivermobile;
	private String receiverarea;
	private String receiveraddress;
	
	private Double commission;
	private String createTimeStr;
	
	public Y23UserOrders() {
	
	}
	
	public Y23UserOrders(Long id,String orderno,String remark) {
		this.id = id;
		this.orderno = orderno;
		this.remark = remark;
	}
	
	
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(Double deliverFee) {
		this.deliverFee = deliverFee;
	}

	public Double getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Double couponFee) {
		this.couponFee = couponFee;
	}

	public Double getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(Double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public Double getTips() {
		return tips;
	}

	public void setTips(Double tips) {
		this.tips = tips;
	}

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
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Integer getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(Integer paymenttype) {
		this.paymenttype = paymenttype;
	}
	public Double getPostage() {
		return postage;
	}
	public void setPostage(Double postage) {
		this.postage = postage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(Date paymenttime) {
		this.paymenttime = paymenttime;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getClosetime() {
		return closetime;
	}
	public void setClosetime(Date closetime) {
		this.closetime = closetime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getDisplayStatus() {
		if(this.status == -1) {
			this.displayStatus = "申请退款";
		}
		
		if(this.status == 0) {
			this.displayStatus = "已取消";
		} 
		
		if(this.status == 10) {
			this.displayStatus = "待付款";
		} 
		
		if(this.status == 20) {
			this.displayStatus = "待发货";
		} 
		
		if(this.status == 40) {
			this.displayStatus = "待收货";
		} 
		
		if(this.status == 50) {
			this.displayStatus = "待评价";
		}
		
		if(this.status == 60) {
			this.displayStatus = "已完成";
		}
		
		if(this.status == 70) {
			this.displayStatus = "已退款";
		}
		
		return displayStatus;
	}
	
	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}
	
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	public String getEnDisplayStatus() {
		if(this.status == 0) {
			this.enDisplayStatus = "CANCELLED";
		} 
		
		if(this.status == 10) {
			this.enDisplayStatus = "PAYING";
		} 
		
		if(this.status == 20) {
			this.enDisplayStatus = "SENDING";
		} 
		
		if(this.status == 40) {
			this.enDisplayStatus = "RECEIVING";
		} 
		
		if(this.status == 50) {
			this.enDisplayStatus = "EVALUATEDING";
		}
		
		if(this.status == 60) {
			this.enDisplayStatus = "COMPLETED";
		}
		
		if(this.status == 70) {
			this.enDisplayStatus = "REFUNDED";
		}
		return enDisplayStatus;
	}
	
	public void setEnDisplayStatus(String enDisplayStatus) {
		this.enDisplayStatus = enDisplayStatus;
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
	public String getCancelDesc() {
		return cancelDesc;
	}
	public void setCancelDesc(String cancelDesc) {
		this.cancelDesc = cancelDesc;
	}
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Double getOldPayment() {
		return oldPayment;
	}

	public void setOldPayment(Double oldPayment) {
		this.oldPayment = oldPayment;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Double getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(Double reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Boolean getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Boolean isPrint) {
		this.isPrint = isPrint;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getCreateTimeStr() {
		this.createTimeStr = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(this.createTime);
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
    
    
}
