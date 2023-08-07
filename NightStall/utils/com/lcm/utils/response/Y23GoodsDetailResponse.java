package com.lcm.utils.response;

import java.util.List;
import java.util.Map;

import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.entity.pojo.Y23GoodsProperty;

public class Y23GoodsDetailResponse extends ResponseInfo {
    private Y23Goods goods;
    private List<Y23GoodsImages> imgs; 
    private Map<String,List<Y23GoodsProperty>> propMap;
    private List<Long> propList;
    
	public Y23GoodsDetailResponse(int resCode, String resDes, Y23Goods goods) {
		super(resCode, resDes);
		this.goods = goods;
	}

	public Y23Goods getGoods() {
		return goods;
	}

	public void setGoods(Y23Goods goods) {
		this.goods = goods;
	}

	public List<Y23GoodsImages> getImgs() {
		return imgs;
	}

	public void setImgs(List<Y23GoodsImages> imgs) {
		this.imgs = imgs;
	}

	public Map<String,List<Y23GoodsProperty>> getPropMap() {
		return propMap;
	}

	public void setPropMap(Map<String,List<Y23GoodsProperty>> propMap) {
		this.propMap = propMap;
	}

	public List<Long> getPropList() {
		return propList;
	}

	public void setPropList(List<Long> propList) {
		this.propList = propList;
	}

	
}
