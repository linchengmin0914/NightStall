package com.lcm.utils.response;

import java.io.Serializable;
import java.util.List;

public class PageResponse implements Serializable {
	private int total;
	private int page;
	private int limit;
	private List data;
	
	public PageResponse() {
	}
	
	public PageResponse(int total, int page, int limit, List data) {
		this.total = total;
		this.page = page;
		this.limit = limit;
		this.data = data;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	
}
