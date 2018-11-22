package com.huisou.vo;

import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月17日 下午5:28:40 
* 类说明 
*/
public class SalesmanVo {
	
	/**
	 * 业务员id
	 */
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

    /**
     * 业务员状态：1，正常；2，禁用
     */
    private Integer salestatus;
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
     * 业务员的客户数
     * @return
     */
    private Integer customers;

	public Integer getCustomers() {
		return customers;
	}

	public void setCustomers(Integer customers) {
		this.customers = customers;
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

	public Integer getSalestatus() {
		return salestatus;
	}

	public void setSalestatus(Integer salestatus) {
		this.salestatus = salestatus;
	}
    
    

}
