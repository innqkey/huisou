package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_renew_mark")
public class RenewMarkPo {
    /**
     * 项目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer markid;
	
    private Integer itemid;
    private Integer itemtype;
    

    /**
     * 项目名称
     */
    private String itemname;

    /**
     * 项目开始时间
     */
    private Date itembegindate;

    /**
     * 项目结束时间
     */
    private Date itemenddate;
    /**
     * 关联客户id
     */
    private Integer custid;

    private String contact;
    
    private String companyname;
    
    private String phone;
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

	/**
     * 关联业务员id
     */
    private Integer saleid;
    
    private String salename;

    
    /**
     * 创建人
     */
    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;



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

	public Date getItembegindate() {
		return itembegindate;
	}

	public void setItembegindate(Date itembegindate) {
		this.itembegindate = itembegindate;
	}

	public Date getItemenddate() {
		return itemenddate;
	}

	public void setItemenddate(Date itemenddate) {
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