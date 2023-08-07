package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.AdminDao;
import com.lcm.entity.pojo.Admin;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;

@Service
public class AdminService extends BaseService {
	@Autowired
	private AdminDao adminDao;
	
	public List<Admin> getAdminList(Map param) {
		return adminDao.selectParam(param);
	}
	
	public Admin getAdminDetail(String nickname) {
		Map param = new LinkedHashMap();
		param.put("nickname", nickname);
		List<Admin> list = getAdminList(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public ResponseInfo add(Admin admin) {
		ResponseInfo responseInfo = null;
		int row = adminDao.insert(admin);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Admin admin) {
		ResponseInfo responseInfo = null;
		int row = adminDao.update(admin);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo delete(Long id) {
		ResponseInfo responseInfo = null;
		Admin admin = new Admin();
		admin.setId(id);
		admin.setIsDelete(true);
		int row = adminDao.update(admin);
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
	
	public List<Admin> getAdminList() {
		return adminDao.select();
	}

	public Admin getAdminDetail(Long id) {
		return adminDao.get(id);
	}
	
	public int getAdminCount(Map param) {
		return adminDao.count(param);
	}
}
