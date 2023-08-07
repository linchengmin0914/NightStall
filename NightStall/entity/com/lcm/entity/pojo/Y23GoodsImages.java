package com.lcm.entity.pojo;

import java.util.Date;

public class Y23GoodsImages {
	private Long id;
	private Long goodsId;
	private String link;
	private String enLink;
	private Integer position;
	private Integer isMaster;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
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
	public String getEnLink() {
		return enLink;
	}
	public void setEnLink(String enLink) {
		this.enLink = enLink;
	}
    
    
}
