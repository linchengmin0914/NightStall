package com.lcm.utils.response;

public class OrderNumResponse extends ResponseInfo {
    private Integer orderStatus10;
    private Integer orderStatus20;
    private Integer orderStatus40;
    private Integer orderStatus50;
    private Integer orderNum;

	public OrderNumResponse(int resCode, String resDes) {
		super(resCode, resDes);
	}

	public Integer getOrderStatus10() {
		return orderStatus10;
	}

	public void setOrderStatus10(Integer orderStatus10) {
		this.orderStatus10 = orderStatus10;
	}

	public Integer getOrderStatus20() {
		return orderStatus20;
	}

	public void setOrderStatus20(Integer orderStatus20) {
		this.orderStatus20 = orderStatus20;
	}

	public Integer getOrderStatus40() {
		return orderStatus40;
	}

	public void setOrderStatus40(Integer orderStatus40) {
		this.orderStatus40 = orderStatus40;
	}

	public Integer getOrderStatus50() {
		return orderStatus50;
	}

	public void setOrderStatus50(Integer orderStatus50) {
		this.orderStatus50 = orderStatus50;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	
}
