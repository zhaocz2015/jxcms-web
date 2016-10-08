package com.zcz.web.core.data;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class RsPage implements Serializable {

	private long total;
	private List rows;

	public RsPage(Page page) {
		this.total = page.getTotal();
		this.rows = page.getResult();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
