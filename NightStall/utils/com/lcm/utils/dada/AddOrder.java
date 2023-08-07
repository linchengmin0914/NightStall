package com.lcm.utils.dada;

public class AddOrder {
	//门店编号，门店创建后可在门店列表查看
	private String shop_no = "16280040e76a48dc"; 
	//第三方订单ID
	private String origin_id;
	//订单所在城市的code
	//"cityName": "厦门",
    //"cityCode": "0592"
	private String city_code = "0592";
	//订单金额（单位：元）
	private Double cargo_price;
	//是否需要垫付 1:是 0:否 (垫付订单金额，非运费)
	private Integer is_prepay = 0;
	//收货人姓名
	private String receiver_name;
	//收货人地址
	private String receiver_address;
	//收货人地址纬度
	private Double receiver_lat;
	//收货人地址经度
	private Double receiver_lng;
	//回调URL
	private String callback;
	//订单重量（单位：Kg）
	private Double cargo_weight;
	//收货人手机号
	private String receiver_phone;
	//收货人座机号
	private String receiver_tel;
	//小费（单位：元，精确小数点后一位，小费金额不能高于订单金额。）
	private Double tips = 0.00;
	//订单备注
	private String info;
	
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getOrigin_id() {
		return origin_id;
	}
	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public Double getCargo_price() {
		return cargo_price;
	}
	public void setCargo_price(Double cargo_price) {
		this.cargo_price = cargo_price;
	}
	public Integer getIs_prepay() {
		return is_prepay;
	}
	public void setIs_prepay(Integer is_prepay) {
		this.is_prepay = is_prepay;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public Double getReceiver_lat() {
		return receiver_lat;
	}
	public void setReceiver_lat(Double receiver_lat) {
		this.receiver_lat = receiver_lat;
	}
	public Double getReceiver_lng() {
		return receiver_lng;
	}
	public void setReceiver_lng(Double receiver_lng) {
		this.receiver_lng = receiver_lng;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public Double getCargo_weight() {
		return cargo_weight;
	}
	public void setCargo_weight(Double cargo_weight) {
		this.cargo_weight = cargo_weight;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getReceiver_tel() {
		return receiver_tel;
	}
	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}
	public Double getTips() {
		return tips;
	}
	public void setTips(Double tips) {
		this.tips = tips;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
