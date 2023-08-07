package com.lcm.utils.response;

import com.lcm.entity.pojo.Y23Coupon;

public class Y23CouponDetailResponse extends ResponseInfo {
	private Y23Coupon entity;
	
	public Y23CouponDetailResponse(int resCode, String resDes, Y23Coupon entity) {
		super(resCode, resDes);
		this.entity = entity;
	}

	public Y23Coupon getEntity() {
		return entity;
	}

	public void setEntity(Y23Coupon entity) {
		this.entity = entity;
	}
	

}
