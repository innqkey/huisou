package com.huisou.vo;

import com.huisou.constant.DictConConstant;

public class TradeVo {

	private Integer itemid;
	private String itemname;
	private Integer itemtype;
	private String itemtypename;
	private String contact;//联系人
	private String phone;
	private String weixin;
	private Integer tradeway;
	private String tradewayname;
	private Integer tradetype;
    private String tradetypename;
    private String tradedate;
    private String voucherno;
    private String traderemarks;
    private Integer auditstatus;
    private String auditstatusname;
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
    
    public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public String getVoucherno() {
		return voucherno;
	}
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	public String getTraderemarks() {
		return traderemarks;
	}
	public void setTraderemarks(String traderemarks) {
		this.traderemarks = traderemarks;
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
	public String getItemtypename() {
//		if(null==this.itemtype){
//			return null;
//		}
		return DictConConstant.getDicName("itemtype",this.itemtype);
	}
	public void setItemtypename(String itemtypename) {
		this.itemtypename = itemtypename;
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
	public Integer getTradeway() {
		return tradeway;
	}
	public void setTradeway(Integer tradeway) {
		this.tradeway = tradeway;
	}
	public String getTradewayname() {
//		if(null==this.tradeway){
//			return null;
//		}
		return DictConConstant.getDicName("tradeway",this.tradeway);
	}
	public void setTradewayname(String tradewayname) {
		this.tradewayname = tradewayname;
	}
	public Integer getTradetype() {
		return tradetype;
	}
	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}
	public String getTradetypename() {
//		if(null==this.tradetype){
//			return null;
//		}
		return DictConConstant.getDicName("tradetype",this.tradetype);
	}
	public void setTradetypename(String tradetypename) {
		this.tradetypename = tradetypename;
	}
	public String getTradedate() {
		return tradedate;
	}
	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}
    
}
