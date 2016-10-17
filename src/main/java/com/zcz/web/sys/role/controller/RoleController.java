package com.zcz.web.sys.role.controller;

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
import com.zcz.web.sys.role.entity.RMTree;
import com.zcz.web.sys.role.entity.SysRM;
import com.zcz.web.sys.role.entity.SysRU;
import com.zcz.web.sys.role.entity.SysRole;
import com.zcz.web.sys.role.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	RoleService roleService;

	@RequestMapping("/roleEntry")
	public String roleEntry() {
		return "system/roleList";
	}

	@RequestMapping("/roleForm")
	public String roleForm() {
		return "system/roleForm";
	}

	@RequestMapping("/roleList")
	@ResponseBody
	public RsPage findRolesByPage(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return roleService.findRolesByPage(params);
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public Object addRole(SysRole role) {
		try {
			roleService.addRole(role);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/editRole")
	@ResponseBody
	public Object editRole(SysRole role) {
		try {
			roleService.editRole(role);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmvRole")
	@ResponseBody
	public Object rmvRole(SysRole role) {
		try {
			roleService.rmvRole(role);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/ruList")
	@ResponseBody
	public List<Map> findRUsByRole(SysRole role) throws Exception {
		return roleService.findRUsByRole(role);
	}

	@RequestMapping("/addRU")
	@ResponseBody
	public Object addRU(SysRU ru) {
		try {
			roleService.addRU(ru);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmvRU")
	@ResponseBody
	public Object rmvRU(SysRU ru) {
		try {
			roleService.rmvRU(ru);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmTree")
	@ResponseBody
	public List<RMTree> findRMsByRole(String roleId) throws Exception {
		return roleService.findRMsByRole(roleId);
	}

	@RequestMapping("/addRM")
	@ResponseBody
	public Object addRM(SysRM rm) {
		try {
			roleService.addRM(rm);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

	@RequestMapping("/rmvRM")
	@ResponseBody
	public Object rmvRM(SysRM rm) {
		try {
			roleService.rmvRM(rm);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误:" + e.getMessage());
		}
	}

}
