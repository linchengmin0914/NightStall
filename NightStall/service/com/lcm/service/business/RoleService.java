package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.RoleDao;
import com.lcm.entity.pojo.Role;
import com.lcm.entity.pojo.RoleRight;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class RoleService extends BaseService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleRightService roleRightService;
	
	public ResponseInfo add(Role role, String rightIds) {
		ResponseInfo responseInfo = null;
		int row = roleDao.insert(role);
		
		if(row > 0) {
			Map param = new LinkedHashMap();
			param.put("roleId", role.getId());
			roleRightService.deleteParam(param);
			if(rightIds != null && !"".equals(rightIds)){
				String[] arr = rightIds.split(",");
				for (int i = 0; i < arr.length; i++) {
					RoleRight roleRight = new RoleRight();
					roleRight.setRightId(Long.parseLong(arr[i]));
					roleRight.setRoleId(role.getId());
					roleRightService.add(roleRight);
				}
			}
			
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增角色成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增角色失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Role role, String rightIds) {
		ResponseInfo responseInfo = null;
		int row = roleDao.update(role);
		if(row > 0) {
			Map param = new LinkedHashMap();
			param.put("roleId", role.getId());
			roleRightService.deleteParam(param);
			if(rightIds != null && !"".equals(rightIds)){
				String[] arr = rightIds.split(",");
				for (int i = 0; i < arr.length; i++) {
					RoleRight roleRight = new RoleRight();
					roleRight.setRightId(Long.parseLong(arr[i]));
					roleRight.setRoleId(role.getId());
					roleRightService.add(roleRight);
				}
			}
			
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "修改角色成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "修改角色失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo delete(Long id) {
		ResponseInfo responseInfo = null;
		Role role = new Role();
		role.setId(id);
		role.setIsDelete(true);
		int row = roleDao.update(role);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除角色成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "删除角色失败!");
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
		responseInfo = new ResponseInfo(ResCode.SUCCESS, "删除角色成功!");
		return responseInfo;
	}
	
	public List<Role> getRoleList() {
		return roleDao.select();
	}

	public List<Role> getRoleList(Map param) {
		return roleDao.selectParam(param);
	}
	
	public Role getRoleDetail(Long id) {
		return roleDao.get(id);
	}
	
	public int getRoleCount(Map param) {
		return roleDao.count(param);
	}
}
