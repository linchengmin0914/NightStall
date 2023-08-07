package com.lcm.service.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.Y23UserDao;
import com.lcm.entity.pojo.Y23User;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class Y23UserService extends BaseService {
	@Autowired
	private Y23UserDao dao;
	
	public ResponseInfo add(Y23User entity) {
		ResponseInfo responseInfo = null;
		int row = dao.insert(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Y23User entity) {
		ResponseInfo responseInfo = null;
		int row = dao.update(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo delete(Long id) {
		ResponseInfo responseInfo = null;
		Y23User entity = new Y23User();
		entity.setId(id);
		entity.setIsDelete(true);
		int row = dao.update(entity);
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
	
	public List<Y23User> getList() {
		return dao.select();
	}

	public Y23User getDetail(Long id) {
		return dao.get(id);
	}
	
	
	public int getCount(Map param) {
		return dao.count(param);
	}
	
	public List<Y23User> getList(Map param) {
		return dao.selectParam(param);
	}
	
	public Y23User getDetailByOpenId(String openId) {
		Map param = new HashMap();
		param.put("openId", openId);
		List<Y23User> list = getList(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public Y23User getDetailByYqCode(String yqCode) {
		Map param = new HashMap();
		param.put("yqcode", yqCode);
		List<Y23User> list = getList(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public Y23User getDetailByUsername(String username) {
		Map param = new HashMap();
		param.put("username", username);
		List<Y23User> list = getList(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
}
