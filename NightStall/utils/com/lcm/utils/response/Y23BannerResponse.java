package com.lcm.utils.response;

import java.util.List;

import com.lcm.entity.pojo.Y23SysBanner;

public class Y23BannerResponse extends ResponseInfo {
	private List<Y23SysBanner> list;

	public Y23BannerResponse(int resCode, String resDes, List<Y23SysBanner> list) {
		super(resCode, resDes);
		this.list = list;
	}

	public List<Y23SysBanner> getList() {
		return list;
	}

	public void setList(List<Y23SysBanner> list) {
		this.list = list;
	}
}
