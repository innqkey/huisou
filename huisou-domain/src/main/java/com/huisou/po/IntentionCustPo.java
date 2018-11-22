package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:09:56 
* 类说明 
*/
@Table(name = "crm_intention_cust")
public class IntentionCustPo {
	/**
	 *意向客户id 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer intentioncustid;
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

	public Integer getIntentioncustid() {
		return intentioncustid;
	}

	public void setIntentioncustid(Integer intentioncustid) {
		this.intentioncustid = intentioncustid;
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
