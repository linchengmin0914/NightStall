package com.lcm.dao.business;

import org.springframework.stereotype.Repository;

import com.lcm.dao.base.BaseDao;
import com.lcm.entity.pojo.Y23UserCoupon;

@Repository
public class Y23UserCouponDao extends BaseDao<Y23UserCoupon, Long> implements IY23UserCouponDao {
	@Override
	public String getNamespace() {
		Class<?> interfaces[] = this.getClass().getInterfaces();
		if(interfaces.length > 0) {
			super.setNamespace(interfaces[0].getName());
		}
		return super.getNamespace();
	}
}
