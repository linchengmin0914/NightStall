package com.lcm.controller.wx;

/**借助此对象封装Controller方法上有
 * @ResponseBody注解的方法的返回值,
 * 目的:统一返回值类型,便于在页面上进
 * 行统一处理
 * */
public class JsonResult {
	private static final int SUCCESS=1;
	private static final int ERROR=0;
	/**状态*/
	private int state;
	/**对应状态的消息*/
	private String message;
	/**具体业务数据*/
	private Object data;
	public JsonResult(){
		this.state=SUCCESS;//1
	}
	/**此构造方法应用于data为null的场景*/
	public JsonResult(String message){
		this();
		this.message=message;
	}
	/**有具体业务数据返回时,使用此构造方法*/
	public JsonResult(Object data){
		this();
		this.data=data;
	}
	/**此方法用于两个拥有业务数据返回时合并的方法*/
	public JsonResult(Object data1,Object data2) {
		this();
		this.data = data1;
		this.data = data2;
	}
	
	/**出现异常以后要调用此方法封装异常信息*/
	public JsonResult(Throwable t){
		this.state=ERROR;
		this.message=t.getMessage();
	}
	public Object getData() {
		return data;
	}
	public int getState() {
		return state;
	}
	public String getMessage() {
		return message;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
}
