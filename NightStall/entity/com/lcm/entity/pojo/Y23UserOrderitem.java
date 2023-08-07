package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserOrderitem {
	private Long id;
	private Long orderId;
	private Long userId;
	private Long goodsId;
	private String goodsName;
	private String cover;
	private Double currentunitprice;
	private Integer quantity;
	private Double price;
	private Double totalprice;
	private String props;
	private String propsStr;
	private String enPropsStr;
	private Double weight;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String enGoodsName;
    private String enCover;
    
    private Double commission;
    
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
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	public Double getCurrentunitprice() {
		return currentunitprice;
	}
	public void setCurrentunitprice(Double currentunitprice) {
		this.currentunitprice = currentunitprice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
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
    
    
}
