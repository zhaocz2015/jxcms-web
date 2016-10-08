package com.zcz.web.sys.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;
import com.zcz.web.sys.user.entity.SysUser;
import com.zcz.web.sys.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("/userEntry")
	public String userEntry() {
		return "system/userList";
	}

	@RequestMapping("/userForm")
	public String userForm() {
		return "system/userForm";
	}

	@RequestMapping("/userList")
	@ResponseBody
	public RsPage userList(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return userService.findUsersByPage(params);
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public Object addUser(SysUser user) {
		try {
			userService.addUser(user);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editUser")
	@ResponseBody
	public Object editUser(SysUser user) {
		try {
			userService.editUser(user);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvUser")
	@ResponseBody
	public Object rmvUser(SysUser user) {
		try {
			userService.rmvUser(user);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editPwd")
	@ResponseBody
	public Object editPwd(SysUser user) {
		try {
			userService.editPwd(user);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

}
