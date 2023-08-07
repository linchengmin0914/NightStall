package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcm.dao.business.Y23GoodsDao;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;


@Service
public class Y23GoodsService extends BaseService {
	@Autowired
	private Y23GoodsDao dao;
	
	public ResponseInfo add(Y23Goods entity) {
		ResponseInfo responseInfo = null;
		int row = dao.insert(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Y23Goods entity) {
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
		Y23Goods entity = new Y23Goods();
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
	
	public ResponseInfo saleMul(String ids) {
		ResponseInfo responseInfo = null;
		String[] arr = ids.split(",");
		for(int i = 0; i<arr.length; i++) {
			Y23Goods entity = getDetail(Long.parseLong(arr[i]));
			entity.setIsSale(1);
			ResponseInfo res = update(entity);
			if(res.getResCode() == ResCode.FAIL) {
				return res;
			}
		}
		responseInfo = new ResponseInfo(ResCode.SUCCESS, "上架成功!");
		return responseInfo;
	}
	
	public ResponseInfo notSaleMul(String ids) {
		ResponseInfo responseInfo = null;
		String[] arr = ids.split(",");
		for(int i = 0; i<arr.length; i++) {
			Y23Goods entity = getDetail(Long.parseLong(arr[i]));
			entity.setIsSale(0);
			ResponseInfo res = update(entity);
			if(res.getResCode() == ResCode.FAIL) {
				return res;
			}
		}
		responseInfo = new ResponseInfo(ResCode.SUCCESS, "下架成功!");
		return responseInfo;
	}
	
	public List<Y23Goods> getList() {
		return dao.select();
	}

	public Y23Goods getDetail(Long id) {
		return dao.get(id);
	}
	
	
	public int getCount(Map param) {
		return dao.count(param);
	}
	
	public List<Y23Goods> getList(Map param) {
		return dao.selectParam(param);
	}
}
