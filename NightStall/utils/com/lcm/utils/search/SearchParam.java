package com.lcm.utils.search;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.lcm.utils.file.DateUtils;

@SuppressWarnings("unchecked")
public class SearchParam {
	//基础参数
	private Integer page;
	private Integer pageSize;
	private String orderBy;
	private String ascOrDesc;
	
	//其他参数
	private String name;
	private String parentId;
	private String nickname;
	private String type;
	private String username;
	private String userId;
	private String scopeName;
	private String materialName;
	private String title;
	private String brand;
	private String status;
	private String isOpen;
	private String tjId;
	private String startTime;
	private String endTime;
	
	private String productName;
	private String company;
	private String storeId;
	
	private String phone;
	
	private String isFree;
	
	private String bind;
	
	private String isShow;
	
	private String mealName;
	private String biaoti;
	
	private String yjcs;
	private String yjcs2;
	private String yjcs3;
	private String yjcs4;
	private String yjcs5;
	private String yjcs6;
	private String syts;
	
	private String isZd;
	
	private String bt;
	
	private String sffzsy;
	private String prices;
	private String cateId;
	private String cateName;
	private String pid;
	private String propNameId;
	
	private String isIndex;
	private String isZhdz;
	private String isSale;
	private String key;
	private String startPrice;
	private String endPrice;
	
	private String goodsId;
	private String nickName;
	private String orderno;
	private String receivername;
	private String goodsName;
	private String goodsIds;
	private String isNullPrice;
	
	private String noKey;
	private String nameKey;
	private String scope;
	
	private String fxname;
	private String fxphone;
	private String statuskey;
	private String fxstatus;
	private String express;
	
