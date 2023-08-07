package com.lcm.entity.pojo;

import java.util.Date;

public class Y23Coupon {
	private Long id;
	private Integer scope;
	private Integer type;
	private Double face;
	private String name;
	private Double userequire;
	private Integer skunum;
	private Integer moreget;
	private Date getstart;
	private Date getend;
	private Integer validtype;
	private Date validstart;
	private Date validend;
	private Integer validsnum;
	private Integer validenum;
	private String summary;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private Integer isGeted = 0;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getScope() {
		return scope;
	}
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getFace() {
		return face;
	}
	public void setFace(Double face) {
		this.face = face;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getUserequire() {
		return userequire;
	}
	public void setUserequire(Double userequire) {
		this.userequire = userequire;
	}
	public Integer getSkunum() {
		return skunum;
	}
	public void setSkunum(Integer skunum) {
		this.skunum = skunum;
	}
	public Integer getMoreget() {
		return moreget;
	}
	public void setMoreget(Integer moreget) {
		this.moreget = moreget;
	}
	public Date getGetstart() {
		return getstart;
	}
	public void setGetstart(Date getstart) {
		this.getstart = getstart;
	}
	public Date getGetend() {
		return getend;
	}
	public void setGetend(Date getend) {
		this.getend = getend;
	}
	public Integer getValidtype() {
		return validtype;
	}
	public void setValidtype(Integer validtype) {
		this.validtype = validtype;
	}
	public Date getValidstart() {
		return validstart;
	}
	public void setValidstart(Date validstart) {
		this.validstart = validstart;
	}
	public Date getValidend() {
		return validend;
	}
	public void setValidend(Date validend) {
		this.validend = validend;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public Integer getValidsnum() {
		return validsnum;
	}
	public void setValidsnum(Integer validsnum) {
		this.validsnum = validsnum;
	}
	public Integer getValidenum() {
		return validenum;
	}
	public void setValidenum(Integer validenum) {
		this.validenum = validenum;
	}
	public Integer getIsGeted() {
		return isGeted;
	}
	public void setIsGeted(Integer isGeted) {
		this.isGeted = isGeted;
	}
    
    
}
