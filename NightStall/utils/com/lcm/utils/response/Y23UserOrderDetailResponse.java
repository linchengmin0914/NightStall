package com.lcm.utils.response;

import java.util.List;
import java.util.Map;

import com.lcm.entity.pojo.Y23Store;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;
import com.lcm.entity.pojo.Y23UserShopping;

public class Y23UserOrderDetailResponse extends ResponseInfo {
    private Y23UserOrders entity;
    private Y23UserShopping userShopping;
    private List<Y23UserOrderitem> userOrderitems;
    private String nowTime;
    private Y23Store store;
    
    private Map<String,List<Y23UserOrderitem>> itemMap;

	public Y23UserOrderDetailResponse(int resCode, String resDes,
			Y23UserOrders entity) {
		super(resCode, resDes);
		this.entity = entity;
	}

	public Y23UserOrders getEntity() {
		return entity;
	}

	public void setEntity(Y23UserOrders entity) {
		this.entity = entity;
	}

	public Y23UserShopping getUserShopping() {
		return userShopping;
	}

	public void setUserShopping(Y23UserShopping userShopping) {
		this.userShopping = userShopping;
	}

	public List<Y23UserOrderitem> getUserOrderitems() {
		return userOrderitems;
	}

	public void setUserOrderitems(List<Y23UserOrderitem> userOrderitems) {
		this.userOrderitems = userOrderitems;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public Y23Store getStore() {
		return store;
	}

	public void setStore(Y23Store store) {
		this.store = store;
	}

	public Map<String,List<Y23UserOrderitem>> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String,List<Y23UserOrderitem>> itemMap) {
		this.itemMap = itemMap;
	}

	

}
