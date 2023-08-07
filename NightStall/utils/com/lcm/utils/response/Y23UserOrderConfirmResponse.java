package com.lcm.utils.response;

import java.util.List;

import com.lcm.entity.pojo.Y23CouponGoods;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserCart;
import com.lcm.entity.pojo.Y23UserCoupon;

public class Y23UserOrderConfirmResponse extends ResponseInfo {
    private List<Y23UserCart> list;
    private List<Y23UserCoupon> userCouponList;
    private Y23UserAddress userAddress;
    private Double totalPrice;
    private String cartIds;

	public Y23UserOrderConfirmResponse(int resCode, String resDes, List<Y23UserCart> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserCart> getList() {
		return list;
	}

	public void setList(List<Y23UserCart> list) {
		this.list = list;
	}

	public Y23UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Y23UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCartIds() {
		return cartIds;
	}

	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}

	public List<Y23UserCoupon> getUserCouponList() {
		return userCouponList;
	}

	public void setUserCouponList(List<Y23UserCoupon> userCouponList) {
		this.userCouponList = userCouponList;
	}

	

}
