package com.lcm.utils.response;

import java.util.List;

public class UserCartToOrderBean {
	private Double totalGoodsPrice;
	private Integer totalNum;
	private Double totalPackingFee;
	private Double totalPrice;
//	private String goodsNums;
	
//	private List<GoodsCartBean> goods;

	public Double getTotalGoodsPrice() {
		return totalGoodsPrice;
	}

	public void setTotalGoodsPrice(Double totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPackingFee() {
		return totalPackingFee;
	}

	public void setTotalPackingFee(Double totalPackingFee) {
		this.totalPackingFee = totalPackingFee;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

//	public String getGoodsNums() {
//		return goodsNums;
//	}
//
//	public void setGoodsNums(String goodsNums) {
//		this.goodsNums = goodsNums;
//	}

//	public List<GoodsCartBean> getGoods() {
//		return goods;
//	}
//
//	public void setGoods(List<GoodsCartBean> goods) {
//		this.goods = goods;
//	}
	
	
}
