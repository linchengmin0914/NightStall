package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23UserComment;

public class Y23UserCommentListResponse extends ResponseInfo {
    private List<Y23UserComment> list;
    private Integer page;
    private Integer pageSize;

	public Y23UserCommentListResponse(int resCode, String resDes, List<Y23UserComment> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23UserComment> getList() {
		return list;
	}

	public void setList(List<Y23UserComment> list) {
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
