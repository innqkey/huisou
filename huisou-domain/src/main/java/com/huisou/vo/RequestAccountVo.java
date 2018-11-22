package com.huisou.vo;
/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月18日 下午6:02:40 
* 类说明 
*/
public class RequestAccountVo {
	
		/**
		 * 网址
		 */
	   private String url;
	   
	   /**
	    * 账号名称
	    */
	   private String username;
	  
	   /**
	    * 密码
	    */
	   private String password;
	   
	   /**
	    * 备注
	    */
	   private String comments;
	   
	   /**
	    * 类型名称
	    */
	   private String accountypename;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAccountypename() {
		return accountypename;
	}

	public void setAccountypename(String accountypename) {
		this.accountypename = accountypename;
	}
	   
	   
}
