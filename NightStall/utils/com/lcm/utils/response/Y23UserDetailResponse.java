package com.lcm.utils.response;

import com.lcm.entity.pojo.Y23User;

public class Y23UserDetailResponse extends ResponseInfo {
    private Y23User entity;

	public Y23UserDetailResponse(int resCode, String resDes,
			Y23User entity) {
		super(resCode, resDes);
		this.entity = entity;
	}

	public Y23User getEntity() {
		return entity;
	}

	public void setEntity(Y23User entity) {
		this.entity = entity;
	}

	

}
