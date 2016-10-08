package com.zcz.web.core.data;

import java.io.Serializable;

public class RsMsg implements Serializable {

	private boolean success;
	private String msg;
	private Object rs;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRs() {
		return rs;
	}

	public void setRs(Object rs) {
		this.rs = rs;
	}

}
