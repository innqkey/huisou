package com.huisou.po;

import java.util.Date;

import javax.persistence.*;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月6日 下午2:33:23 
* 类说明 
*/
@Table(name="crm_notice")
public class NoticePo {
	
	/**
	 * 消息id
	 */
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer noticeid;
	 
	 /**
	  * 项目id
	  */
	 private Integer itemid;
	 
	 /**
	  * 项目名称
	  */
	 private String itemname;
	 
	 /**
	  * 消息类型；1-新增项目；2-需求变更；3-业务员回复；4-需业务员回复    （1/2/3为客服提醒；4为业务员提醒）
	  */
	 private Integer noticetype;
	 
	 /**
	  * 消息（1新增项目-对应项目名称；2需求变更-对应变更的需求；3业务员回复-对应业务员的回复内容；4-需业务员回复-对应客服填写的内容）
	  */
	 private String noticemessage;
	 
	 /**
	  * 消息接收人类型 （消息类型1/2/3--对应存1，表示客服接收；消息类型为4--对应存2，表示业务员接收）
	  */
	 private Integer noticeacceptype;
	 
	 /**
	  * 创建人
	  */
	 private Integer createby;
	 
	 /**
	  * 创建时间
	  */
	 private Date createdate;

	public Integer getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Integer noticeid) {
		this.noticeid = noticeid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getNoticetype() {
		return noticetype;
	}

	public void setNoticetype(Integer noticetype) {
		this.noticetype = noticetype;
	}


	public String getNoticemessage() {
		return noticemessage;
	}

	public void setNoticemessage(String noticemessage) {
		this.noticemessage = noticemessage;
	}

	public Integer getNoticeacceptype() {
		return noticeacceptype;
	}

	public void setNoticeacceptype(Integer noticeacceptype) {
		this.noticeacceptype = noticeacceptype;
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
	 
	 
}
