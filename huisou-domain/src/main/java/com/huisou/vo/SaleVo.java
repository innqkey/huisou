package com.huisou.vo;

import java.io.Serializable;

public class SaleVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5995473876802427362L;
	private Integer saleid;
	private String salephone;
	public Integer getSaleid() {
		return saleid;
	}
	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}
	public String getSalephone() {
		return salephone;
	}
	public void setSalephone(String salephone) {
		this.salephone = salephone;
	}
	
}
