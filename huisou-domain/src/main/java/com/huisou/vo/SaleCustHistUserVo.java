package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

/**
 * 客户变更的视图
 * @author Administrator
 *
 */
public class SaleCustHistUserVo implements Serializable{
	
	private static final long serialVersionUID = -4153395392394718674L;
	/**
     * 变更流水id
     */
    private Integer salehistid;

    /**
     * 客户id
     */
    private Integer custid;

    /**
     * 变更前业务员
     */
    private Integer beforesaleid;

    /**
     * 变更后业务员
     */
    private Integer endsaleid;

    /**
     * 项目id
     */
    private Integer itemid;

    /**
     * 创建人
     */
    private Integer createby;

    /**
     * 更新时间
     */
    private Date createdate;

    /**
     * 变更原因
     */
    private String changecause;
	
	private String contact;
    private String companyname;
    private String itemname;
    private String username;
    private String beforesalename;
    private String endsalename;
	public Integer getSalehistid() {
		return salehistid;
	}
	public void setSalehistid(Integer salehistid) {
		this.salehistid = salehistid;
	}
	public Integer getCustid() {
		return custid;
	}
	public void setCustid(Integer custid) {
		this.custid = custid;
	}
	public Integer getBeforesaleid() {
		return beforesaleid;
	}
	public void setBeforesaleid(Integer beforesaleid) {
		this.beforesaleid = beforesaleid;
	}
	public Integer getEndsaleid() {
		return endsaleid;
	}
	public void setEndsaleid(Integer endsaleid) {
		this.endsaleid = endsaleid;
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
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
	public String getChangecause() {
		return changecause;
	}
	public void setChangecause(String changecause) {
		this.changecause = changecause;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBeforesalename() {
		return beforesalename;
	}
	public void setBeforesalename(String beforesalename) {
		this.beforesalename = beforesalename;
	}
	public String getEndsalename() {
		return endsalename;
	}
	public void setEndsalename(String endsalename) {
		this.endsalename = endsalename;
	}
    
}
