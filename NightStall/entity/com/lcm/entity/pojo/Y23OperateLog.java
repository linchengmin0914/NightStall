package com.lcm.entity.pojo;

import java.util.Date;

public class Y23OperateLog {
	private Long id;
	
	private String operateMo;
	private String product;
	private String many;
	private String operator;
	private String time;
    
    private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOperateMo() {
		return operateMo;
	}
	public void setOperateMo(String operateMo) {
		this.operateMo = operateMo;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMany() {
		return many;
	}
	public void setMany(String many) {
		this.many = many;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
    
    
    
}
