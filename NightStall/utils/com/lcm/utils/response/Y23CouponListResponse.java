package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23Coupon;

public class Y23CouponListResponse extends ResponseInfo {
    private List<Y23Coupon> list;
    private Integer page;
    private Integer pageSize;

	public Y23CouponListResponse(int resCode, String resDes, List<Y23Coupon> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23Coupon> getList() {
		return list;
	}

	public void setList(List<Y23Coupon> list) {
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
