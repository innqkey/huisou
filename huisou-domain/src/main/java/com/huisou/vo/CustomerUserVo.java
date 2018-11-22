package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 上午10:53:56 
* 类说明 
*/
public class CustomerUserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2353601751027970391L;
	 private Integer custuserid;

	    /**
	     * 客户id
	     */
	    private Integer custid;

	    /**
	     * 客户姓名联系人
	     */
	    private String contact;

	    /**
	     * 客户手机号
	     */
	    private String phone;

	    /**
	     * 登录密码
	     */
	    private String password;
	    
	    /**
	     * (登录帐号)
	     */
	    private String username;
	    /**
	     * 登陆总次数
	     */
	    private Integer loginCount;
	    /**
	     * 最近一次登陆时间 
	     */
	    private Date lastLoginDate;
	    

		public Integer getLoginCount() {
			return loginCount;
		}

		public void setLoginCount(Integer loginCount) {
			this.loginCount = loginCount;
		}

		public Date getLastLoginDate() {
			return lastLoginDate;
		}

		public void setLastLoginDate(Date lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Integer getCustuserid() {
			return custuserid;
		}

		public void setCustuserid(Integer custuserid) {
			this.custuserid = custuserid;
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	    
}
