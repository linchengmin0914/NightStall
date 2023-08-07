package com.lcm.entity.pojo;

import java.util.Date;

public class Y23Store {
	private Long id;
	private String name;
	private String cover;
	private String phone;
	private String summary;
	private String address;
	private String opentime;
	private Integer status;
	private Double latitude;
	private Double longitude;
	private Integer msaleNum;
	private Integer overall;
	private Double distanceFormat;
	private Double minDeliveryPrice;
	private Integer deliveryTime;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private Double distance;
    
    private String idStr;
    
    
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
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Double getMinDeliveryPrice() {
		return minDeliveryPrice;
	}
	public void setMinDeliveryPrice(Double minDeliveryPrice) {
		this.minDeliveryPrice = minDeliveryPrice;
	}
	public Integer getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
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
	public Integer getMsaleNum() {
		return msaleNum;
	}
	public void setMsaleNum(Integer msaleNum) {
		this.msaleNum = msaleNum;
	}
	public Integer getOverall() {
		return overall;
	}
	public void setOverall(Integer overall) {
		this.overall = overall;
	}
	public Double getDistanceFormat() {
		return distanceFormat;
	}
	public void setDistanceFormat(Double distanceFormat) {
		this.distanceFormat = distanceFormat;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getIdStr() {
		this.idStr = this.id + "";
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
    
}
