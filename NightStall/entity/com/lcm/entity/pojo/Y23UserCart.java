package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserCart {
	private Long id;
	private Long userId;
	private Long goodsId;
	private Integer goodsNum;
	private String props;
	private Long storeId;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String propsStr;
    private String goodsName;
    private String enGoodsName;
    private String cover;
    private String enCover;
    private Double price;
    private String enPropsStr;
    
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
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
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
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public String getPropsStr() {
		return propsStr;
	}
	public void setPropsStr(String propsStr) {
		this.propsStr = propsStr;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getEnGoodsName() {
		return enGoodsName;
	}
	public void setEnGoodsName(String enGoodsName) {
		this.enGoodsName = enGoodsName;
	}
	public String getEnCover() {
		return enCover;
	}
	public void setEnCover(String enCover) {
		this.enCover = enCover;
	}
	public String getEnPropsStr() {
		return enPropsStr;
	}
	public void setEnPropsStr(String enPropsStr) {
		this.enPropsStr = enPropsStr;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
    
    
}
