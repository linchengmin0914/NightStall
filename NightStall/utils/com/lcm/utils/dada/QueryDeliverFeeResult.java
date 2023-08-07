package com.lcm.utils.dada;

public class QueryDeliverFeeResult {
	//响应状态，成功为"success"，失败为"fail"
	private String status;
	//响应返回码
	private Integer code;
	//响应返回码
	private String msg;
	//响应返回码
	private AddOrderResp result;
	//响应返回码
	private Integer errorCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AddOrderResp getResult() {
		return result;
	}
	public void setResult(AddOrderResp result) {
		this.result = result;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
