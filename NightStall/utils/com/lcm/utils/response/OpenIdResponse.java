package com.lcm.utils.response;


public class OpenIdResponse extends ResponseInfo {
    private String openId;
    private String sessionKey;
    
	public OpenIdResponse(int resCode, String resDes, String openId,
			String sessionKey) {
		super(resCode, resDes);
		this.openId = openId;
		this.sessionKey = sessionKey;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	
}
