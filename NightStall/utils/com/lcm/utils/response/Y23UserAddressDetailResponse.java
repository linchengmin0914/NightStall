package com.lcm.utils.response;

import com.lcm.entity.pojo.Y23UserAddress;

public class Y23UserAddressDetailResponse extends ResponseInfo {
    private Y23UserAddress entity;

	public Y23UserAddressDetailResponse(int resCode, String resDes,
			Y23UserAddress entity) {
		super(resCode, resDes);
		this.entity = entity;
	}

	public Y23UserAddress getEntity() {
		return entity;
	}

	public void setEntity(Y23UserAddress entity) {
		this.entity = entity;
	}

	

}
