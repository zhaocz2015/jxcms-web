package com.zcz.web.sys.menu.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsTree;
import com.zcz.web.sys.menu.entity.SysMenu;
import com.zcz.web.sys.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	MenuService menuService;

	@RequestMapping("/menuEntry")
	public String menuEntry() {
		return "system/menuList";
	}

	@RequestMapping("/menuForm")
	public String menuForm() {
		return "system/menuForm";
	}

	@RequestMapping("/menuTree")
	@ResponseBody
	public List<RsTree> getMenuTree(String name) throws Exception {
		return menuService.getMenuTree(true, name);
	}

	@RequestMapping("/userMenuTree")
	@ResponseBody
	public List<RsTree> getMenuTree() throws Exception {
		return menuService.getMenuTree(false, getUserId());
	}

	@RequestMapping("/addMenu")
	@ResponseBody
	public Object addMenu(SysMenu menu) {
		try {
			menuService.addMenu(menu);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editMenu")
	@ResponseBody
	public Object editMenu(SysMenu menu) {
		try {
			menuService.editMenu(menu);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvMenu")
	@ResponseBody
	public Object rmvMenu(SysMenu menu) {
		try {
			menuService.rmvMenu(menu);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/sortMenu")
	@ResponseBody
	public Object sortMenu(String jsonStr) {
		try {

			String menuStr = URLDecoder.decode(URLDecoder.decode(jsonStr, "UTF-8"), "UTF-8");
			List<RsTree> nodes = JSON.parseArray(menuStr, RsTree.class);
			menuService.sortMenu(nodes);
			return renderSuccess("排序成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("排序失败，错误：" + e.getMessage());
		}
	}

}
