package com.huisou.vo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年9月14日 下午2:17:46 
* 类说明 
*/
public class EmployeeVo {
	 /**
     * 员工id
     */
    private Integer empid;

    /**
     * 名称
     */
    private String empname;

    /**
     * 手机号
     */
    private String empphone;

    /**
     * 身份证
     */
    private String empidcard;

    /**
     * 微信号
     */
    private String empweixin;

    /**
     * 邮箱
     */
    private String empemail;

    /**
     * qq号
     */
    private String empqq;

    /**
     * 入职日期
     */
    private Date entrydate;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 职务
     */
    private String duty;

    /**
     * 地址
     */
    private String address;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 员工状态：1在岗;2离职;3出差;4请假
     */
    private Integer empstatus;

    /**
     * 紧急联系人
     */
    private String urgentcont;

    /**
     * 紧急联系人手机号
     */
    private String urgentcontphone;

    /**
     * 备注
     */
    private String empremark;

    /**
     * 部门id(备用)
     */
    private Integer depid;

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
     * 部门id
     */
    private Integer orgid;
    
    /**
     * 岗位id
     */
    private Integer postid;
    
    /**
     * 员工角色
     */
    private  Integer rolevalue;
    
	public Integer getRolevalue() {
		return rolevalue;
	}

	public void setRolevalue(Integer rolevalue) {
		this.rolevalue = rolevalue;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpphone() {
		return empphone;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public String getEmpidcard() {
		return empidcard;
	}

	public void setEmpidcard(String empidcard) {
		this.empidcard = empidcard;
	}

	public String getEmpweixin() {
		return empweixin;
	}

	public void setEmpweixin(String empweixin) {
		this.empweixin = empweixin;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public String getEmpqq() {
		return empqq;
	}

	public void setEmpqq(String empqq) {
		this.empqq = empqq;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public Integer getEmpstatus() {
		return empstatus;
	}

	public void setEmpstatus(Integer empstatus) {
		this.empstatus = empstatus;
	}

	public String getUrgentcont() {
		return urgentcont;
	}

	public void setUrgentcont(String urgentcont) {
		this.urgentcont = urgentcont;
	}

	public String getUrgentcontphone() {
		return urgentcontphone;
	}

	public void setUrgentcontphone(String urgentcontphone) {
		this.urgentcontphone = urgentcontphone;
	}

	public String getEmpremark() {
		return empremark;
	}

	public void setEmpremark(String empremark) {
		this.empremark = empremark;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
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

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}
}
