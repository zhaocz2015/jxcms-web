package com.zcz.web.sys.role.service;

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
import com.zcz.web.core.utils.StringUtil;
import com.zcz.web.sys.role.entity.RMTree;
import com.zcz.web.sys.role.entity.SysRM;
import com.zcz.web.sys.role.entity.SysRU;
import com.zcz.web.sys.role.entity.SysRole;

@Service
public class RoleService {

	@Autowired
	DaoSupport dao;

	public RsPage findRolesByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("RoleMapper.findRolesByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addRole(SysRole role) throws Exception {
		role.setId(UUID.randomUUID().toString());
		dao.save("RoleMapper.addRole", role);
	}

	public void editRole(SysRole role) throws Exception {
		dao.update("RoleMapper.editRole", role);
	}

	public void rmvRole(SysRole role) throws Exception {
		dao.delete("RoleMapper.rmvRole", role);
		dao.delete("RoleMapper.rmvRUs", role); // 删除授权用户
		dao.delete("RoleMapper.rmvRMs", role); // 删除授权菜单
	}

	public List<Map> findRUsByRole(SysRole role) throws Exception {
		return (List<Map>) dao.findForList("RoleMapper.findRUsByRole", role);
	}

	public void addRU(SysRU ru) throws Exception {
		ru.setId(UUID.randomUUID().toString());
		dao.save("RoleMapper.addRU", ru);
	}

	public void rmvRU(SysRU ru) throws Exception {
		dao.update("RoleMapper.rmvRU", ru);
	}

	public List<RMTree> findRMsByRole(String roleId) throws Exception {
		List<Map> menus = (List<Map>) dao.findForList("RoleMapper.findRMsByRole", roleId);

		Map<String, RMTree> treeMap = new LinkedHashMap<>();
		for (Map m : menus) {
			RMTree tmp = new RMTree();
			tmp.setId((String) m.get("menu_id"));
			tmp.setText((String) m.get("name"));
			tmp.setPid((String) m.get("pid"));
			tmp.setRmId((String) m.get("rm_id"));
			tmp.setState("open");

			treeMap.put(tmp.getId(), tmp);
		}

		return mockTree(treeMap);
	}

	private List<RMTree> mockTree(Map<String, RMTree> nodeMap) {

		List<RMTree> roots = new LinkedList();
		for (Entry<String, RMTree> node : nodeMap.entrySet()) {
			RMTree treeNode = node.getValue();

			// 根级节点
			if (StringUtil.isBlanks(node.getValue().getPid())) {
				roots.add(treeNode);
				continue;
			} else {
				// 层级节点或者叶子节点
				RMTree parentNode = nodeMap.get(treeNode.getPid());
				if (parentNode != null) {
					parentNode.getChildren().add(treeNode);
				} else {
					roots.add(treeNode);
				}
			}
		}

		return roots;
	}

	public void addRM(SysRM rm) throws Exception {
		rm.setId(UUID.randomUUID().toString());
		dao.save("RoleMapper.addRM", rm);
	}

	public void rmvRM(SysRM rm) throws Exception {
		dao.update("RoleMapper.rmvRM", rm);
	}

}
