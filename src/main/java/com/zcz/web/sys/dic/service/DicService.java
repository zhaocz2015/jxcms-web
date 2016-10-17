package com.zcz.web.sys.dic.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.data.RsTree;
import com.zcz.web.core.utils.StringUtil;
import com.zcz.web.sys.dic.entity.DicTree;
import com.zcz.web.sys.dic.entity.SysDic;
import com.zcz.web.sys.dic.entity.SysDicItem;

@Service
public class DicService {

	@Autowired
	DaoSupport dao;

	public RsPage findDicsByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("DicMapper.findDicsByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addDic(SysDic dic) throws Exception {
		dic.setId(UUID.randomUUID().toString());
		dao.save("DicMapper.addDic", dic);
	}

	public void editDic(SysDic dic) throws Exception {
		dao.update("DicMapper.editDic", dic);
	}

	public void rmvDic(SysDic dic) throws Exception {
		dao.delete("DicMapper.rmvDic", dic);
		dao.delete("DicMapper.rmvItems", dic); // 删除字典值集
	}

	public List findListByDic(String dicid) throws Exception {
		return (List) dao.findForList("DicMapper.findItemsByDic", dicid);
	}

	public List findTreeByDic(String dicid) throws Exception {
		List<Map> items = findListByDic(dicid);
		Map<String, DicTree> treeMap = new LinkedHashMap<>();
		for (Map item : items) {
			DicTree tmp = new DicTree();
			tmp.setId((String) item.get("id"));
			tmp.setText((String) item.get("name"));
			tmp.setName((String) item.get("name"));
			tmp.setState("open");

			tmp.setPid((String) item.get("pid"));
			tmp.setOrderno((int) item.get("orderno"));

			tmp.setVal((String) item.get("val"));

			treeMap.put((String) item.get("id"), tmp);
		}

		return mockTree(treeMap);
	}

	public void addItem(SysDicItem item) throws Exception {
		item.setId(UUID.randomUUID().toString());
		dao.save("DicMapper.addItem", item);
	}

	public void editItem(SysDicItem item) throws Exception {
		dao.update("DicMapper.editItem", item);
	}

	public void rmvItem(SysDicItem item) throws Exception {
		dao.delete("DicMapper.rmvItem", item);
	}

	public List dicCmblist(String code) throws Exception {
		List<Map> items = (List<Map>) dao.findForList("DicMapper.dicCmblist", code);
		for (Map item : items) {
			item.put("text", item.get("name"));
			item.put("value", item.get("val"));
		}

		return items;
	}

	public List dicCmbtree(String code) throws Exception {
		List<Map> items = dicCmblist(code);

		Map<String, DicTree> treeMap = new LinkedHashMap<>();
		for (Map item : items) {
			DicTree tmp = new DicTree();
			tmp.setId((String) item.get("id"));
			tmp.setText((String) item.get("name"));
			tmp.setName((String) item.get("name"));
			tmp.setState("open");

			tmp.setPid((String) item.get("pid"));
			tmp.setOrderno((int) item.get("orderno"));

			tmp.setVal((String) item.get("val"));

			treeMap.put((String) item.get("id"), tmp);
		}

		return mockTree(treeMap);
	}

	private List<DicTree> mockTree(Map<String, DicTree> nodeMap) {

		List<DicTree> roots = new LinkedList();
		for (Entry<String, DicTree> node : nodeMap.entrySet()) {
			DicTree treeNode = node.getValue();

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

}
