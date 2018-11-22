package com.huisou.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
* @author qinkai 
* @date 2018年1月16日
*/

public class CustomerAccountVo {
	/**
	 * 最近7天登陆次数
	 */
	private Integer lastSevenCount;
	/**
	 * 登陆总次数
	 */
	private Integer loginCount;
	/**
	 * 最近登陆时间
	 */
	private Date lastLoginDate;
	/**
	 * 客户首页项目列表
	 */
	private List<ItemAccountNumberListVo> itemsVo = new ArrayList<ItemAccountNumberListVo>();
	
	
	public List<ItemAccountNumberListVo> getItemsVo() {
		return itemsVo;
	}
	public void setItemsVo(List<ItemAccountNumberListVo> itemsVo) {
		this.itemsVo = itemsVo;
	}
	/**
	 * 项目点击次数(帐号统计页)
	 */
	private List<ClickUrlRecordVo> clickUrlVos = new ArrayList<ClickUrlRecordVo>();
	public Integer getLastSevenCount() {
		return lastSevenCount;
	}
	public void setLastSevenCount(Integer lastSevenCount) {
		this.lastSevenCount = lastSevenCount;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public List<ClickUrlRecordVo> getClickUrlVos() {
		return clickUrlVos;
	}
	public void setClickUrlVos(List<ClickUrlRecordVo> clickUrlVos) {
		this.clickUrlVos = clickUrlVos;
	}
	
}
