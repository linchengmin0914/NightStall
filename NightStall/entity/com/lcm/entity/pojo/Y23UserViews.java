package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserViews {
	private Long id;
	private Long goodsId;
	private Long userId;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String goodsName;
	private String enGoodsName;
	private String cover;
	private String enCover;
	private Double price;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getEnGoodsName() {
		return enGoodsName;
	}
	public void setEnGoodsName(String enGoodsName) {
		this.enGoodsName = enGoodsName;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getEnCover() {
		return enCover;
	}
	public void setEnCover(String enCover) {
		this.enCover = enCover;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
    
	
}
