package com.zcz.web.core.data;

import java.util.ArrayList;
import java.util.List;

public class RsTree {

	private String id;
	private String text;
	private String state;
	private String url;

	private List<RsTree> children = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<RsTree> getChildren() {
		return children;
	}

	public void setChildren(List<RsTree> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
