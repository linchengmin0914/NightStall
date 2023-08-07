package com.lcm.entity.pojo;

import java.util.Date;

public class Y23UserComment {
	private Long id;
	private Long orderId;
	private Long goodsId;
	private Long userId;
	private Long storeId;
	private String content;
	private Integer overall;
	private Integer storeScore;
	private Integer delivScore;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String goodsName;
    private String orderno;
    
    private String goodsIds;
    private String userCover;
    private String nickName;
    
    
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Integer getOverall() {
		return overall;
	}
	public void setOverall(Integer overall) {
		this.overall = overall;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserCover() {
		return userCover;
	}
	public void setUserCover(String userCover) {
		this.userCover = userCover;
	}
	public Integer getStoreScore() {
		return storeScore;
	}
	public void setStoreScore(Integer storeScore) {
		this.storeScore = storeScore;
	}
	public Integer getDelivScore() {
		return delivScore;
	}
	public void setDelivScore(Integer delivScore) {
		this.delivScore = delivScore;
	}
    
    
}
