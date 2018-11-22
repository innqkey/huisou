package com.huisou.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import com.common.DateUtils;
import com.huisou.constant.DictConConstant;

public class CustomerVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8753725344545226792L;
	//crm_customer
	private Integer custid;
	private String contact;//联系人
	private String phone;
	private String weixin;
	private String email;
	private String address;
	private Integer custstatus;
    private String province;
 
	private String city;

    private String area;
	//crm_salesman
	private Integer saleid;
	private String  salename;
	
	//crm_items
	private Integer itemid;
	private String itemname;
	private Integer itemtype;
	private String itemenddate;
	private String itemtypename;
	private Integer itemtypedetail;
	private String itemdetailname;
	private String companyname;
	private Integer auditstatus;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private String auditstatusname;
	
	private Integer tradetype;
	
	private String tradetypename;
	
	private Integer commitstatus;
	private String commitstatusname;
	
	private Integer relatecontstatus;
	private String relatecontstatusname;
	
	private String itembegindate;
	
	private Integer userid;
	private String petname;
	//编码
	private String standby3;
	
	//域名
	private String standby2;

	private String standby4;
	
	private String serremark;
	
	private String replaceusername;
	
	//标示待续费
	private String markstatus;
	
	//是否续费
	private Integer  custrenewstatus;
	
	private Integer status;
	
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

	//追踪次数
	private Integer tracenum;
	
	public String getMarkstatus() {
		return markstatus;
	}

	public void setMarkstatus(String markstatus) {
		this.markstatus = markstatus;
	}

	public String getReplaceusername() {
		return replaceusername;
	}

	public void setReplaceusername(String replaceusername) {
		this.replaceusername = replaceusername;
	}

	public String getSerremark() {
		return serremark;
	}

	public void setSerremark(String serremark) {
		this.serremark = serremark;
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getItembegindate() {
		return itembegindate;
	}

	public void setItembegindate(String itembegindate) {
		this.itembegindate = itembegindate;
	}

	public Integer getRelatecontstatus() {
		return relatecontstatus;
	}

	public void setRelatecontstatus(Integer relatecontstatus) {
		this.relatecontstatus = relatecontstatus;
	}

	public String getRelatecontstatusname() {
		return DictConConstant.getDicName("relatecontstatus",this.relatecontstatus);
	}


	public void setRelatecontstatusname(String relatecontstatusname) {
		this.relatecontstatusname = relatecontstatusname;
	}

	public Integer getCommitstatus() {
		return commitstatus;
	}

	public void setCommitstatus(Integer commitstatus) {
		this.commitstatus = commitstatus;
	}

	public String getCommitstatusname() {
		return DictConConstant.getDicName("commitstatus",this.commitstatus);
	}

	public void setCommitstatusname(String commitstatusname) {
		this.commitstatusname = commitstatusname;
	}

	//详细的地址
	private String addressInfo;
	
	
	public Integer getTradetype() {
		return tradetype;
	}

	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}

	public String getTradetypename() {
		return DictConConstant.getDicName("tradetype",this.tradetype);
	}

	public void setTradetypename(String tradetypename) {
		this.tradetypename = tradetypename;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Integer getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditstatusname() {
		return DictConConstant.getDicName("auditstatus",this.auditstatus);
	}

	public void setAuditstatusname(String auditstatusname) {
		this.auditstatusname = auditstatusname;
	}

	
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getItemtypename() {
		
		return DictConConstant.getDicName("Itemtype", itemtype);
	}

	public void setItemtypename(String itemtypename) {
		this.itemtypename = itemtypename;
	}
	
	
    
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

	private String expirationTime;
	
	public String getProvince(){
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

	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}

	@JSONField
	public String getExpirationTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int day= 0;
    	if(itemenddate!=null){
    		try {
				day = DateUtils.diffMonth(new Date(), sdf.parse(itemenddate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		return "无";
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

	// 到期时间
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuststatusName() {
	
		return DictConConstant.getDicName("custstatus", this.custstatus);
	}
	
	public String getItemstatusName() {
		
		return DictConConstant.getDicName("custstatus", this.status);
	}
	
	public Integer getCuststatus() {
		return custstatus;
	}
	
	public void setCuststatus(Integer custstatus) 
	{
		this.custstatus = custstatus;
	}

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
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

	@JSONField(serialize=false)
	public String getItemenddate() {
		return itemenddate;
	}

	public void setItemenddate(String itemenddate) {
		this.itemenddate = itemenddate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
