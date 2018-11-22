package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:34:51 
* 类说明 
*/
@Table(name = "crm_training_course")
public class TrainingCoursePo {

	/**
	 *培训课程id 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseid;
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

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
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
