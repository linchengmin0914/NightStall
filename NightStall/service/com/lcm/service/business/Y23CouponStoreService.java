package com.lcm.service.business;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.Y23CouponStoreDao;
import com.lcm.entity.pojo.Y23CouponStore;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class Y23CouponStoreService extends BaseService {
	@Autowired
	private Y23CouponStoreDao dao;
	
	public ResponseInfo add(Y23CouponStore entity) {
		ResponseInfo responseInfo = null;
		int row = dao.insert(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Y23CouponStore entity) {
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
		Y23CouponStore entity = new Y23CouponStore();
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
	
	public List<Y23CouponStore> getList() {
		return dao.select();
	}

	public Y23CouponStore getDetail(Long id) {
		return dao.get(id);
	}
	
	
	public int getCount(Map param) {
		return dao.count(param);
	}
	
	public List<Y23CouponStore> getList(Map param) {
		return dao.selectParam(param);
	}
}
