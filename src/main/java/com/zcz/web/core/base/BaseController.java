package com.zcz.web.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zcz.web.core.data.RsMsg;

public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

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
