package com.huisou.vo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import com.huisou.constant.DictConConstant;

public class RenewMarkVo {
    /**
     * 项目id
     */
	private Integer markid;
	
    private Integer itemid;

    /**
     * 项目名称
     */
    private String itemname;

    /**
     * 项目开始时间
     */
    private String itembegindate;

    /**
     * 项目结束时间
     */
    private String itemenddate;
    /**
     * 关联客户id
     */
    private Integer custid;

    private String contact;
    
    private String companyname;
    /**
     * 关联业务员id
     */
    private Integer saleid;
    
    private String salename;

    private Integer itemtype;
	private Integer itemtypedetail;
	private String itemdetailname;
    private String itemtypename;
    public Integer getItemtypedetail() {
		return itemtypedetail;
	}

	public void setItemtypedetail(Integer itemtypedetail) {
		this.itemtypedetail = itemtypedetail;
	}

	public String getItemdetailname() {
		
		return DictConConstant.getDicName("ItemDetailType", itemtypedetail);
	}
	
	public void setItemdetailname(String itemdetailname) {
		this.itemdetailname = itemdetailname;
	}
    public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}
	
	//续费服务人
	private String petname;
    
    public String getItemtypename() {
    	return DictConConstant.getDicName("itemtype", this.itemtype);
	}

	public void setItemtypename(String itemtypename) {
		this.itemtypename = itemtypename;
	}

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String phone;
    /**
     * 创建人
     */
    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;

    private Integer tracenum;
    
    private Integer custrenewstatus;

	public Integer getCustrenewstatus() {
		return custrenewstatus;
	}

	public void setCustrenewstatus(Integer custrenewstatus) {
		this.custrenewstatus = custrenewstatus;
	}

	public Integer getTracenum() {
		return tracenum;
	}

	public void setTracenum(Integer tracenum) {
		this.tracenum = tracenum;
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

	public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
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

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	public String getItembegindate() {
		return itembegindate;
	}

	public void setItembegindate(String itembegindate) {
		this.itembegindate = itembegindate;
	}

	public String getItemenddate() {
		return itemenddate;
	}

	public void setItemenddate(String itemenddate) {
		this.itemenddate = itemenddate;
	}


	public Integer getMarkid() {
		return markid;
	}

	public void setMarkid(Integer markid) {
		this.markid = markid;
	}

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
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