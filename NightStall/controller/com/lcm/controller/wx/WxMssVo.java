package com.lcm.controller.wx;

import java.util.Map;

/**
 * 封装入参的类 注意：根据官方文档提示，这些属性 必须为此写法，否则会报错
 */
public class WxMssVo {
    /**
     * 接口调用凭证
     */
    private String access_token;
    /**
     * 接收者（用户）的 openid
     */
    private String touser;
    /**
     * 所需下发的模板消息的id
     */
    private String template_id;
    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     */
    private String page;
    /**
     * 模板内容，不填则下发空模板
     */
    private Map<String, TemplateData> data;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Map<String, TemplateData> getData() {
		return data;
	}
	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}
    
    
}