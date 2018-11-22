package com.huisou.vo;

import java.util.Date;
import com.huisou.constant.DictConConstant;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月24日 下午2:33:55 
* 类说明 
*/
public class RequireChangeVo {
		private Integer requirechangeid;
		
		/**
		 * 需求id
		 */
		private Integer requireid;
		
		/**
		 * 项目Id
		 */
		private Integer itemid;
		
		/**
		 * 需求变动次数
		 */
		private Integer changenum;
		
		/**
		 * 变动类型：1-制作需求变动；2-上传文档变动；3-制作需求变动及上传文档变动
		 */
		private Integer changetype;
		
		/**
		 * 回复状态 默认：0-未处理（页面显示黄色）；1-已处理
		 */
		private Integer dealstatus;
		
		/**
		 * 恢复状态名字
		 */
		private String dealstatusName;
		/**
		 *  处理备注
		 */
		private String dealcontent;
		
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
	     * 客户姓名（联系人）
	     */
	    private String contact;
	    
	    /**
	     * 项目名
	     */
	    private String itemname;
	    
	    /**
	     * 客户手机号
	     */
	    private String phone;
	    
	    /**
	     * 公司地址
	     */
	    private String addressInfo;
	    
	    /**
	     * 公司名称
	     */
	    private String companyname;
	    
	    /**
	     * 项目类型
	     */
	    private int itemtype;
	    /**
	     * 汇搜云项目类型
	     */
		private Integer itemtypedetail;
		private String itemdetailname;
	    
	    /**
	     * 项目类型名字
	     */
	    private String itemtypeName;
	    
	    /**
	     * 项目状态
	     */
	    private int custstatus;
	    
	    /**
	     * 客户状态名字
	     */
	    private String custstatusName;
	    
	    /**
	     * 项目状态名字
	     */
	    private String itemstatusName;
	    
	    public String getItemstatusName() {
			return itemstatusName;
		}

		public void setItemstatusName(String itemstatusName) {
			this.itemstatusName = itemstatusName;
		}

		private Integer status;
		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
		/**
	     * 省
	     */
	    private String province;
	    
	    /**
	     * 市
	     */
	    private String city;
	    
	    /**
	     * 区
	     */
	    private String area;
	    
	    /**
	     * 地址
	     */
	    private String address;
	    
	    /**
	     * 业务员
	     */
	    private String salename;
	    
	    


		public String getDealstatusName() {
			return dealstatusName;
		}

		public void setDealstatusName(String dealstatusName) {
			this.dealstatusName = dealstatusName;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public int getCuststatus() {
			return custstatus;
		}

		public void setCuststatus(int custstatus) {
			this.custstatus = custstatus;
		}

		public String getCuststatusName() {
			return custstatusName;
		}

		public void setCuststatusName(String custstatusName) {
			this.custstatusName = custstatusName;
		}

		public Integer getRequirechangeid() {
			return requirechangeid;
		}

		public void setRequirechangeid(Integer requirechangeid) {
			this.requirechangeid = requirechangeid;
		}

		public Integer getRequireid() {
			return requireid;
		}

		public void setRequireid(Integer requireid) {
			this.requireid = requireid;
		}

		public Integer getItemid() {
			return itemid;
		}

		public void setItemid(Integer itemid) {
			this.itemid = itemid;
		}

		public Integer getChangenum() {
			return changenum;
		}

		public void setChangenum(Integer changenum) {
			this.changenum = changenum;
		}

		public Integer getChangetype() {
			return changetype;
		}

		public void setChangetype(Integer changetype) {
			this.changetype = changetype;
		}

		public Integer getDealstatus() {
			return dealstatus;
		}

		public void setDealstatus(Integer dealstatus) {
			this.dealstatus = dealstatus;
		}

		public String getDealcontent() {
			return dealcontent;
		}

		public void setDealcontent(String dealcontent) {
			this.dealcontent = dealcontent;
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


		public String getItemname() {
			return itemname;
		}

		public void setItemname(String itemname) {
			this.itemname = itemname;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddressInfo() {
			return addressInfo;
		}

		public void setAddressInfo(String addressInfo) {
			this.addressInfo = addressInfo;
		}

		public String getCompanyname() {
			return companyname;
		}

		public void setCompanyname(String companyname) {
			this.companyname = companyname;
		}

		public int getItemtype() {
			return itemtype;
		}

		public void setItemtype(int itemtype) {
			this.itemtype = itemtype;
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
		public String getItemtypeName() {
			return itemtypeName;
		}

		public void setItemtypeName(String itemtypeName) {
			this.itemtypeName = itemtypeName;
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

		public String getSalename() {
			return salename;
		}

		public void setSalename(String salename) {
			this.salename = salename;
		}
	    


}
