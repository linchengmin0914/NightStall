package com.lcm.utils.response;

import java.util.List;

import com.lcm.entity.pojo.Y23UserCoupon;

public class Y23UserCouponListResponse extends ResponseInfo {
	private List<Y23UserCoupon> list;
    private Integer page;
    private Integer pageSize;

	public Y23UserCouponListResponse(int resCode, String resDes, List<Y23UserCoupon> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserCoupon> getList() {
		return list;
	}

	public void setList(List<Y23UserCoupon> list) {
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
}
