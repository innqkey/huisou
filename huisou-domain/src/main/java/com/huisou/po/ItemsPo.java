package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_items")
public class ItemsPo {
    /**
     * 项目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemid;

    /**
     * 项目名称
     */
    private String itemname;

    /**
     * itemtype
     */
    private Integer itemtype;
    /**
     * itemtypedetail
     */
    private Integer itemtypedetail;

    /**
     * 关联客户id
     */
    private Integer custid;

    /**
     * 关联业务员id
     */
    private Integer saleid;

    /**
     * 项目开始时间
     */
    private Date itembegindate;

    /**
     * 项目结束时间
     */
    private Date itemenddate;

    /**
     * 项目状态：1正常;2待续签;3禁用
     */
    private Integer itemstatus;

    /**
     * 合同编号
     */
    private String contnum;

    /**
     * 项目要求
     */
    private String itemrequire;

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
     * 交易金额
     */
    private BigDecimal allmoney;
    
    /**
     * 开发费
     */
    private BigDecimal developmoney;
    
    /**
     * 服务费
     */
    
    private BigDecimal servicemoney;
    
    /**
     * 项目的状态：1，正常，2，禁用
     */
    private Integer status;
    
    private Integer tradetype;
    private Integer auditstatus;
    private Integer commitstatus;
    
    //标记待处理,0-未标记；1-标记待处理
    private Integer markstatus;
    //关联system系统hscom_application表status,合同状态
    private Integer relatecontstatus;
    //关联system系统hscom_application表desc,制作描述
    private String relatedesc;
    //备用字段3
    private String standby3;
    //备用字段4
    private String standby4;
    //关联system系统hscom_application表id,项目id
    private Integer relateitemid;
    
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    public Integer getMarkstatus() {
		return markstatus;
	}

	public void setMarkstatus(Integer markstatus) {
		this.markstatus = markstatus;
	}

	public Integer getRelatecontstatus() {
		return relatecontstatus;
	}

	public void setRelatecontstatus(Integer relatecontstatus) {
		this.relatecontstatus = relatecontstatus;
	}

	public String getRelatedesc() {
		return relatedesc;
	}

	public void setRelatedesc(String relatedesc) {
		this.relatedesc = relatedesc;
	}

	public String getStandby3() {
		return standby3;
	}

	public void setStandby3(String standby3) {
		this.standby3 = standby3;
	}

	public String getStandby4() {
		return standby4;
	}

	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}

	public Integer getRelateitemid() {
		return relateitemid;
	}

	public void setRelateitemid(Integer relateitemid) {
		this.relateitemid = relateitemid;
	}

	public Integer getCommitstatus() {
		return commitstatus;
	}

	public void setCommitstatus(Integer commitstatus) {
		this.commitstatus = commitstatus;
	}

	public Integer getTradetype() {
		return tradetype;
	}

	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}

	public Integer getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	/**
     * 获取项目id
     *
     * @return itemid - 项目id
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置项目id
     *
     * @param itemid 项目id
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取项目名称
     *
     * @return itemname - 项目名称
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * 设置项目名称
     *
     * @param itemname 项目名称
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * 获取itemtype
     *
     * @return itemtype - itemtype
     */
    public Integer getItemtype() {
        return itemtype;
    }

	/**
     * 设置itemtype
     *
     * @param itemtype itemtype
     */
    public void setItemtype(Integer itemtype) {
        this.itemtype = itemtype;
    }
    /**
     * 汇搜云小程序类型：1基础版  2小程序版   3高级版  4至尊版
     * @return
     */
    public Integer getItemtypedetail() {
		return itemtypedetail;
	}

	public void setItemtypedetail(Integer itemtypedetail) {
		this.itemtypedetail = itemtypedetail;
	}

	/**
     * 获取关联客户id
     *
     * @return custid - 关联客户id
     */
    public Integer getCustid() {
        return custid;
    }

    /**
     * 设置关联客户id
     *
     * @param custid 关联客户id
     */
    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    /**
     * 获取关联业务员id
     *
     * @return saleid - 关联业务员id
     */
    public Integer getSaleid() {
        return saleid;
    }

    /**
     * 设置关联业务员id
     *
     * @param saleid 关联业务员id
     */
    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    /**
     * 获取项目开始时间
     *
     * @return itembegindate - 项目开始时间
     */
    public Date getItembegindate() {
        return itembegindate;
    }

    /**
     * 设置项目开始时间
     *
     * @param itembegindate 项目开始时间
     */
    public void setItembegindate(Date itembegindate) {
        this.itembegindate = itembegindate;
    }

    /**
     * 获取项目结束时间
     *
     * @return itemenddate - 项目结束时间
     */
    public Date getItemenddate() {
        return itemenddate;
    }

    /**
     * 设置项目结束时间
     *
     * @param itemenddate 项目结束时间
     */
    public void setItemenddate(Date itemenddate) {
        this.itemenddate = itemenddate;
    }

    /**
     * 获取业务员状态：1正常;2待续签;3禁用
     *
     * @return itemstatus - 业务员状态：1正常;2待续签;3禁用
     */
    public Integer getItemstatus() {
        return itemstatus;
    }

    /**
     * 设置业务员状态：1正常;2待续签;3禁用
     *
     * @param itemstatus 业务员状态：1正常;2待续签;3禁用
     */
    public void setItemstatus(Integer itemstatus) {
        this.itemstatus = itemstatus;
    }

    /**
     * 获取合同编号
     *
     * @return contnum - 合同编号
     */
    public String getContnum() {
        return contnum;
    }

    /**
     * 设置合同编号
     *
     * @param contnum 合同编号
     */
    public void setContnum(String contnum) {
        this.contnum = contnum;
    }

    /**
     * 获取项目要求
     *
     * @return itemrequire - 项目要求
     */
    public String getItemrequire() {
        return itemrequire;
    }

    /**
     * 设置项目要求
     *
     * @param itemrequire 项目要求
     */
    public void setItemrequire(String itemrequire) {
        this.itemrequire = itemrequire;
    }

    /**
     * 获取创建人
     *
     * @return createby - 创建人
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * 设置创建人
     *
     * @param createby 创建人
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * 获取创建时间
     *
     * @return createdate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取更新人
     *
     * @return updateby - 更新人
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * 设置更新人
     *
     * @param updateby 更新人
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    /**
     * 获取更新时间
     *
     * @return updatedate - 更新时间
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置更新时间
     *
     * @param updatedate 更新时间
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取备用字段
     *
     * @return standby1 - 备用字段
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置备用字段
     *
     * @param standby1 备用字段
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 获取备用字段2
     *
     * @return standby2 - 备用字段2
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置备用字段2
     *
     * @param standby2 备用字段2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

	public BigDecimal getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}

	public BigDecimal getDevelopmoney() {
		return developmoney;
	}

	public void setDevelopmoney(BigDecimal developmoney) {
		this.developmoney = developmoney;
	}

	public BigDecimal getServicemoney() {
		return servicemoney;
	}

	public void setServicemoney(BigDecimal servicemoney) {
		this.servicemoney = servicemoney;
	}
    
}