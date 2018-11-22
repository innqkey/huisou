package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:49:47 
* 类说明 
*/
public class ItemCourseVo implements Serializable{

	/**
	 *意向项目id 
	 */
	private Integer intentionitemid;
	/**
	 * 意向项目名称
	 */
	private String name;
	
	/**
	 * 意向项目类型
	 */
	private Integer itemtype;
	
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
     * 关联课程id courseid
     */
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
	 * 意向项目类型名称
	 */
	private String itemtypename;
    

	public String getItemtypename() {
		return itemtypename;
	}

	public void setItemtypename(String itemtypename) {
		this.itemtypename = itemtypename;
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

	
	/**
     * 备用字段2
     */
    private String standby2;

	public Integer getIntentionitemid() {
		return intentionitemid;
	}

	public void setIntentionitemid(Integer intentionitemid) {
		this.intentionitemid = intentionitemid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
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

    public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
    
    
}
