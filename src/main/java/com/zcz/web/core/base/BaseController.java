package com.zcz.web.core.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zcz.web.core.data.RsMsg;
import com.zcz.web.core.shiro.ShiroUser;

public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取当前用户session
	 * 
	 * @return session
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户对象shiro
	 * 
	 * @return shirouser
	 */
	public static ShiroUser getShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	/**
	 * 获取当前登录用户id
	 * 
	 * @return
	 */
	public String getUserId() {
		return getShiroUser().getUserId();
	}

	/**
	 * 获取当前登录用户名
	 * 
	 * @return
	 */
	public String getLoginName() {
		return getShiroUser().getLoginName();
	}

	public Object renderSuccess() {
		RsMsg rs = new RsMsg();
		rs.setSuccess(true);
		return rs;
	}

	public Object renderSuccess(String msg) {
		RsMsg rs = new RsMsg();
		rs.setSuccess(true);
		rs.setMsg(msg);
		return rs;
	}

	public Object renderSuccess(Object rsObj) {
		RsMsg rs = new RsMsg();
		rs.setSuccess(true);
		rs.setRs(rsObj);
		return rs;
	}

	public Object renderError() {
		RsMsg rs = new RsMsg();
		rs.setSuccess(true);
		return rs;
	}

	public Object renderError(String msg) {
		RsMsg rs = new RsMsg();
		rs.setSuccess(false);
		rs.setMsg(msg);
		return rs;
	}

}
