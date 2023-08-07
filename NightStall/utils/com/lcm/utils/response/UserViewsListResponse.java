package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23UserViews;

public class UserViewsListResponse extends ResponseInfo {
    private List<Y23UserViews> list;
    private Integer page;
    private Integer pageSize;

	public UserViewsListResponse(int resCode, String resDes, List<Y23UserViews> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserViews> getList() {
		return list;
	}

	public void setList(List<Y23UserViews> list) {
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
