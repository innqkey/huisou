package com.huisou.po;

import java.util.Date;

import javax.persistence.*;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月20日 下午4:37:20 
* 类说明 
*/
@Table(name = "crm_item_require")
public class ItemRequirePo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requireid;
	
	/**
	 * 项目id
	 */
	private Integer itemid;
	
	/**
	 * 需求
	 */
	private String requirecontent;
	
	/**
	 * 创建人
	 */
	private Integer createby;
	
	   /**
     * 创建时间
     */
    private Date createdate;
     
    /**
     * 备用字段
     */
    private String standby1;

    /**
     * 备用字段2
     */
    private String standby2;

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

	public String getRequirecontent() {
		return requirecontent;
	}

	public void setRequirecontent(String requirecontent) {
		this.requirecontent = requirecontent;
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
    
    
}
