package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserCashout {
	private Long id;
	private Long userId;
	private Double amount;
	private Double money;
	private Integer status;
	private Double sxmoney;
	private Boolean isDelete;
	private Date createTime;
    private Date modifyTime;
    
    private String fxname;
    private String fxphone;
    
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Double getSxmoney() {
		return sxmoney;
	}
	public void setSxmoney(Double sxmoney) {
		this.sxmoney = sxmoney;
	}
	public String getFxname() {
		return fxname;
	}
	public void setFxname(String fxname) {
		this.fxname = fxname;
	}
	public String getFxphone() {
		return fxphone;
	}
	public void setFxphone(String fxphone) {
		this.fxphone = fxphone;
	}
    
    
}
