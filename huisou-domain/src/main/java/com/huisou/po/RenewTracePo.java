package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "crm_renew_trace")
public class RenewTracePo {
    /**
     * 项目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traceid;
	
    private Integer itemid;

    private Integer visitway;
    
    //续费意向：1-差；2一般；3中等；4良好；5优
    private Integer renewintention;
    
    //客户回访态度：1-差；2一般；3中等；4良好；5优
    private Integer custvistiattitude;
    
    //客户评价：1-差；2一般；3中等；4良好；5优
    private Integer custevaluate;
    
    //客户是否有新项目：无/有
    private Integer custnewitem;
    //汇搜云项目类型（项目类型）
    private Integer itemtypedetail;
	public Integer getItemtypedetail() {
		return itemtypedetail;
	}

	public void setItemtypedetail(Integer itemtypedetail) {
		this.itemtypedetail = itemtypedetail;
	}

    //客户倾向项目类型（项目类型）
    private Integer custitemtype;
    //客服评价（是否优质客户）：1-差；2一般；3中等；4良好；5优
    private Integer serviceevaluate;
    //是否续费
    private Integer custrenewstatus;


	/**
     * 项目开始时间
     */
    private Date renewitembegindate;

    /**
     * 项目结束时间
     */
    private Date renewitemenddate;

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
     * 续费时间
     */
    private Date renewdate;
    
    /**
     * 客户跟踪备注：说明客户不续费原因、续费意向等
     */
    private String tracedesc;

	public Integer getTraceid() {
		return traceid;
	}

	public void setTraceid(Integer traceid) {
		this.traceid = traceid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}


	public Integer getVisitway() {
		return visitway;
	}

	public void setVisitway(Integer visitway) {
		this.visitway = visitway;
	}

	public Integer getRenewintention() {
		return renewintention;
	}

	public void setRenewintention(Integer renewintention) {
		this.renewintention = renewintention;
	}

	public Integer getCustvistiattitude() {
		return custvistiattitude;
	}

	public void setCustvistiattitude(Integer custvistiattitude) {
		this.custvistiattitude = custvistiattitude;
	}

	public Integer getCustevaluate() {
		return custevaluate;
	}

	public void setCustevaluate(Integer custevaluate) {
		this.custevaluate = custevaluate;
	}

	public Integer getCustnewitem() {
		return custnewitem;
	}

	public void setCustnewitem(Integer custnewitem) {
		this.custnewitem = custnewitem;
	}

	public Integer getCustitemtype() {
		return custitemtype;
	}

	public void setCustitemtype(Integer custitemtype) {
		this.custitemtype = custitemtype;
	}

	public Integer getServiceevaluate() {
		return serviceevaluate;
	}

	public void setServiceevaluate(Integer serviceevaluate) {
		this.serviceevaluate = serviceevaluate;
	}

	public Integer getCustrenewstatus() {
		return custrenewstatus;
	}

	public void setCustrenewstatus(Integer custrenewstatus) {
		this.custrenewstatus = custrenewstatus;
	}

	public Date getRenewitembegindate() {
		return renewitembegindate;
	}

	public void setRenewitembegindate(Date renewitembegindate) {
		this.renewitembegindate = renewitembegindate;
	}

	public Date getRenewitemenddate() {
		return renewitemenddate;
	}

	public void setRenewitemenddate(Date renewitemenddate) {
		this.renewitemenddate = renewitemenddate;
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

	public Date getRenewdate() {
		return renewdate;
	}

	public void setRenewdate(Date renewdate) {
		this.renewdate = renewdate;
	}

	public String getTracedesc() {
		return tracedesc;
	}

	public void setTracedesc(String tracedesc) {
		this.tracedesc = tracedesc;
	}
    
}