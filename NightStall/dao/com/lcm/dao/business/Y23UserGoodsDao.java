package com.lcm.dao.business;

import org.springframework.stereotype.Repository;

import com.lcm.dao.base.BaseDao;
import com.lcm.entity.pojo.Y23UserGoods;

@Repository
public class Y23UserGoodsDao extends BaseDao<Y23UserGoods, Long> implements IY23UserGoodsDao {
	@Override
	public String getNamespace() {
		Class<?> interfaces[] = this.getClass().getInterfaces();
		if(interfaces.length > 0) {
			super.setNamespace(interfaces[0].getName());
		}
		return super.getNamespace();
	}
}
