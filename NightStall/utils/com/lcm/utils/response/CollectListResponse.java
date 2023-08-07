package com.lcm.utils.response;

import java.util.List;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsCollect;

public class CollectListResponse extends ResponseInfo {
    private List<Y23GoodsCollect> list;
    private Integer page;
    private Integer pageSize;

	public CollectListResponse(int resCode, String resDes, List<Y23GoodsCollect> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23GoodsCollect> getList() {
		return list;
	}

	public void setList(List<Y23GoodsCollect> list) {
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
