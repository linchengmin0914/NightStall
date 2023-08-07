package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.RightDao;
import com.lcm.entity.pojo.Right;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class RightService extends BaseService {
	@Autowired
	private RightDao rightDao;
	
	public ResponseInfo add(Right right) {
		ResponseInfo responseInfo = null;
		int row = rightDao.insert(right);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增权限成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增权限失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Right right) {
		ResponseInfo responseInfo = null;
		int row = rightDao.update(right);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改权限成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改权限失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo delete(Long id) {
		ResponseInfo responseInfo = null;
		Right right = new Right();
		right.setId(id);
		right.setIsDelete(true);
		int row = rightDao.update(right);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除权限成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "删除权限失败!");
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
		responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除权限成功!");
		return responseInfo;
	}
	
	public List<Right> getRightList() {
		return rightDao.select();
	}

	public List<Right> getRightList(Integer page, Integer pageSize) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("page", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		return rightDao.selectParam(param);
	}
	
	public Right getRightDetail(Long id) {
		return rightDao.get(id);
	}
	
	public Right getPre(Long id) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("preObj", "true");
		param.put("id", id);
		List<Right> list = rightDao.selectParam(param);
		if(list.size() > 0) {
			return list.get(0); 
		}
		return null;
	}
	
	public Right getNext(Long id) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("nextObj", "true");
		param.put("id", id);
		List<Right> list = rightDao.selectParam(param);
		if(list.size() > 0) {
			return list.get(0); 
		}
		return null;
	}
	
	public int getRightCount(Map param) {
		return rightDao.count(param);
	}
	
	public List<Right> getRightList(Map param) {
		return rightDao.selectParam(param);
	}
}
