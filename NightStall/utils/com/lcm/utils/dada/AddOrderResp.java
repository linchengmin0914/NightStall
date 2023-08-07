package com.lcm.utils.dada;

public class AddOrderResp {
	 /**
     * 配送距离(单位：米)
     */
    private Double distance;

    /**
     * 实际运费(单位：元)，运费减去优惠券费用
     */
    private Double fee;

    /**
     * 运费(单位：元)
     */
    private Double deliverFee;

    /**
     * 优惠券费用(单位：元)
     */
    private Double couponFee;

    /**
     * 保价费(单位：元)
     */
    private Double insuranceFee;

    /**
     * 小费(单位：元)
     */
    private Double tips;

    /**
     * 运单号(查询运费接口会返回该字段，用于查询运费后发单使用)
     */
    private String deliveryNo;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(Double deliverFee) {
		this.deliverFee = deliverFee;
	}

	public Double getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Double couponFee) {
		this.couponFee = couponFee;
	}

	public Double getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(Double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public Double getTips() {
		return tips;
	}

	public void setTips(Double tips) {
		this.tips = tips;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
    
    
}
