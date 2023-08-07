package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.Y23SystemParamDao;
import com.lcm.entity.pojo.Y23SystemParam;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class Y23SystemParamService extends BaseService {
	@Autowired
	private Y23SystemParamDao dao;
	
	public ResponseInfo update(Y23SystemParam entity) {
		ResponseInfo responseInfo = null;
		int row = dao.update(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改失败!");
		}
		
		return responseInfo;
	}
	
	public Y23SystemParam getY23SystemParamDetail(String code) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("code", code);
		List<Y23SystemParam> list = dao.selectParam(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	public Map<String, Y23SystemParam> getY23SystemParamMap(String[] codes) {
		Map<String, Y23SystemParam> map = new LinkedHashMap<String, Y23SystemParam>();
		for (int i = 0; i < codes.length; i++) {
			Y23SystemParam entity = getY23SystemParamDetail(codes[i]);
			map.put(codes[i], entity);
		}
		return map;
	}
	
	public Map<String, Y23SystemParam> getMap() {
		Map<String, Y23SystemParam> map = new LinkedHashMap<String, Y23SystemParam>();
		List<Y23SystemParam> list = dao.select();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getCode(), list.get(i));
		}
		return map;
	}
	
}
