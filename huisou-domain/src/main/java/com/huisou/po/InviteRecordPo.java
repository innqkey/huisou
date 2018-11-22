package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:59:38 
* 类说明 
*/
@Table(name = "crm_invite_record")
public class InviteRecordPo {

	/**
	 *邀约记录id 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inviterecordid;
	/**
	 * 培训课程id
	 */
	private Integer courseid;
	
	/**
	 * 应约次数
	 */
	private Integer inviteidcount;
	
	/**
	 * 意向客户id
	 */
	private Integer intentioncustid;
	
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

	public Integer getInviterecordid() {
		return inviterecordid;
	}

	public void setInviterecordid(Integer inviterecordid) {
		this.inviterecordid = inviterecordid;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getInviteidcount() {
		return inviteidcount;
	}

	public void setInviteidcount(Integer inviteidcount) {
		this.inviteidcount = inviteidcount;
	}

	public Integer getIntentioncustid() {
		return intentioncustid;
	}

	public void setIntentioncustid(Integer intentioncustid) {
		this.intentioncustid = intentioncustid;
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
