package com.lcm.dao.business;

import org.springframework.stereotype.Repository;

import com.lcm.dao.base.BaseDao;
import com.lcm.entity.pojo.Right;

@Repository
public class RightDao extends BaseDao<Right, Long> implements IRightDao {
	@Override
	public String getNamespace() {
		Class<?> interfaces[] = this.getClass().getInterfaces();
		if(interfaces.length > 0) {
			super.setNamespace(interfaces[0].getName());
		}
		return super.getNamespace();
	}
}
