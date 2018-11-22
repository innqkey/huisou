package com.huisou.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.huisou.constant.DictConConstant;


@Table(name = "crm_trade_record")
public class TradeCecordPo implements Serializable{
    /**
     * 交易id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeid;

    /**
     * 关联项目id
     */
    private Integer itemid;

    /**
     * 关联客户id
     */
    private Integer custid;

    /**
     * 业务员id
     */
    private Integer saleid;

    /**
     * 业务员名称
     */
    private String salename;

    /**
     * 交易金额
     */
    private BigDecimal advancemoney;

    /**
     * 付费方式：1支付宝;2微信，3网银
     */
    private Integer tradeway;

    /**
     * 付费账号
     */
    private String payaccount;

    /**
     * 评价类型：1好;2一般，3差
     */
    private Integer evaluationtype;

    /**
     * 交易日期
     */
    private String tradedate;

    /**
     * 缴费类型：1续费;2尾款;3退款
     */
    private Integer tradetype;

    /**
     * 凭证号
     */
    private String voucherno;
    
    /**
     * 审核人id(财务)
     */
    private Integer audituserid;

    /**
     * 审核人名称
     */
    private String auditusername;

    /**
     * 审核状态：1审核通过;2审核不通过
     */
    private Integer auditstatus;
    
    /**
     * 审核时间
     */
    private Date auditdate;

    /**
     * 审核备注
     */
    private String auditremarks;

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
     * 备注
     */
    private String traderemarks;
    
    public String getTraderemarks() {
		return traderemarks;
	}
	public void setTraderemarks(String traderemarks) {
		this.traderemarks = traderemarks;
	}

    /**
     * 获取交易id
     *
     * @return tradeid - 交易id
     */
    public Integer getTradeid() {
        return tradeid;
    }

    /**
     * 设置交易id
     *
     * @param tradeid 交易id
     */
    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    /**
     * 获取关联项目id
     *
     * @return itemid - 关联项目id
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置关联项目id
     *
     * @param itemid 关联项目id
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
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
     * 获取业务员id
     *
     * @return saleid - 业务员id
     */
    public Integer getSaleid() {
        return saleid;
    }

    /**
     * 设置业务员id
     *
     * @param saleid 业务员id
     */
    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    /**
     * 获取业务员名称
     *
     * @return salename - 业务员名称
     */
    public String getSalename() {
        return salename;
    }

    /**
     * 设置业务员名称
     *
     * @param salename 业务员名称
     */
    public void setSalename(String salename) {
        this.salename = salename;
    }

    /**
     * 获取交易金额
     *
     * @return advancemoney - 交易金额
     */
    public BigDecimal getAdvancemoney() {
        return advancemoney;
    }

    /**
     * 设置交易金额
     *
     * @param advancemoney 交易金额
     */
    public void setAdvancemoney(BigDecimal advancemoney) {
        this.advancemoney = advancemoney;
    }

    /**
     * 获取付费方式：1支付宝;2微信，3网银
     *
     * @return tradeway - 付费方式：1支付宝;2微信，3网银
     */
    public Integer getTradeway() {
        return tradeway;
    }

    /**
     * 设置付费方式：1支付宝;2微信，3网银
     *
     * @param tradeway 付费方式：1支付宝;2微信，3网银
     */
    public void setTradeway(Integer tradeway) {
        this.tradeway = tradeway;
    }

    /**
     * 获取付费账号
     *
     * @return payaccount - 付费账号
     */
    public String getPayaccount() {
        return payaccount;
    }

    /**
     * 设置付费账号
     *
     * @param payaccount 付费账号
     */
    public void setPayaccount(String payaccount) {
        this.payaccount = payaccount;
    }

    /**
     * 获取评价类型：1好;2一般，3差
     *
     * @return evaluationtype - 评价类型：1好;2一般，3差
     */
    public Integer getEvaluationtype() {
        return evaluationtype;
    }

    /**
     * 设置评价类型：1好;2一般，3差
     *
     * @param evaluationtype 评价类型：1好;2一般，3差
     */
    public void setEvaluationtype(Integer evaluationtype) {
        this.evaluationtype = evaluationtype;
    }

    /**
     * 获取交易日期
     *
     * @return tradedate - 交易日期
     */
    public String getTradedate() {
        return tradedate;
    }

    /**
     * 设置交易日期
     *
     * @param tradedate 交易日期
     */
    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }

    /**
     * 获取缴费类型：1续费;2尾款;3退款
     *
     * @return tradetype - 缴费类型：1续费;2尾款;3退款
     */
    public Integer getTradetype() {
        return tradetype;
    }
    
    
    public String getTradetypename(){
    	return DictConConstant.getDicName("tradetype", this.tradetype);
    }

    /**
     * 设置缴费类型：1续费;2尾款;3退款
     *
     * @param tradetype 缴费类型：1续费;2尾款;3退款
     */
    public void setTradetype(Integer tradetype) {
        this.tradetype = tradetype;
    }

    /**
     * 获取凭证号
     *
     * @return voucherno - 凭证号
     */
    public String getVoucherno() {
        return voucherno;
    }

    /**
     * 设置凭证号
     *
     * @param voucherno 凭证号
     */
    public void setVoucherno(String voucherno) {
        this.voucherno = voucherno;
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

	public Integer getAudituserid() {
		return audituserid;
	}

	public void setAudituserid(Integer audituserid) {
		this.audituserid = audituserid;
	}

	public String getAuditusername() {
		return auditusername;
	}

	public void setAuditusername(String auditusername) {
		this.auditusername = auditusername;
	}

	public Integer getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditremarks() {
		return auditremarks;
	}

	public void setAuditremarks(String auditremarks) {
		this.auditremarks = auditremarks;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	@Override
	public String toString() {
		return "TradeCecordPo [tradeid=" + tradeid + ", itemid=" + itemid
				+ ", custid=" + custid + ", saleid=" + saleid + ", salename="
				+ salename + ", advancemoney=" + advancemoney + ", tradeway="
				+ tradeway + ", payaccount=" + payaccount + ", evaluationtype="
				+ evaluationtype + ", tradedate=" + tradedate + ", tradetype="
				+ tradetype + ", voucherno=" + voucherno + ", audituserid="
				+ audituserid + ", auditusername=" + auditusername
				+ ", auditstatus=" + auditstatus + ", auditdate=" + auditdate
				+ ", auditremarks=" + auditremarks + ", createby=" + createby
				+ ", createdate=" + createdate + ", updateby=" + updateby
				+ ", updatedate=" + updatedate + ", standby1=" + standby1
				+ ", standby2=" + standby2 + "]";
	}
    
}