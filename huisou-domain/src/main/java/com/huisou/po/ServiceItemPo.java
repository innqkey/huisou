package com.huisou.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "crm_service_item")
public class ServiceItemPo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6217088264707484903L;

	/**
     * 变更流水id
     */
    @Id
    private Integer serviceid;

    /**
     * 客户id
     */
    private Integer custid;

    /**
     * 项目id
     */
    private Integer itemid;

    /**
     * 业务员id
     */
    private Integer saleid;
    
    /**
     * 用户id
     */
    private Integer userid;
    
    /**
     * 备注
     */
    private String serremark;

    /**
     * 创建人
     */
    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备用字段1
     */
    private String standby1;
    
    /**
     * 备用字段2
     */
    private String standby2;

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getSerremark() {
		return serremark;
	}

	public void setSerremark(String serremark) {
		this.serremark = serremark;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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