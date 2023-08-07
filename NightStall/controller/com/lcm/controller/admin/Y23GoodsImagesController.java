package com.lcm.controller.admin;

import java.util.LinkedHashMap;
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
import com.lcm.entity.pojo.Y23GoodsImages;
import com.lcm.service.business.Y23GoodsImagesService;
import com.lcm.service.business.Y23GoodsService;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23GoodsImagesController")
@RequestMapping("/admin/01/goods/images")
public class Y23GoodsImagesController extends BaseController {
	@Autowired
	private Y23GoodsImagesService y23GoodsImagesService;
	@Autowired
	private Y23GoodsService y23GoodsService;
	
	private String path = "01/goods-images";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23GoodsImages> list = y23GoodsImagesService.getList(param);
		PageResponse response = new PageResponse(y23GoodsImagesService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @ModelAttribute Y23GoodsImages entity) {
		ResponseInfo responseInfo = y23GoodsImagesService.add(entity);
		return responseInfo;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23GoodsImages entity = y23GoodsImagesService.getDetail(id);
		model.addAttribute("entity", entity);
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @ModelAttribute Y23GoodsImages entity) {
		ResponseInfo responseInfo = y23GoodsImagesService.update(entity);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23GoodsImagesService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23GoodsImagesService.deleteMul(ids);
		return responseInfo;
	}
	
	@RequestMapping("/picture")
	public String picture(HttpServletRequest request,Model model, @RequestParam Long goodsId) {
		model.addAttribute("goodsId", goodsId);
		Map param = new LinkedHashMap();
		param.put("goodsId", goodsId);
		List<Y23GoodsImages> pictures =  y23GoodsImagesService.getList(param);
		model.addAttribute("pictures", pictures);
		return ADMIN_PG_PREFIX + "/" + path + "/picture";
	}

	@ResponseBody
	@RequestMapping("/picture/update")
	public ResponseInfo pictureUpdate(HttpServletRequest request,Model model, @RequestParam Long goodsId, 
			@RequestParam String uploadPics, @RequestParam String isCovers) {
		ResponseInfo responseInfo = null;
		
		Y23GoodsImages image = new Y23GoodsImages();
		image.setGoodsId(goodsId);
		image.setIsDelete(true);
		y23GoodsImagesService.update(image);
		
		String[] picArr = uploadPics.split(",");
		String[] isCoversArr = isCovers.split(",");
		for (int i = 0; i < picArr.length; i++) {
			Y23GoodsImages images = new Y23GoodsImages();
			images.setGoodsId(goodsId);
			images.setLink(picArr[i]);
			images.setPosition(i + 1);
			images.setIsMaster(0);
			y23GoodsImagesService.add(images);
		}
		
		responseInfo = new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "修改图片成功");
		return responseInfo;
	}
	
	@RequestMapping("/enpicture")
	public String enpicture(HttpServletRequest request,Model model, @RequestParam Long goodsId) {
		model.addAttribute("goodsId", goodsId);
		Map param = new LinkedHashMap();
		param.put("goodsId", goodsId);
		List<Y23GoodsImages> pictures =  y23GoodsImagesService.getList(param);
		model.addAttribute("pictures", pictures);
		return ADMIN_PG_PREFIX + "/" + path + "/enpicture";
	}

	@ResponseBody
	@RequestMapping("/enpicture/update")
	public ResponseInfo enpictureUpdate(HttpServletRequest request,Model model, @RequestParam Long goodsId, 
			@RequestParam String uploadPics, @RequestParam String isCovers) {
		ResponseInfo responseInfo = null;
		
		Map param = new LinkedHashMap();
		param.put("goodsId", goodsId);
		List<Y23GoodsImages> pictures =  y23GoodsImagesService.getList(param); 
		
		String[] picArr = uploadPics.split(",");
		String[] isCoversArr = isCovers.split(",");
		
		if(pictures.size() != picArr.length) {
			return new ResponseInfo(ResponseInfo.ResCode.FAIL, "上传失败，请先上传中文图片再进行上传，并保持中英文图片张数一致");
		}
		
		for (int i = 0; i < picArr.length; i++) {
			Y23GoodsImages y23GoodsImages = new Y23GoodsImages();
			y23GoodsImages.setId(pictures.get(i).getId());
			y23GoodsImages.setEnLink(picArr[i]);
			y23GoodsImagesService.update(y23GoodsImages);
		}
		
		responseInfo = new ResponseInfo(ResponseInfo.ResCode.SUCCESS, "修改图片成功");
		return responseInfo;
	}
	
}
