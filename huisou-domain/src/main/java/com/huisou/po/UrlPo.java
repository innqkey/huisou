package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午5:45:51 
* 类说明 
*/
@Table(name = "crm_url")
public class UrlPo {
	 /**
	    * 帐号类型网站id
	    */
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer urlid;

	   /**
	    * 项目帐号类型
	    */
	   private Integer accountype;
	   
	   /**
	    * 类型名称
	    */
	   private String accountypename;
	   
	   /**
	    * 网址
	    */
	   private String url;
	   
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

	public Integer getUrlid() {
		return urlid;
	}

	public void setUrlid(Integer urlid) {
		this.urlid = urlid;
	}

	public Integer getAccountype() {
		return accountype;
	}

	public void setAccountype(Integer accountype) {
		this.accountype = accountype;
	}

	public String getAccountypename() {
		return accountypename;
	}

	public void setAccountypename(String accountypename) {
		this.accountypename = accountypename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	   
	   
}
