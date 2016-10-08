package com.zcz.web.sys.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.exception.CaptchaException;
import com.zcz.web.core.shiro.UserCaptchaToken;
import com.zcz.web.core.utils.StringUtil;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login() {
		logger.info("登录页面");
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "main/main";
		}
		return "login2";
	}

	@RequestMapping("/main")
	public String main() {
		logger.info("主页面");
		return "main/main";
	}

	@RequestMapping("/login_login")
	@ResponseBody
	public Object login(HttpServletRequest request) {
		logger.info("登录验证");
		String errMsg = "";
		UserCaptchaToken token = new UserCaptchaToken(request);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (CaptchaException e) {
			errMsg = "验证码错误";
		} catch (UnknownAccountException e) {
			errMsg = "用户名不存在";
		} catch (IncorrectCredentialsException ice) {
			errMsg = "密码错误";
		} catch (LockedAccountException e) {
			errMsg = "账户锁定状态";
		} catch (DisabledAccountException dax) {
			errMsg = "用户禁用状态";
		} catch (ExcessiveAttemptsException e) {
			errMsg = "登录失败次数过多";
		} catch (ExpiredCredentialsException eca) {
			errMsg = "用户凭证过期";
		} catch (RuntimeException e) {
			e.printStackTrace();
			errMsg = "未知错误,请联系管理员";
		} finally {
			if (StringUtil.isNotBlanks(errMsg)) {
				return renderError(errMsg);
			}
		}

		return renderSuccess();
	}

	/**
	 * 退出
	 * 
	 * @return {Result}
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Object logout(Model model) {
		logger.info("安全退出,用户名:");
		SecurityUtils.getSubject().logout();
		return renderSuccess("您已安全退出");
	}

}
