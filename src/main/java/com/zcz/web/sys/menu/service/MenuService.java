package com.zcz.web.sys.menu.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsTree;
import com.zcz.web.core.utils.StringUtil;
import com.zcz.web.sys.menu.entity.SysMenu;

@Service
public class MenuService {

	@Autowired
	DaoSupport dao;

	public List<RsTree> getMenuTree(boolean isAll, String param) throws Exception {
		List<SysMenu> menus = (List<SysMenu>) dao.findForList(isAll ? "MenuMapper.findAllMenus" : "MenuMapper.findUserMenus", param);

		Map<String, RsTree> treeMap = new LinkedHashMap<>();
		for (SysMenu menu : menus) {
			RsTree tmp = new RsTree();
			tmp.setId(menu.getId());
			tmp.setText(menu.getName());
			tmp.setUrl(menu.getUrl());
			tmp.setState("open");

			tmp.setName(menu.getName());
			tmp.setPid(menu.getPid());
			tmp.setOrderno(menu.getOrderno());

			treeMap.put(menu.getId(), tmp);
		}

		return mockTree(treeMap);
	}

	private List<RsTree> mockTree(Map<String, RsTree> nodeMap) {

		List<RsTree> roots = new LinkedList();
		for (Entry<String, RsTree> node : nodeMap.entrySet()) {
			RsTree treeNode = node.getValue();

			// 根级节点
			if (StringUtil.isBlanks(node.getValue().getPid())) {
				roots.add(treeNode);
				continue;
			} else {
				// 层级节点或者叶子节点
				RsTree parentNode = nodeMap.get(treeNode.getPid());
				if (parentNode != null) {
					parentNode.getChildren().add(treeNode);
				} else {
					roots.add(treeNode);
				}
			}
		}

		return roots;
	}

	public void addMenu(SysMenu menu) throws Exception {
		SysMenu rsMenu = (SysMenu) dao.findForObject("MenuMapper.findMenuByname", menu.getName());
		if (rsMenu != null) {
			throw new Exception("菜单已存在");
		}
		menu.setId(UUID.randomUUID().toString());
		dao.save("MenuMapper.addMenu", menu);
	}

	public void editMenu(SysMenu menu) throws Exception {
		dao.update("MenuMapper.editMenu", menu);
	}

	public void rmvMenu(SysMenu menu) throws Exception {
		dao.delete("MenuMapper.rmvMenu", menu);
		dao.delete("MenuMapper.rmvSubMenus", menu);
	}

	public void sortMenu(List<RsTree> nodes) throws Exception {
		int index = 1;
		for (RsTree node : nodes) {
			node.setOrderno(index++);
			dao.update("MenuMapper.sortMenu", node);
		}
	}

}
