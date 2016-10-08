package com.zcz.web.sys.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsTree;
import com.zcz.web.sys.menu.service.MenuService;

@Controller
public class MenuController extends BaseController {

	@Autowired
	MenuService menuService;

	@RequestMapping("/menuTree")
	@ResponseBody
	public List<RsTree> getMenuTree() throws Exception {
		return menuService.getMenuTree();
	}

}
