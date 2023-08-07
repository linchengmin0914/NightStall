package com.lcm.service.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.Y23UserShoppingDao;
import com.lcm.entity.pojo.Y23User;
import com.lcm.entity.pojo.Y23UserShopping;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class Y23UserShoppingService extends BaseService {
	@Autowired
	private Y23UserShoppingDao dao;
	
	public ResponseInfo add(Y23UserShopping entity) {
		ResponseInfo responseInfo = null;
		int row = dao.insert(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Y23UserShopping entity) {
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
		Y23UserShopping entity = new Y23UserShopping();
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
	
	public List<Y23UserShopping> getList() {
		return dao.select();
	}

	public Y23UserShopping getDetail(Long id) {
		return dao.get(id);
	}
	
	public Y23UserShopping getDetailByOrderId(Long orderId) {
		Map param = new HashMap();
		param.put("orderId", orderId);
		List<Y23UserShopping> list = getList(param);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	public int getCount(Map param) {
		return dao.count(param);
	}
	
	public List<Y23UserShopping> getList(Map param) {
		return dao.selectParam(param);
	}
}
