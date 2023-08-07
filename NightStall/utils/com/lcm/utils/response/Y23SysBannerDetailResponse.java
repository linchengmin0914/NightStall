package com.lcm.utils.response;

import com.lcm.entity.pojo.Y23SysBanner;

public class Y23SysBannerDetailResponse extends ResponseInfo {
    private Y23SysBanner entity;

	public Y23SysBannerDetailResponse(int resCode, String resDes,
			Y23SysBanner entity) {
		super(resCode, resDes);
		this.entity = entity;
	}

	public Y23SysBanner getEntity() {
		return entity;
	}

	public void setEntity(Y23SysBanner entity) {
		this.entity = entity;
	}

	

}
