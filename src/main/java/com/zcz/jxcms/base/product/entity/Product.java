package com.zcz.jxcms.base.product.entity;

public class Product {

	private String id;
	private String code;
	private String name;

	private String xhgg; // 型号规格
	private String scqy; // 生产企业
	private String pzwh; // 批准文号

	private String yxq; // 有效期
	private String dw; // 单位

	private String price; // 价格

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXhgg() {
		return xhgg;
	}

	public void setXhgg(String xhgg) {
		this.xhgg = xhgg;
	}

	public String getScqy() {
		return scqy;
	}

	public void setScqy(String scqy) {
		this.scqy = scqy;
	}

	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}

	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		this.yxq = yxq;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
