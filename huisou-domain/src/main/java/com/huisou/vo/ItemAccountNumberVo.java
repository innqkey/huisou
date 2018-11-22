package com.huisou.vo;

import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月19日 上午11:24:59 
* 类说明 
*/
public class ItemAccountNumberVo {
	/**
	 * 项目账号id
	 */
	 private Integer accountid;

	   /**
	    * 项目id
	    */
	   private Integer itemid;
	   
	   /**
	    * 用户账户表custuserid
	    */
	   private Integer custuserid;
	   
	   /**
	    * 帐号类型网站表id
	    */
	   private Integer urlid;
	   
	   /**
	    * 网址
	    */
	   private String url;
	   
	   /**
	    * 账号名称
	    */
	   private String username;
	  
	   /**
	    * 密码
	    */
	   private String password;
	   
	   /**
	    * 备注
	    */
	   private String comments;


	   /**
	    * 创建人
	    */
	   private Integer createby;

	   /**
	    * 创建时间
	    */
	   private Date createdate;

	   /**
	    * 更新人
	    */
	   private Integer updateby;

	   /**
	    * 更新时间
	    */
	   private Date updatedate;

	   /**
	    * 备用字段
	    */
	   private String standby1;

	   /**
	    * 备用字段2
	    */
	   private String standby2;
	   
	   /**
	    * 用户姓名
	    */
	   private String contact;
	  
	   /**
	    * 用户手机号
	    */
	   private String phone;
	   
	   /**
	    * 项目名称
	    */
	   private String itemname;
	   
	   /**
	    * 项目类型
	    */
	   private Integer itemtype;
	   
	   /**
	    * 项目类型名称
	    */
	   private String itemtypename;
	   
	   /**
	    * 类型名称
	    */
	   private String accountypename;
	   
	   /**
	    * 业务员Id
	    */
	   private Integer saleid;
	   
	   /**
	    * 创建人
	    */
	   private String createbyName;
	   
	   

	public String getCreatebyName() {
		return createbyName;
	}

	public void setCreatebyName(String createbyName) {
		this.createbyName = createbyName;
	}

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	public String getAccountypename() {
		return accountypename;
	}

	public void setAccountypename(String accountypename) {
		this.accountypename = accountypename;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getCustuserid() {
		return custuserid;
	}

	public void setCustuserid(Integer custuserid) {
		this.custuserid = custuserid;
	}

	public Integer getUrlid() {
		return urlid;
	}

	public void setUrlid(Integer urlid) {
		this.urlid = urlid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Integer updateby) {
		this.updateby = updateby;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public String getItemtypename() {
		return itemtypename;
	}

	public void setItemtypename(String itemtypename) {
		this.itemtypename = itemtypename;
	}
	   
}
