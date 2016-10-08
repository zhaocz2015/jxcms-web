package com.zcz.web.core.shiro;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.zcz.web.core.utils.ServletUtils;

/**
 * 扩展添加验证码-继承用户验证类
 */
public class UserCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;

	private Map<String, Object> params;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UserCaptchaToken() {
		super();
	}

	public UserCaptchaToken(String username, char[] password, String captcha, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public UserCaptchaToken(HttpServletRequest request) {
		this(request.getParameter("loginname"), request.getParameter("password").toCharArray(), request.getParameter("captcha"), true, request.getRemoteHost());
		this.params = ServletUtils.getParameters(request);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "UsernamePasswordCaptchaToken [captcha=" + captcha + ", toString()=" + super.toString() + "]";
	}

}