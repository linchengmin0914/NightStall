package com.lcm.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lcm.controller.base.BaseController;
import com.lcm.entity.pojo.Y23PostageArea;
import com.lcm.entity.pojo.Y23PostagePiece;
import com.lcm.entity.pojo.Y23PostageTemplete;
import com.lcm.service.business.Y23PostageAreaService;
import com.lcm.service.business.Y23PostagePieceService;
import com.lcm.service.business.Y23PostageTempleteService;
import com.lcm.utils.request.PostageBean;
import com.lcm.utils.response.PageResponse;
import com.lcm.utils.response.ResponseInfo;
import com.lcm.utils.response.ResponseInfo.ResCode;
import com.lcm.utils.search.SearchParam;

@Controller(value="adminY23PostageController")
@RequestMapping("/admin/01/postage")
public class Y23PostageController extends BaseController {
	@Autowired
	private Y23PostageTempleteService y23PostageTempleteService;
	@Autowired
	private Y23PostageAreaService y23PostageAreaService;
	@Autowired
	private Y23PostagePieceService y23PostagePieceService;
	
	private String path = "01/postage";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path + "/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResponse list(HttpServletRequest request,Model model, @ModelAttribute SearchParam searchParam) {
		Map param = searchParam.getSearchMap();
		List<Y23PostageTemplete> list = y23PostageTempleteService.getList(param);
		PageResponse response = new PageResponse(y23PostageTempleteService.getCount(param), searchParam.getPage(), searchParam.getPageSize(), list);
		return response;
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
		return ADMIN_PG_PREFIX + "/" + path +  "/add";
	}
	
	@ResponseBody
	@RequestMapping("/add/done")
	public ResponseInfo addDone(HttpServletRequest request,Model model, @RequestBody PostageBean entity) {
		try {
			Y23PostageTemplete y23PostageTemplete = entity.getY23PostageTemplete();
			y23PostageTemplete.setIsOpen(false);
			y23PostageTempleteService.add(y23PostageTemplete);
			
			List<Y23PostageArea> y23PostageAreas = entity.getY23PostageAreas();
			for (Y23PostageArea y23PostageArea:y23PostageAreas) {
				y23PostageArea.setTempId(y23PostageTemplete.getId());
				y23PostageAreaService.add(y23PostageArea);
			}
			
			List<Y23PostagePiece> y23PostagePieces = entity.getY23PostagePieces();
			for (Y23PostagePiece y23PostagePiece:y23PostagePieces) {
				y23PostagePiece.setTempId(y23PostageTemplete.getId());
				y23PostagePieceService.add(y23PostagePiece);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseInfo(ResCode.SUCCESS, "添加模板成功");
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,Model model, @RequestParam Long id) {
		Y23PostageTemplete entity = y23PostageTempleteService.getDetail(id);
		model.addAttribute("entity", entity);
		
		Map param = new HashMap();
		param.put("tempId", id);
		List<Y23PostageArea> y23PostageAreas = y23PostageAreaService.getList(param);
		model.addAttribute("y23PostageAreas", y23PostageAreas);
		List<Y23PostagePiece> y23PostagePieces = y23PostagePieceService.getList(param);
		model.addAttribute("y23PostagePieces", y23PostagePieces);
		
		return ADMIN_PG_PREFIX + "/" + path + "/update";
	}
	
	@ResponseBody
	@RequestMapping("/openclose/done")
	public ResponseInfo opencloseDone(HttpServletRequest request,Model model, @RequestParam Long id,@RequestParam Boolean isOpen) {
		try {
			Y23PostageTemplete y23PostageTemplete = y23PostageTempleteService.getDetail(id);
			y23PostageTemplete.setIsOpen(isOpen);
			y23PostageTempleteService.update(y23PostageTemplete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseInfo(ResCode.SUCCESS, "修改模板成功");
	}
	
	@ResponseBody
	@RequestMapping("/update/done")
	public ResponseInfo updateDone(HttpServletRequest request,Model model, @RequestBody PostageBean entity) {
		try {
			Y23PostageTemplete y23PostageTemplete = entity.getY23PostageTemplete();
			y23PostageTempleteService.update(y23PostageTemplete);
			
			List<Y23PostageArea> y23PostageAreas = entity.getY23PostageAreas();
			for (Y23PostageArea y23PostageArea:y23PostageAreas) {
				if(StringUtils.isEmpty(y23PostageArea.getId())) {
					y23PostageArea.setTempId(y23PostageTemplete.getId());
					y23PostageAreaService.add(y23PostageArea);
				} else {
					y23PostageAreaService.update(y23PostageArea);
				}
				
			}
			
			List<Y23PostagePiece> y23PostagePieces = entity.getY23PostagePieces();
			for (Y23PostagePiece y23PostagePiece:y23PostagePieces) {
				if(StringUtils.isEmpty(y23PostagePiece.getId())) {
					y23PostagePiece.setTempId(y23PostageTemplete.getId());
					y23PostagePieceService.add(y23PostagePiece);
				} else {
					y23PostagePieceService.update(y23PostagePiece);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseInfo(ResCode.SUCCESS, "修改模板成功");
	}
	
	@ResponseBody
	@RequestMapping("/delete/done")
	public ResponseInfo deleteDone(HttpServletRequest request,Model model, @RequestParam Long id) {
		ResponseInfo responseInfo = y23PostageTempleteService.delete(id);
		return responseInfo;
	}
	
	@ResponseBody
	@RequestMapping("/delete/mul/done")
	public ResponseInfo deleteMulDone(HttpServletRequest request,Model model, @RequestParam String ids) {
		ResponseInfo responseInfo = y23PostageTempleteService.deleteMul(ids);
		return responseInfo;
	}
	
}
