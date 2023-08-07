package com.lcm.utils.response;

import java.util.List;
import java.util.Map;

import com.lcm.entity.pojo.Y23DadaStore;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.entity.pojo.Y23GoodsProperty;
import com.lcm.utils.dada.DaDaUtils;
import com.lcm.utils.dada.OrderDetail;

public class OrderSendInfoResponse extends ResponseInfo {
    private OrderDetail entity;
    private Y23DadaStore store;
    
	public OrderSendInfoResponse(int resCode, String resDes, OrderDetail entity) {
		super(resCode, resDes);
		this.setEntity(entity);
	}

	public OrderDetail getEntity() {
		return entity;
	}

	public void setEntity(OrderDetail entity) {
		this.entity = entity;
	}

	public Y23DadaStore getStore() {
		return store;
	}

	public void setStore(Y23DadaStore store) {
		this.store = store;
	}

}
