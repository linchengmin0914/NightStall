package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.UserRightDao;
import com.lcm.entity.pojo.UserRight;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;

@Service
public class UserRightService extends BaseService {
	@Autowired
	private UserRightDao userRightDao;
	
	public List<UserRight> getUserRightList(Map param) {
		return userRightDao.selectParam(param);
	}
	
	public ResponseInfo add(UserRight userRight) {
		ResponseInfo responseInfo = null;
		int row = userRightDao.insert(userRight);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(UserRight userRight) {
		ResponseInfo responseInfo = null;
		int row = userRightDao.update(userRight);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo delete(Long id) {
		ResponseInfo responseInfo = null;
		UserRight userRight = new UserRight();
		userRight.setId(id);
		userRight.setIsDelete(true);
		int row = userRightDao.update(userRight);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "删除失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo deleteMul(String ids) {
		ResponseInfo responseInfo = null;
		String[] arr = ids.split(",");
		for(int i = 0; i<arr.length; i++) {
			ResponseInfo res = delete(Long.parseLong(arr[i]));
			if(res.getResCode() == ResCode.FAIL) {
				return res;
			}
		}
		responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除成功!");
		return responseInfo;
	}
	
	public List<UserRight> getUserRightList() {
		return userRightDao.select();
	}

	public UserRight getUserRightDetail(Long id) {
		return userRightDao.get(id);
	}
	
	public int getUserRightCount(Map param) {
		return userRightDao.count(param);
	}
}
