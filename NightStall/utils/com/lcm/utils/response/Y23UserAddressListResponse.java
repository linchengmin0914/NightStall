package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserAddress;

public class Y23UserAddressListResponse extends ResponseInfo {
    private List<Y23UserAddress> list;
    private Integer page;
    private Integer pageSize;

	public Y23UserAddressListResponse(int resCode, String resDes, List<Y23UserAddress> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserAddress> getList() {
		return list;
	}

	public void setList(List<Y23UserAddress> list) {
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
