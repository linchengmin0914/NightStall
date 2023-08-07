package com.lcm.utils.response;

public class ResponseInfo {
	public static class ResCode {
		public static final int SUCCESS = 1;
		public static final int FAIL = 0;
	}
	
	private int resCode;
	private String resDes;
	
	public ResponseInfo() {
		
	}
	
	public ResponseInfo(int resCode, String resDes) {
		this.resCode = resCode;
		this.resDes = resDes;
	}
	
	public int getResCode() {
		return resCode;
	}
	public void setResCode(int resCode) {
		this.resCode = resCode;
	}
	public String getResDes() {
		return resDes;
	}
	public void setResDes(String resDes) {
		this.resDes = resDes;
	}
	
	
}
