package com.zcz.web.sys.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsTree;
import com.zcz.web.sys.menu.entity.SysMenu;

@Service
public class MenuService {

	@Autowired
	DaoSupport dao;

	public List<RsTree> getMenuTree() throws Exception {
		List<SysMenu> menus = (List<SysMenu>) dao.findForList("MenuMapper.findAllMenus", null);

		List<RsTree> tree = new ArrayList<>();
		for (SysMenu menu : menus) {
			RsTree tmp = new RsTree();
			tmp.setId(menu.getId());
			tmp.setText(menu.getName());
			tmp.setUrl(menu.getUrl());
			tmp.setState("open");

			tree.add(tmp);
		}

		return tree;
	}

}
