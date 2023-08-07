package com.lcm.entity.pojo;

import java.util.Date;

public class UserRight {
	public static class SourceType {
		public static int ZILIAO = 1; //完善资料
		public static int ORDER = 2; //购买服务
		public static int OLD = 3; //旧的权限
	}
	
	private Long id;

    private Long userId;

    private Integer contactNum;

    private Integer publishNum;

    private Integer showMathRightNum;

    private Integer smsNum;

    private Integer rightType;

    private Date freeStartTime;

    private Date freeEndTime;

    private Integer sourceType;

    private Long sourceId;

    private Boolean isDelete;

    private Date createTime;

    private Date modifyTime;
    
    private String username;
    
    private Integer storeNum;
    
    private Integer fanganNum;

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

	public Integer getContactNum() {
		return contactNum;
	}

	public void setContactNum(Integer contactNum) {
		this.contactNum = contactNum;
	}

	public Integer getPublishNum() {
		return publishNum;
	}

	public void setPublishNum(Integer publishNum) {
		this.publishNum = publishNum;
	}

	public Integer getShowMathRightNum() {
		return showMathRightNum;
	}

	public void setShowMathRightNum(Integer showMathRightNum) {
		this.showMathRightNum = showMathRightNum;
	}

	public Integer getSmsNum() {
		return smsNum;
	}

	public void setSmsNum(Integer smsNum) {
		this.smsNum = smsNum;
	}

	public Integer getRightType() {
		return rightType;
	}

	public void setRightType(Integer rightType) {
		this.rightType = rightType;
	}

	public Date getFreeStartTime() {
		return freeStartTime;
	}

	public void setFreeStartTime(Date freeStartTime) {
		this.freeStartTime = freeStartTime;
	}

	public Date getFreeEndTime() {
		return freeEndTime;
	}

	public void setFreeEndTime(Date freeEndTime) {
		this.freeEndTime = freeEndTime;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	public Integer getFanganNum() {
		return fanganNum;
	}

	public void setFanganNum(Integer fanganNum) {
		this.fanganNum = fanganNum;
	}
    
    
	
}	
