package com.zcz.web.sys.dic.entity;

public class SysDicItem {

	private String id;
	private String name;
	private String val;

	private String pid;
	private int orderno;

	private String dicid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getDicid() {
		return dicid;
	}

	public void setDicid(String dicid) {
		this.dicid = dicid;
	}

}
