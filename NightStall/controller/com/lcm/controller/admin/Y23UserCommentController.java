package com.lcm.controller.admin;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23Goods;
import com.lcm.entity.pojo.Y23UserComment;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.service.business.Y23UserCommentService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.search.SearchParam;
import com.lcm.utils.task.TaskListener;

@Controller(value="adminY23UserCommentController")
@RequestMapping("/admin/01/user/comment")
public class Y23UserCommentController extends BaseController {
	@Autowired
	private Y23UserCommentService y23UserCommentService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	
	private String path = "01/comment";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model,@RequestParam(required=false) String goodsId) {
		model.addAttribute("goodsId", goodsId);
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23UserComment> list = y23UserCommentService.getList(param);
		PageResponse response = new PageResponse(y23UserCommentService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model,@RequestParam(required=false) String goodsId) {
		List<Y23Goods> goodsList = y23GoodsService.getList();
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsId", goodsId);
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserComment entity) {
		if(!StringUtils.isEmpty(entity.getGoodsIds())) {
			String[] goodsIdsArr = entity.getGoodsIds().split(",");
			for (int i = 0; i < goodsIdsArr.length; i++) {
				Y23UserComment comment = new Y23UserComment();
				comment.setGoodsId(Long.parseLong(goodsIdsArr[i]));
				comment.setUserId(1L);
				comment.setStoreId(y23GoodsService.getDetail(Long.parseLong(goodsIdsArr[i])).getStoreId());
				comment.setContent(entity.getContent());
				comment.setCreateTime(entity.getCreateTime());
				y23UserCommentService.add(comment);
			}
			return new ResponseInfo(ResCode.SUCCESS, "添加评论成功");
		} else {
			return new ResponseInfo(ResCode.FAIL, "请选择要评论的商品");
		}
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23UserComment entity = y23UserCommentService.getDetail(id);
		model.addAttribute("entity", entity);
		
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23UserComment entity) {
		ResponseInfo responseInfo = y23UserCommentService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23UserCommentService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23UserCommentService.deleteMul(ids);
		return responseInfo;
	}
	
}
