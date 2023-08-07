package com.lcm.utils.response;

public class EditorResponse {
	private String original;
	private String url;
	private String title;
	private String state;
	
	public EditorResponse() {
	}
	
	
	public EditorResponse(String original, String url, String title,
			String state) {
		super();
		this.original = original;
		this.url = url;
		this.title = title;
		this.state = state;
	}
	
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
