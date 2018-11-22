package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午5:42:32 
* 类说明 
*/
@Table(name = "crm_click_url_record")
public class ClickUrlRecordPo {

	 /**
	    * 点击链接记录id
	    */
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer clickid;

	   /**
	    * 项目帐号id
	    */
	   private Integer accountid;
	   
	   /**
	    * 项目id
	    */
	   private Integer itemid;
	   /**
	    * 项目类型
	    */
	   private Integer itemtype;
	   
	   /**
	    * 用户账户表custuserid
	    */
	   private Integer custuserid;
	   
	   /**
	    * 帐号类型网站表id
	    */
	   private Integer urlid;
	   
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
	   

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public Integer getClickid() {
		return clickid;
	}

	public void setClickid(Integer clickid) {
		this.clickid = clickid;
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
