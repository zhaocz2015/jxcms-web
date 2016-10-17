package com.zcz.web.sys.dic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;
import com.zcz.web.sys.dic.entity.SysDic;
import com.zcz.web.sys.dic.entity.SysDicItem;
import com.zcz.web.sys.dic.service.DicService;

@Controller
@RequestMapping("/dic")
public class DicController extends BaseController {

	@Autowired
	DicService dicService;

	@RequestMapping("/dicEntry")
	public String dicEntry() {
		return "system/dicList";
	}

	@RequestMapping("/dicForm")
	public String dicForm() {
		return "system/dicForm";
	}

	@RequestMapping("/dicList")
	@ResponseBody
	public RsPage findDicsByPage(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return dicService.findDicsByPage(params);
	}

	@RequestMapping("/addDic")
	@ResponseBody
	public Object addDic(SysDic dic) {
		try {
			dicService.addDic(dic);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/editDic")
	@ResponseBody
	public Object editRole(SysDic dic) {
		try {
			dicService.editDic(dic);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmvDic")
	@ResponseBody
	public Object rmvDic(SysDic dic) {
		try {
			dicService.rmvDic(dic);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/itemForm")
	public String dicItemForm() {
		return "system/dicItemForm";
	}

	@RequestMapping("/itemList")
	@ResponseBody
	public List findListByDic(String dicid) throws Exception {
		return dicService.findListByDic(dicid);
	}

	@RequestMapping("/itemTree")
	@ResponseBody
	public List findTreeByDic(String dicid) throws Exception {
		return dicService.findTreeByDic(dicid);
	}

	@RequestMapping("/addItem")
	@ResponseBody
	public Object addItem(SysDicItem item) {
		try {
			dicService.addItem(item);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/editItem")
	@ResponseBody
	public Object editItem(SysDicItem item) {
		try {
			dicService.editItem(item);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmvItem")
	@ResponseBody
	public Object rmvItem(SysDicItem item) {
		try {
			dicService.rmvItem(item);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/dicCmblist")
	@ResponseBody
	public List dicCmblist(String code) throws Exception {
		return dicService.dicCmblist(code);
	}

	@RequestMapping("/dicCmbtree")
	@ResponseBody
	public List dicCmbtree(String code) throws Exception {
		return dicService.dicCmbtree(code);
	}

}
