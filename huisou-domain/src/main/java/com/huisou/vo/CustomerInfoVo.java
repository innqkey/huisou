package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.common.DateUtils;
import com.huisou.constant.DictConConstant;
import com.huisou.po.PicRecordPo;
import com.huisou.po.TradeCecordPo;

public class CustomerInfoVo implements Serializable{

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 3183028469122975310L;
	
	private Integer custid;
	/**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 公司名称
     */
    private String companyname;

    /**
     * 公司简介
     */
    private String companyintro;
    
    private  String province;
    private  String city;
    private  String area;
    private String address;  
    private Integer custstatus;
    private String evaluate;
    
    private String itemid;
    //项目
    /**
     * 项目名称
     */
    private String itemname;

    /**
     * itemtype
     */
    private Integer itemtype;
	private Integer itemtypedetail;
	private String itemdetailname;
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

    /**
     * 合同编号
     */
    private String contnum;

    /**
     * 项目要求
     */
    private String itemrequire;
    
    private Date itembegindate;

    private static Date itemenddate;
    //业务员
    private Integer saleid;
    
    

    /**
     * 业务员名称
     */
    private String salename;

    /**
     * 业务员手机号
     */
    private String salephone;

    /**
     * 业务员微信号
     */
    private String saleweixin;

    /**
     * 业务员邮箱
     */
    private String saleemail;
    private String custstatusName;
    private String itemtypename;
    private String addressInfo;
	private String relatecontstatusname;
	private Integer relatecontstatus;
	
	//客户备用
	private String custremark;
	
	private String standby4;
	
	public String getStandby4() {
		return standby4;
	}

	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}

	public String getCustremark() {
		return custremark;
	}

	public void setCustremark(String custremark) {
		this.custremark = custremark;
	}

	public void setRelatecontstatus(Integer relatecontstatus) {
		this.relatecontstatus = relatecontstatus;
	}

	public String getRelatecontstatusname() {
		return DictConConstant.getDicName("relatecontstatus",this.relatecontstatus);
	}
    public String getAddressInfo() {
	return addressInfo;
}

	public Integer getRelatecontstatus() {
			return relatecontstatus;
		}
	
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getCuststatusName() {
	return DictConConstant.getDicName("custstatus", this.custstatus);
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
    //到期时间
    private String expirationTime;
    private static List<TradeCecordVo> tradeCecordPos=new ArrayList<TradeCecordVo>(); 
    private static List<SaleCustHistoryVo> salesCustHistoryVos=new ArrayList<SaleCustHistoryVo>();
    private List<PicRecordPo> uploadDocs=new ArrayList<PicRecordPo>();

	public List<PicRecordPo> getUploadDocs() {
		return uploadDocs;
	}

	public void setUploadDocs(List<PicRecordPo> uploadDocs) {
		this.uploadDocs = uploadDocs;
	}

	@JSONField
	public static String getExpirationTime() {
    	int day= 0;
    	if(itemenddate!=null){
    		day = DateUtils.diffMonth(new Date(), itemenddate);
    	}else {
    		return "1个月";
    	}
		if(day<0){
			day=day / 30;
			if(day==0){
				 return "逾期小于1个月";
			}else {
				return "逾期"+Math.abs(day)+"个月";
			}
		}
		if(day>=0){
			day=day/30;
			if(day==0){
				return "小于1个月";
			}else if(day<=12){
				return "剩余"+day+"个月";
			}else if(day>12){
				int year=day/12;
				return "剩余"+year+"年";
			}
		}
		return null;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
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

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}

	public String getSalephone() {
		return salephone;
	}

	public void setSalephone(String salephone) {
		this.salephone = salephone;
	}

	public String getSaleweixin() {
		return saleweixin;
	}

	public void setSaleweixin(String saleweixin) {
		this.saleweixin = saleweixin;
	}

	public String getSaleemail() {
		return saleemail;
	}

	public void setSaleemail(String saleemail) {
		this.saleemail = saleemail;
	}

	public String getContact() {
		return contact;
	}
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
    

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyintro() {
		return companyintro;
	}

	public void setCompanyintro(String companyintro) {
		this.companyintro = companyintro;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCuststatus() {
		return custstatus;
	}

	public void setCuststatus(Integer custstatus) {
		this.custstatus = custstatus;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemtypename(){
		return DictConConstant.getDicName("itemtype", this.itemtype);
	}
	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
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

	public  List<TradeCecordVo> getTradeCecordPos() {
		return tradeCecordPos;
	}

	public  void setTradeCecordPos(List<TradeCecordVo> tradeCecordPos) {
		this.tradeCecordPos = tradeCecordPos;
	}

	public List<SaleCustHistoryVo> getSalesCustHistoryVos() {
		return salesCustHistoryVos;
	}

	public void setSalesCustHistoryVos(List<SaleCustHistoryVo> salesCustHistoryVos) {
		this.salesCustHistoryVos = salesCustHistoryVos;
	}

	

	

	
}
