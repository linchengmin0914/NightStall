package com.lcm.utils.response;

import java.util.List;

import com.lcm.entity.pojo.Y23Goods;

public class MenuGoodsBean {
	private Long cateId;
	private String cateName;
	private List<Y23Goods> goodsList;
	
	public Long getCateId() {
		return cateId;
	}
	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public List<Y23Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Y23Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
