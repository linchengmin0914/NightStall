package com.lcm.utils.response;

import java.util.List;
import java.util.Map;

import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserAddress;
import com.lcm.entity.pojo.Y23UserOrderitem;
import com.lcm.entity.pojo.Y23UserOrders;

public class Y23UserOrdersListResponse extends ResponseInfo {
    private List<Y23UserOrders> list;
    private Map<Long, List<Y23UserOrderitem>> map;
    private Integer page;
    private Integer pageSize;

	public Y23UserOrdersListResponse(int resCode, String resDes, List<Y23UserOrders> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserOrders> getList() {
		return list;
	}

	public void setList(List<Y23UserOrders> list) {
		this.list = list;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<Long, List<Y23UserOrderitem>> getMap() {
		return map;
	}

	public void setMap(Map<Long, List<Y23UserOrderitem>> map) {
		this.map = map;
	}

}
