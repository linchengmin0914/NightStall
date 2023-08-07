package com.lcm.entity.pojo;

import java.util.Date;

public class Y23GoodsProperty {
	private Long id;
	private Long goodsId;
	private Long propNameId;
	private Long propValueId;
	private Double price;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private String nameTitle;
    private String enNameTitle;
    private Integer isColor;
    private Integer isKey;
    private Integer isSale;
    private String valueTitle;
    private String enValueTitle;
    private String value;
    private String isMust;
    
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
	public Long getPropNameId() {
		return propNameId;
	}
	public void setPropNameId(Long propNameId) {
		this.propNameId = propNameId;
	}
	public Long getPropValueId() {
		return propValueId;
	}
	public void setPropValueId(Long propValueId) {
		this.propValueId = propValueId;
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
	public String getNameTitle() {
		return nameTitle;
	}
	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}
	public Integer getIsColor() {
		return isColor;
	}
	public void setIsColor(Integer isColor) {
		this.isColor = isColor;
	}
	public Integer getIsKey() {
		return isKey;
	}
	public void setIsKey(Integer isKey) {
		this.isKey = isKey;
	}
	public Integer getIsSale() {
		return isSale;
	}
	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}
	public String getValueTitle() {
		return valueTitle;
	}
	public void setValueTitle(String valueTitle) {
		this.valueTitle = valueTitle;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}
	public String getEnNameTitle() {
		return enNameTitle;
	}
	public void setEnNameTitle(String enNameTitle) {
		this.enNameTitle = enNameTitle;
	}
	public String getEnValueTitle() {
		return enValueTitle;
	}
	public void setEnValueTitle(String enValueTitle) {
		this.enValueTitle = enValueTitle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
    
}
