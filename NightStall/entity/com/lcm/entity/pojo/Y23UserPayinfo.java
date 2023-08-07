package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserPayinfo {
	private Long id;
	private Long orderId;
	private String orderno;
	private Long userId;
	private Integer payplatform;
	private String platformnumber;
	private String platformstatus;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getPayplatform() {
		return payplatform;
	}
	public void setPayplatform(Integer payplatform) {
		this.payplatform = payplatform;
	}
	public String getPlatformnumber() {
		return platformnumber;
	}
	public void setPlatformnumber(String platformnumber) {
		this.platformnumber = platformnumber;
	}
	public String getPlatformstatus() {
		return platformstatus;
	}
	public void setPlatformstatus(String platformstatus) {
		this.platformstatus = platformstatus;
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
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
    
    
}
