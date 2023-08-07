package com.lcm.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23GoodsCollect;
import com.lcm.service.business.Y23GoodsCollectService;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;

@Controller(value="bY23GoodsCollectController")
@RequestMapping("/y23/api/collect")
public class Y23GoodsCollectController extends BaseController {
	@Autowired
	private Y23GoodsCollectService y23GoodsCollectService;
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23GoodsCollect entity, 
			@RequestParam Long userId) {
		entity.setUserId(userId);
		ResponseInfo responseInfo = y23GoodsCollectService.add(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @ModelAttribute Y23GoodsCollect entity, 
			@RequestParam Long userId) {
		ResponseInfo responseInfo = new ResponseInfo(ResCode.SUCCESS, "");
		Map cparam = new HashMap();
		cparam.put("userId", userId);
		cparam.put("goodsId", entity.getGoodsId());
		List<Y23GoodsCollect> list = y23GoodsCollectService.getList(cparam);
		
		if(list.size() > 0) {
			Y23GoodsCollect y23GoodsCollect = list.get(0);
			y23GoodsCollect.setIsDelete(true);
			responseInfo = y23GoodsCollectService.delete(y23GoodsCollect.getId());
		}
		
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/check")
	public ResponseInfo check(HttpServletRequest request,Model model, @RequestParam Long userId, 
			@RequestParam String goodsId) {
		Map cparam = new HashMap();
		cparam.put("userId", userId);
		cparam.put("goodsId", goodsId);
		Integer allNum = y23GoodsCollectService.getCount(cparam);
		return new ResponseInfo(ResCode.SUCCESS, allNum + "");
	}
	
}
