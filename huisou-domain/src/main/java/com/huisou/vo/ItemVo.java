package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;


public class ItemVo implements Serializable {
	
	public String getStandby4() {
		return standby4;
	}

	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}

	private Integer itemid;

    /**name     : '',				//姓名
					phone    : '',				//手机
					weixin   : '',				//微信
					email    : '',				//邮箱
					companyname: '',			//公司名称
					companyintro: '',			//公司简介
					province : '',				//省份
					city     : '',				//市
					area  : '',				//区
					address  : null,			//详细地址
					salesman : null,			//业务员
					itemtype : '',				//项目类型
					itemname : '',				//项目名称
					idcard    : '',				//身份证号
					contnum : null,			//合同编号
					itembegindate: '',				//开始时间
          			itemenddate  : '',				//结束时间
          			custstatus   : '',			//服务状态
          			evaluate: '',				//评价
          			itemrequire  : '',
          			saleid: ''//制作说明
          			salename  : '',		//业务员姓名	
          			salephone : '',		//业务员电话	
          			saleweixin: '',		//业务员微信	
          			saleemail : '',		//业务员邮箱	
          			IDImageUrl    : [],		//上传身份证图片url
          			contractImageUrl: [],		//上传合同图片url
          			licenseImageUrl : [],		//上传营业执照图片url
          			invoiceImageUrl : [],		
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
    private String itembegindate;

    /**
     * 项目结束时间
     */
    private String itemenddate;

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
    
    //备用字段4,客服备注
    private String standby4;
    
    private Integer relatecontstatus;
    
	public Integer getRelatecontstatus() {
		return relatecontstatus;
	}

	public void setRelatecontstatus(Integer relatecontstatus) {
		this.relatecontstatus = relatecontstatus;
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

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public Integer getItemtypedetail() {
		return itemtypedetail;
	}

	public void setItemtypedetail(Integer itemtypedetail) {
		this.itemtypedetail = itemtypedetail;
	}

	public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
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

	public Integer getItemstatus() {
		return itemstatus;
	}

	public void setItemstatus(Integer itemstatus) {
		this.itemstatus = itemstatus;
	}

	public String getContnum() {
		return contnum;
	}

	public void setContnum(String contnum) {
		this.contnum = contnum;
	}

	public String getItemrequire() {
		return itemrequire;
	}

	public void setItemrequire(String itemrequire) {
		this.itemrequire = itemrequire;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

 

}
