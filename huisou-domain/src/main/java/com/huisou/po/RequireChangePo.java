package com.huisou.po;

import java.util.Date;

import javax.persistence.*;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月20日 下午5:19:31 
* 类说明 
*/
@Table(name = "crm_item_require_change")
public class RequireChangePo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	
}
