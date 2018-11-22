package com.huisou.vo;

public class HscomApplicationVo {

	private Integer id;
//	private String number;
//	private String name;
	private String shortname;
//	private Integer version;
//	private Integer ios;
//	private Integer appreciation;
//	private Integer android;
//	private String requirements;
//	private Integer color;
//	private String contract;
	private Integer cust_id;
//	private Integer user_id;
	private Integer status;
	private String domain;
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	
}
