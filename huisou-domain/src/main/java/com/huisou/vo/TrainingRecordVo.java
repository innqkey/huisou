package com.huisou.vo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午3:07:39 
* 类说明 
*/
public class TrainingRecordVo {

	/**
	 *培训记录id 
	 */
	private Integer trainingrecordid;
	/**
	 * 培训课程id
	 */
	private Integer courseid;
	
	/**
	 * 培训次数
	 */
	private Integer trainingcount;
	
	/**
	 * 意向客户id
	 */
	private Integer intentioncustid;
	
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
     * 关联邀约人id
     */
    private Integer saleid;

    /**
     * 备用字段2
     */
    private String standby2;

    /**
     * 业务员名称
     */
    private String salename;
    /**
     * 销售员名称
     */
    private String username;
    /**
     * 课程名称
     */
    private String coursename;
    /**
     * 课程时间
     */
    private Date coursetime;
    /**
     * 课程地点
     */
    private String courseaddress;
    
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户公司
     */
    private String companyname;
    /**
     * 公司地址
     */
    private String address;
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 客户手机号
     */
    private String phone;
    
    
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

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Date getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(Date coursetime) {
		this.coursetime = coursetime;
	}

	public String getCourseaddress() {
		return courseaddress;
	}

	public void setCourseaddress(String courseaddress) {
		this.courseaddress = courseaddress;
	}

	public Integer getTrainingrecordid() {
		return trainingrecordid;
	}

	public void setTrainingrecordid(Integer trainingrecordid) {
		this.trainingrecordid = trainingrecordid;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getTrainingcount() {
		return trainingcount;
	}

	public void setTrainingcount(Integer trainingcount) {
		this.trainingcount = trainingcount;
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

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
    
}
