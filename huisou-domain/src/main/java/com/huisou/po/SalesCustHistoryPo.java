package com.huisou.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "crm_sales_cust_history")
public class SalesCustHistoryPo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6217088264707484903L;

	/**
     * 变更流水id
     */
    @Id
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

    /**
     * 备用字段2
     */
    private String standby2;

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

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}

	

 
    
}