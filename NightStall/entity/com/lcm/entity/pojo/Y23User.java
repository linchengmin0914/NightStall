package com.lcm.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Y23User {
	private Long id;
	private String username;
	private String pwd;
	private String name;
	private String cover;
	private String nickName;
	private String phone;
	private String openId;
	private String fxname;
	private String fxphone;
	private Integer status;
	private Long refereeId;
	private String refereeName;
	private Integer fxlevel;
	private Date joinTime;
	private Double money;
	private String yqcode;
	private String byqcode;
	private Boolean isAdmin;
	private Boolean isDelete;
    private Date createTime;
    private Date modifyTime;
    
    private Double fxMoney;
    private Double txzMoney;
    private Double ytzMoney;
    private Double todayMoney;
    private Double saleMoney;
    private String joinTimeStr;
    private Integer cashoutNum;
    private Integer myTeamNum;
    private Integer orderNum;
    private Double shareMoney;
    private Integer shareNum;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getFxname() {
		return fxname;
	}
	public void setFxname(String fxname) {
		this.fxname = fxname;
	}
	public String getFxphone() {
		return fxphone;
	}
	public void setFxphone(String fxphone) {
		this.fxphone = fxphone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Long refereeId) {
		this.refereeId = refereeId;
	}
	public Integer getFxlevel() {
		return fxlevel;
	}
	public void setFxlevel(Integer fxlevel) {
		this.fxlevel = fxlevel;
	}
	public String getRefereeName() {
		return refereeName;
	}
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getTxzMoney() {
		return txzMoney;
	}
	public void setTxzMoney(Double txzMoney) {
		this.txzMoney = txzMoney;
	}
	public Double getYtzMoney() {
		return ytzMoney;
	}
	public void setYtzMoney(Double ytzMoney) {
		this.ytzMoney = ytzMoney;
	}
	public Double getFxMoney() {
		return fxMoney;
	}
	public void setFxMoney(Double fxMoney) {
		this.fxMoney = fxMoney;
	}
	public String getJoinTimeStr() {
		if(this.joinTime != null) this.joinTimeStr = new SimpleDateFormat("yyyy年MM月dd日").format(this.joinTime);
		return joinTimeStr;
	}
	public void setJoinTimeStr(String joinTimeStr) {
		this.joinTimeStr = joinTimeStr;
	}
	public Double getTodayMoney() {
		return todayMoney;
	}
	public void setTodayMoney(Double todayMoney) {
		this.todayMoney = todayMoney;
	}
	public Double getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}
	public Integer getCashoutNum() {
		return cashoutNum;
	}
	public void setCashoutNum(Integer cashoutNum) {
		this.cashoutNum = cashoutNum;
	}
	public Integer getMyTeamNum() {
		return myTeamNum;
	}
	public void setMyTeamNum(Integer myTeamNum) {
		this.myTeamNum = myTeamNum;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getYqcode() {
		return yqcode;
	}
	public void setYqcode(String yqcode) {
		this.yqcode = yqcode;
	}
	public String getByqcode() {
		return byqcode;
	}
	public void setByqcode(String byqcode) {
		this.byqcode = byqcode;
	}
	public Double getShareMoney() {
		return shareMoney;
	}
	public void setShareMoney(Double shareMoney) {
		this.shareMoney = shareMoney;
	}
	public Integer getShareNum() {
		return shareNum;
	}
	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
    
    
}
