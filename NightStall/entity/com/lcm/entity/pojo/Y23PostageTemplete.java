package com.lcm.entity.pojo;

import java.util.Date;

public class Y23PostageTemplete {
	private Long id;
	private String name;
	private Integer type;
	private Boolean isOpen;
	private String areas;
	private Integer firstNum;
	private Double firstPrice;
	private Integer continueNum;
	private Double continuePrice;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String idStr;
    
    private String displayType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
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
	public String getDisplayType() {
		if(this.type == 1) {
			this.displayType = "包邮";
		}
		
		if(this.type == 2) {
			this.displayType = "按件数";
		}
		
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getIdStr() {
		this.idStr = this.id + "";
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
    
    
}
