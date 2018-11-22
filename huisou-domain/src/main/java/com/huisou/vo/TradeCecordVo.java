package com.huisou.vo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import com.huisou.constant.DictConConstant;

public class TradeCecordVo{
    /**
     * 交易id
     */
    private Integer tradeid;

    /**
     * 关联项目id
     */
    private Integer itemid;

    /**
     * 交易金额
     */
    private BigDecimal advancemoney;

    /**
     * 付费方式：1支付宝;2微信;3网银
     */
    private Integer tradeway;
    
    private String tradewayname;

    /**
     * 付费账号
     */
    private String payaccount;

    /**
     * 评价类型：1好;2一般;3差
     */
    private Integer evaluationtype;
    
    private String evaluationtypename;

    /**
     * 交易日期
     */
    private String tradedate;

    /**
     * 缴费类型：1续费;2尾款;3退款
     */
    private Integer tradetype;
    
    private String tradetypename;

    /**
     * 凭证号
     */
    private String voucherno;
    
    /**
     * 审核人名称
     */
    private String auditusername;

    /**
     * 审核状态：1审核通过;2审核不通过
     */
    private Integer auditstatus;
    
    /**
     * 审核状态：1审核通过;2审核不通过
     */
    private String auditstatusname;
    
    private Date auditdate;
    
    private String salename;
    
    private String traderemarks;
    
    private Date createdate;

    public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getTraderemarks() {
		return traderemarks;
	}

	public void setTraderemarks(String traderemarks) {
		this.traderemarks = traderemarks;
	}

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
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

	public String getTradewayname() {
		return DictConConstant.getDicName("tradeway",this.tradeway);
	}

	public void setTradewayname(String tradewayname) {
		this.tradewayname = tradewayname;
	}

	public String getEvaluationtypename() {
		return DictConConstant.getDicName("evaluationtype",this.evaluationtype);
	}

	public void setEvaluationtypename(String evaluationtypename) {
		this.evaluationtypename = evaluationtypename;
	}

	public String getTradetypename() {
		return DictConConstant.getDicName("tradetype",this.tradetype);
	}

	public void setTradetypename(String tradetypename) {
		this.tradetypename = tradetypename;
	}

	public String getAuditstatusname() {
		return DictConConstant.getDicName("auditstatus",this.auditstatus);
	}

	public void setAuditstatusname(String auditstatusname) {
		this.auditstatusname = auditstatusname;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

}