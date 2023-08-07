package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23UserCart;

public class Y23UserCartListResponse extends ResponseInfo {
    private List<Y23UserCart> list;
    private Integer page;
    private Integer pageSize;

	public Y23UserCartListResponse(int resCode, String resDes, List<Y23UserCart> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserCart> getList() {
		return list;
	}

	public void setList(List<Y23UserCart> list) {
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
