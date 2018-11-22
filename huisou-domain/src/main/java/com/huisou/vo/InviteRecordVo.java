package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月27日 上午10:07:06 
* 类说明 
*/
public class InviteRecordVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8977510022919594926L;
	/**
	 * 邀请记录id
	 */
	private Integer inviterecordid;
	/**
	 * 培训课程id
	 */
	private Integer courseid;
	
	/**
	 * 应约次数
	 */
	private Integer inviteidcount;
	
	/**
	 * 意向客户id
	 */
	private Integer intentioncustid;
	
	/**
     * 创建人（关联userid，）
     */
    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
	 * 意向客户姓名
	 */
	private String name;
	
	/**
	 * 意向客户手机号
	 */
	private String phone;
	
	/**
	 * 意向客户公司名称
	 */
	private String companyname;
	
	/**
	 * 意向客户公司地址
	 */
	private String address;
	
	/**
	 * 培训课程名称
	 */
	private String coursename;
	
	/**
	 * 培训课程地址
	 */
	private String courseaddress;
	
	/**
	 * 培训课程天数
	 */
	private String coursedays;
	
    /**
     * 培训课程时间
     */
    private Date coursetime;
    
    /**
     * 业务员姓名（也是邀约人）
     */
    private String petname;

	

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public Integer getInviterecordid() {
		return inviterecordid;
	}

	public void setInviterecordid(Integer inviterecordid) {
		this.inviterecordid = inviterecordid;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getInviteidcount() {
		return inviteidcount;
	}

	public void setInviteidcount(Integer inviteidcount) {
		this.inviteidcount = inviteidcount;
	}

	public Integer getIntentioncustid() {
		return intentioncustid;
	}

	public void setIntentioncustid(Integer intentioncustid) {
		this.intentioncustid = intentioncustid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseaddress() {
		return courseaddress;
	}

	public void setCourseaddress(String courseaddress) {
		this.courseaddress = courseaddress;
	}

	public String getCoursedays() {
		return coursedays;
	}

	public void setCoursedays(String coursedays) {
		this.coursedays = coursedays;
	}

	public Date getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(Date coursetime) {
		this.coursetime = coursetime;
	}
    
    
}