	public Map getSearchMap() {
		Map param = new LinkedHashMap();
		param.put("page", (getPage() - 1) * getPageSize());
		param.put("pageSize", getPageSize());
		if(getOrderBy() != null) {
			if(getOrderBy().indexOf("createTime") > -1) {
				setOrderBy(getOrderBy().replace("createTime", "create_time"));
			}
			
			param.put("orderBy", getOrderBy() + " " + getAscOrDesc());
		}
		param.put("name", getName());
		param.put("parentId", getParentId());
		param.put("nickname", getNickname());
		param.put("username", getUsername());
		param.put("type", getType());
		param.put("userId", getUserId());
		param.put("scopeName", getScopeName());
		param.put("materialName", getMaterialName());
		param.put("title", getTitle());
		param.put("brand", getBrand());
		param.put("status", getStatus());
		param.put("productName", getProductName());
		param.put("isOpen", getIsOpen());
		param.put("tjId", getTjId());
		param.put("startTime", getStartTime());
		param.put("endTime", getEndTime());
		param.put("company", getCompany());
		param.put("storeId", getStoreId());
		param.put("phone", getPhone());
		param.put("isFree", getIsFree());
		param.put("bind", getBind());
		param.put("isShow", getIsShow());
		param.put("mealName", getMealName());
		param.put("biaoti", getBiaoti());
		param.put("yjcs", getYjcs());
		param.put("yjcs2", getYjcs2());
		param.put("yjcs3", getYjcs3());
		param.put("yjcs4", getYjcs4());
		param.put("yjcs5", getYjcs5());
		param.put("yjcs6", getYjcs6());
		param.put("isZd", getIsZd());
		param.put("bt", getBt());
		param.put("sffzsy", getSffzsy());
		param.put("prices", getPrices());
		param.put("cateId", getCateId());
		param.put("cateName", getCateName());
		param.put("pid", getPid());
		param.put("propNameId", getPropNameId());
		param.put("isIndex", getIsIndex());
		param.put("key", getKey());
		param.put("isZhdz", getIsZhdz());
		param.put("startPrice", getStartPrice());
		param.put("endPrice", getEndPrice());
		param.put("goodsId", getGoodsId());
		param.put("nickName", getNickName());
		param.put("isSale", getIsSale());
		param.put("orderno", getOrderno());
		param.put("receivername", getReceivername());
		param.put("goodsName", getGoodsName());
		param.put("goodsIds", getGoodsIds());
		param.put("isNullPrice", getIsNullPrice());
		param.put("noKey", getNoKey());
		param.put("nameKey", getNameKey());
		param.put("scope", getScope());
		param.put("fxname", getFxname());
		param.put("fxphone", getFxphone());
		param.put("statuskey", getStatuskey());
		param.put("fxstatus", getFxstatus());
		param.put("express", getExpress());
		return param;
	}
	
	
	public Integer getPage() {
		if(page == null) {
			page = 1;
		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		if(pageSize == null) {
			pageSize = 10;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderBy() {
		if("".equals(this.orderBy)) {
			this.orderBy = null;
		}
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getAscOrDesc() {
		if("".equals(this.ascOrDesc)) {
			this.ascOrDesc = null;
		}
		return ascOrDesc;
	}
	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		if("".equals(this.name)) {
			this.name = null;
		}
		return name;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentId() {
		if("".equals(this.parentId)) {
			this.parentId = null;
		}
		return parentId;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getNickname() {
		if("".equals(this.nickname)) {
			this.nickname = null;
		}
		return nickname;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getType() {
		if("".equals(this.type)) {
			this.type = null;
		}
		return type;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsername() {
		if("".equals(this.username)) {
			this.username = null;
		}
		return username;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserId() {
		if("".equals(this.userId)) {
			this.userId = null;
		}
		return userId;
	}


	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}


	public String getScopeName() {
		if("".equals(this.scopeName)) {
			this.scopeName = null;
		}
		return scopeName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	public String getMaterialName() {
		if("".equals(this.materialName)) {
			this.materialName = null;
		}
		return materialName;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTitle() {
		if("".equals(this.title)) {
			this.title = null;
		}
		return title;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getBrand() {
		if("".equals(this.brand)) {
			this.brand = null;
		}
		return brand;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStatus() {
		if("".equals(this.status)) {
			this.status = null;
		}
		return status;
	}


	public void setProductName(String productName) {
		if("".equals(this.productName)) {
			this.productName = null;
		}
		this.productName = productName;
	}


	public String getProductName() {
		return productName;
	}


	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}


	public String getIsOpen() {
		if("".equals(this.isOpen)) {
			this.isOpen = null;
		}
		return isOpen;
	}


	public void setTjId(String tjId) {
		this.tjId = tjId;
	}


	public String getTjId() {
		if("".equals(this.tjId)) {
			this.tjId = null;
		}
		return tjId;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getStartTime() {
		if("".equals(this.startTime)) {
			this.startTime = null;
		}
		return startTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getEndTime() {
		if("".equals(this.endTime)) {
			this.endTime = null;
		}
		return endTime;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getCompany() {
		if("".equals(this.company)) {
			this.company = null;
		}
		return company;
	}


	public String getStoreId() {
		if("".equals(this.storeId)) {
			this.storeId = null;
		}
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPhone() {
		if("".equals(this.phone)) {
			this.phone = null;
		}
		return phone;
	}


	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}


	public String getIsFree() {
		if("".equals(this.isFree)) {
			this.isFree = null;
		}
		return isFree;
	}


	public void setBind(String bind) {
		this.bind = bind;
	}


	public String getBind() {
		if("".equals(this.bind)) {
			this.bind = null;
		}
		return bind;
	}


	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}


	public String getIsShow() {
		if("".equals(this.isShow)) {
			this.isShow = null;
		}
		return isShow;
	}


	public void setMealName(String mealName) {
		this.mealName = mealName;
	}


	public String getMealName() {
		if("".equals(this.mealName)) {
			this.mealName = null;
		} else {
			try {
				if(mealName != null) {
					mealName = new String(mealName.getBytes("ISO-8859-1"),"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				this.mealName = null;
			}
		}
		return mealName;
	}


	public void setBiaoti(String biaoti) {
		if("".equals(this.biaoti)) {
			this.biaoti = null;
		}
		this.biaoti = biaoti;
	}


	public String getBiaoti() {
		return biaoti;
	}


	public void setYjcs(String yjcs) {
		this.yjcs = yjcs;
	}


	public String getYjcs() {
		if("".equals(this.yjcs)) {
			this.yjcs = null;
		}
		return yjcs;
	}


	public void setYjcs2(String yjcs2) {
		this.yjcs2 = yjcs2;
	}


	public String getYjcs2() {
		if("".equals(this.yjcs2)) {
			this.yjcs2 = null;
		}
		return yjcs2;
	}


	public void setYjcs3(String yjcs3) {
		this.yjcs3 = yjcs3;
	}


	public String getYjcs3() {
		if("".equals(this.yjcs3)) {
			this.yjcs3 = null;
		}
		return yjcs3;
	}


	public void setYjcs4(String yjcs4) {
		this.yjcs4 = yjcs4;
	}


	public String getYjcs4() {
		if("".equals(this.yjcs4)) {
			this.yjcs4 = null;
		}
		return yjcs4;
	}


	public void setYjcs5(String yjcs5) {
		this.yjcs5 = yjcs5;
	}


	public String getYjcs5() {
		if("".equals(this.yjcs5)) {
			this.yjcs5 = null;
		}
		return yjcs5;
	}


	public void setYjcs6(String yjcs6) {
		this.yjcs6 = yjcs6;
	}


	public String getYjcs6() {
		if("".equals(this.yjcs6)) {
			this.yjcs6 = null;
		}
		return yjcs6;
	}


	public void setSyts(String syts) {
		try {
			if(StringUtils.isEmpty(syts)) {
				this.syts = null;
			} else {
				this.endTime = DateUtils.getPastDate(Integer.parseInt(syts));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		this.syts = syts;
	}


	public String getSyts() {
		return syts;
	}


	public void setIsZd(String isZd) {
		this.isZd = isZd;
	}


	public String getIsZd() {
		if("".equals(this.isZd)) {
			this.isZd = null;
		}
		return isZd;
	}


	public void setBt(String bt) {
		this.bt = bt;
	}


	public String getBt() {
		if("".equals(this.bt)) {
			this.bt = null;
		}
		return bt;
	}


	public void setSffzsy(String sffzsy) {
		this.sffzsy = sffzsy;
	}


	public String getSffzsy() {
		if("".equals(this.sffzsy)) {
			this.sffzsy = null;
		}
		return sffzsy;
	}


	public String getPrices() {
		if("".equals(this.prices)) {
			this.prices = null;
		}
		return prices;
	}


	public void setPrices(String prices) {
		this.prices = prices;
	}


	public String getCateId() {
		if("".equals(this.cateId)) {
			this.cateId = null;
		}
		return cateId;
	}


	public void setCateId(String cateId) {
		this.cateId = cateId;
	}


	public String getCateName() {
		if("".equals(this.cateName)) {
			this.cateName = null;
		}
		return cateName;
	}


	public void setCateName(String cateName) {
		this.cateName = cateName;
	}


	public String getPid() {
		if("".equals(this.pid)) {
			this.pid = null;
		}
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getPropNameId() {
		if("".equals(this.propNameId)) {
			this.propNameId = null;
		}
		return propNameId;
	}


	public void setPropNameId(String propNameId) {
		this.propNameId = propNameId;
	}


	public String getIsIndex() {
		if("".equals(this.isIndex)) {
			this.isIndex = null;
		}
		return isIndex;
	}


	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}


	public String getKey() {
		if("".equals(this.key)) {
			this.key = null;
		}
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getIsZhdz() {
		if("".equals(this.isZhdz)) {
			this.isZhdz = null;
		}
		return isZhdz;
	}


	public void setIsZhdz(String isZhdz) {
		this.isZhdz = isZhdz;
	}


	public String getStartPrice() {
		if("".equals(this.startPrice)) {
			this.startPrice = null;
		}
		return startPrice;
	}


	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}


	public String getEndPrice() {
		if("".equals(this.endPrice)) {
			this.endPrice = null;
		}
		return endPrice;
	}


	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}


	public String getGoodsId() {
		if("".equals(this.goodsId)) {
			this.goodsId = null;
		}
		return goodsId;
	}


	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public String getNickName() {
		if("".equals(this.nickName)) {
			this.nickName = null;
		}
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getIsSale() {
		if("".equals(this.isSale)) {
			this.isSale = null;
		}
		return isSale;
	}


	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}


	public String getOrderno() {
		if("".equals(this.orderno)) {
			this.orderno = null;
		}
		return orderno;
	}


	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}


	public String getReceivername() {
		if("".equals(this.receivername)) {
			this.receivername = null;
		}
		return receivername;
	}


	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}


	public String getGoodsName() {
		if("".equals(this.goodsName)) {
			this.goodsName = null;
		}
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getGoodsIds() {
		if("".equals(this.goodsIds)) {
			this.goodsIds = null;
		}
		return goodsIds;
	}


	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}


	public String getIsNullPrice() {
		if("".equals(this.isNullPrice)) {
			this.isNullPrice = null;
		}
		return isNullPrice;
	}


	public void setIsNullPrice(String isNullPrice) {
		this.isNullPrice = isNullPrice;
	}
	
	public String getNoKey() {
		if("".equals(this.noKey)) {
			this.noKey = null;
		}
		return noKey;
	}


	public void setNoKey(String noKey) {
		this.noKey = noKey;
	}


	public String getNameKey() {
		if("".equals(this.nameKey)) {
			this.nameKey = null;
		}
		return nameKey;
	}


	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}


	public String getScope() {
		if("".equals(this.scope)) {
			this.scope = null;
		}
		return scope;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


	public String getFxname() {
		if("".equals(this.fxname)) {
			this.fxname = null;
		}
		return fxname;
	}


	public void setFxname(String fxname) {
		this.fxname = fxname;
	}


	public String getFxphone() {
		if("".equals(this.fxphone)) {
			this.fxphone = null;
		}
		return fxphone;
	}


	public void setFxphone(String fxphone) {
		this.fxphone = fxphone;
	}


	public String getStatuskey() {
		if("".equals(this.statuskey)) {
			this.statuskey = null;
		}
		return statuskey;
	}


	public void setStatuskey(String statuskey) {
		this.statuskey = statuskey;
	}


	public String getFxstatus() {
		if("".equals(this.fxstatus)) {
			this.fxstatus = null;
		}
		return fxstatus;
	}


	public void setFxstatus(String fxstatus) {
		this.fxstatus = fxstatus;
	}


	public String getExpress() {
		if("".equals(this.express)) {
			this.express = null;
		}
		return express;
	}


	public void setExpress(String express) {
		this.express = express;
	}
}
