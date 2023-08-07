package com.lcm.utils.response;


public class GoodCartResponse extends ResponseInfo {
    private String total;
    private String totalPrice;
    
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
    
}
