package com.huisou.vo;

import org.apache.commons.lang.StringUtils;

import com.common.DateUtil;
import com.common.DateUtils;
import com.huisou.constant.DictConConstant;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月20日 下午2:42:28 
* 类说明 
*/
public class VisitRecrdVo {
	
	/**
	 * 最近的一条回访记录id（用作标识）
	 */
	private Integer visitid;

	/**
	 * 项目id（用作标识）
	 */
	private Integer itemid; 
	
	/**
	 * 业务员手机号
	 */
	private String salephone;
	
	private String replycontext;
	
	private String petname;
	
	private String standby3;
	    
	public String getStandby3() {
		return standby3;
	}

	public void setStandby3(String standby3) {
		this.standby3 = standby3;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getReplycontext() {
			return replycontext;
		}

		public void setReplycontext(String replycontext) {
			this.replycontext = replycontext;
		}

	public String getSalephone() {
		return salephone;
	}

	public void setSalephone(String salephone) {
		this.salephone = salephone;
	}


	/**
	 * 联系人（客户名字）
	 */
	private String contact;
	/*
	 * 手机号（客户手机号）
	 */
	private String phone;
	
	/**
	 * 项目名称
	 */
	private String itemname;
	
	/**
	 * 服务次数
	 */
	private Integer visitcount;
	
	/**
	 * 最近联系时间
	 */
	private String createdate;
	
	/**
	 * 业务员
	 */
	private String salename;
	
	/**
	 * 售后形式
	 */
	private String visitwayname;
	/**
	 * 售后形式的标志((1,"电话回访"),visitway2(2,"到访"),visitway3(3,"邮件回访"))
	 */
	private Integer visitway;
	 public Integer getVisitway() {
		return visitway;
	}

	public void setVisitway(Integer visitway) {
		this.visitway = visitway;
	}


	/**
     * 备注
     */
    private String visitremarks;
    
	private Integer replystatus;
	
	private String replystatusname;
	
	
  

	public Integer getReplystatus() {
		return replystatus;
	}

	public void setReplystatus(Integer replystatus) {
		this.replystatus = replystatus;
	}

	public String getReplystatusname() {
		
		return DictConConstant.getDicName("replystatus", this.replystatus==null?0:this.replystatus);
	}


	public void setReplystatusname(String replystatusname) {
		this.replystatusname = replystatusname;
	}

	public Integer getVisitid() {
		return visitid;
	}

	public void setVisitid(Integer visitid) {
		this.visitid = visitid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
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

	public Integer getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(Integer visitcount) {
		this.visitcount = visitcount;
	}


	public String getVisitwayname() {
		return visitwayname;
	}

	public void setVisitwayname(String visitwayname) {
		this.visitwayname = visitwayname;
	}

	public String getVisitremarks() {
		return visitremarks;
	}

	public void setVisitremarks(String visitremarks) {
		this.visitremarks = visitremarks;
	}

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}

	public String getCreatedate() {
		if(StringUtils.isBlank(createdate)){
			return "";
		}
		try {
			String string = DateUtils.format(createdate, DateUtils.Y_M_D_HMS, "yyyy-MM-dd");
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}



	
	
}
