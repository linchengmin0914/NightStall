package com.lcm.entity.pojo;

import java.util.Date;
import java.util.List;

import com.lcm.utils.response.SubGoodsBean;

public class Y23Goods {
	private Long id;
	private String goodsName;
	private String cover;
	private String longCover;
	private Long brandId;
	private Long cateId;
	private Double price;
	private Double original;
	private String tags;
	private String content;
	private String summary;
	private Integer status;
	private Integer isSale;
	private Boolean isIndex;
	private Boolean isZhdz;
	private Integer saleNum;
	private Integer storeNum;
	private String brandName;
	private Double commission;
	private Boolean fxstatus;
	private Double weight;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    private Long storeId;
    private String unit;
    
    private String enGoodName;
    private String enCover;
    private String enTags;
    private String enContent;
    private String enSummary;
    private String enBrandName;
    
    
    private String cateName;
    private String enCateName;
    private String storeName;
    
    private String postageIds;
    private String no;
    
    private String idStr;
    private Boolean isJoined; 
    
    private List<SubGoodsBean> subGoods;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getLongCover() {
		return longCover;
	}
	public void setLongCover(String longCover) {
		this.longCover = longCover;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getCateId() {
		return cateId;
	}
	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOriginal() {
		return original;
	}
	public void setOriginal(Double original) {
		this.original = original;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getIsSale() {
		return isSale;
	}
	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
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
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}
	public Boolean getIsZhdz() {
		return isZhdz;
	}
	public void setIsZhdz(Boolean isZhdz) {
		this.isZhdz = isZhdz;
	}
	public Integer getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	public Integer getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getEnGoodName() {
		return enGoodName;
	}
	public void setEnGoodName(String enGoodName) {
		this.enGoodName = enGoodName;
	}
	public String getEnCover() {
		return enCover;
	}
	public void setEnCover(String enCover) {
		this.enCover = enCover;
	}
	public String getEnTags() {
		return enTags;
	}
	public void setEnTags(String enTags) {
		this.enTags = enTags;
	}
	public String getEnContent() {
		return enContent;
	}
	public void setEnContent(String enContent) {
		this.enContent = enContent;
	}
	public String getEnSummary() {
		return enSummary;
	}
	public void setEnSummary(String enSummary) {
		this.enSummary = enSummary;
	}
	public String getEnBrandName() {
		return enBrandName;
	}
	public void setEnBrandName(String enBrandName) {
		this.enBrandName = enBrandName;
	}
	public String getEnCateName() {
		return enCateName;
	}
	public void setEnCateName(String enCateName) {
		this.enCateName = enCateName;
	}
	public String getPostageIds() {
		return postageIds;
	}
	public void setPostageIds(String postageIds) {
		this.postageIds = postageIds;
	}
	public String getNo() {
		if(this.no == null) this.no = "";
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getIdStr() {
		this.idStr = this.id + "";
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Boolean getFxstatus() {
		return fxstatus;
	}
	public void setFxstatus(Boolean fxstatus) {
		this.fxstatus = fxstatus;
	}
	public Boolean getIsJoined() {
		return isJoined;
	}
	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public List<SubGoodsBean> getSubGoods() {
		return subGoods;
	}
	public void setSubGoods(List<SubGoodsBean> subGoods) {
		this.subGoods = subGoods;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
    
	
    
}
