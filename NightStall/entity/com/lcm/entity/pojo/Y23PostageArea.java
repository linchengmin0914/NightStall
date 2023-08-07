package com.lcm.entity.pojo;

import java.util.Date;

public class Y23PostageArea {
	private Long id;
	private Long tempId;
	private String areas;
	private Integer firstNum;
	private Double firstPrice;
	private Integer continueNum;
	private Double continuePrice;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public Integer getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}
	public Double getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(Double firstPrice) {
		this.firstPrice = firstPrice;
	}
	public Integer getContinueNum() {
		return continueNum;
	}
	public void setContinueNum(Integer continueNum) {
		this.continueNum = continueNum;
	}
	public Double getContinuePrice() {
		return continuePrice;
	}
	public void setContinuePrice(Double continuePrice) {
		this.continuePrice = continuePrice;
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
	public Long getTempId() {
		return tempId;
	}
	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}
    
    
}
