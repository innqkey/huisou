package com.huisou.vo;

import java.io.Serializable;

/**
 * 客户变更的视图
 * @author Administrator
 *
 */
public class SaleCustHistoryVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4153395392394718674L;
	private Integer saleid;
	private String beginDate;
	private String endDate;
	private String salename;
	private String changecause;
	public Integer getSaleid() {
		return saleid;
	}
	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSalename() {
		return salename;
	}
	public void setSalename(String salename) {
		this.salename = salename;
	}
	public String getChangecause() {
		return changecause;
	}
	public void setChangecause(String changecause) {
		this.changecause = changecause;
	}

}
