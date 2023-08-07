package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.RoleRightDao;
import com.lcm.entity.pojo.RoleRight;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class RoleRightService extends BaseService {
	@Autowired
	private RoleRightDao roleRightDao;
	
	public ResponseInfo add(RoleRight roleRight) {
		ResponseInfo responseInfo = null;
		int row = roleRightDao.insert(roleRight);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public List<RoleRight> getRoleRightList(Map param) {
		return roleRightDao.selectParam(param);
	}
	
	public int deleteParam(Map<String, Object> param) {
		return roleRightDao.deleteParam(param);
	}
}
