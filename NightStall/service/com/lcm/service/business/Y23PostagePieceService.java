package com.lcm.service.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcm.dao.business.Y23PostagePieceDao;
import com.lcm.entity.pojo.Y23PostagePiece;
import com.lcm.service.base.BaseService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;

@Service
public class Y23PostagePieceService extends BaseService {
	@Autowired
	private Y23PostagePieceDao dao;
	
	public ResponseInfo add(Y23PostagePiece entity) {
		ResponseInfo responseInfo = null;
		int row = dao.insert(entity);
		if(row > 0) {
			responseInfo = new ResponseInfo(ResCode.SUCCESS, "新增成功!");
		} else {
			responseInfo = new ResponseInfo(ResCode.FAIL, "新增失败!");
		}
		
		return responseInfo;
	}
	
	public ResponseInfo update(Y23PostagePiece entity) {
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
		Y23PostagePiece entity = new Y23PostagePiece();
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
	
	public List<Y23PostagePiece> getList() {
		return dao.select();
	}

	public List<Y23PostagePiece> getList(Integer page, Integer pageSize) {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("page", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		return dao.selectParam(param);
	}
	
	public Y23PostagePiece getDetail(Long id) {
		return dao.get(id);
	}
	
	public int getCount(Map param) {
		return dao.count(param);
	}
	
	public List<Y23PostagePiece> getList(Map param) {
		return dao.selectParam(param);
	}
}
