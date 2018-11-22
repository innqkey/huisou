package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_visit_record")
public class VisitRecordPo {
    /**
     * 回访记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitid;

    /**
     * 关联项目id
     */
    private Integer itemid;

    /**
     * 关联客户id
     */
    private Integer custid;

    /**
     * 业务员id
     */
    private Integer saleid;

    /**
     * 业务员名称
     */
    private String salename;

    /**
     * 回访类型：1电话回访;2拜访，3邮件回访（备用）
     */
    private Integer visitway;

    /**
     * 回访类型名称
     */
    private String visitwayname;

    /**
     * 备注
     */
    private String visitremarks;

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
    
    private Integer readstatus;
    private Integer replystatus;
    private String replycontext;
    

    /**
     * 获取回访记录id
     *
     * @return visitid - 回访记录id
     */
    public Integer getVisitid() {
        return visitid;
    }

    /**
     * 设置回访记录id
     *
     * @param visitid 回访记录id
     */
    public void setVisitid(Integer visitid) {
        this.visitid = visitid;
    }

    /**
     * 获取关联项目id
     *
     * @return itemid - 关联项目id
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置关联项目id
     *
     * @param itemid 关联项目id
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取关联客户id
     *
     * @return custid - 关联客户id
     */
    public Integer getCustid() {
        return custid;
    }

    /**
     * 设置关联客户id
     *
     * @param custid 关联客户id
     */
    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    /**
     * 获取业务员id
     *
     * @return saleid - 业务员id
     */
    public Integer getSaleid() {
        return saleid;
    }

    /**
     * 设置业务员id
     *
     * @param saleid 业务员id
     */
    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    /**
     * 获取业务员名称
     *
     * @return salename - 业务员名称
     */
    public String getSalename() {
        return salename;
    }

    /**
     * 设置业务员名称
     *
     * @param salename 业务员名称
     */
    public void setSalename(String salename) {
        this.salename = salename;
    }

    /**
     * 获取回访类型：1电话回访;2拜访，3邮件回访（备用）
     *
     * @return visitway - 回访类型：1电话回访;2拜访，3邮件回访（备用）
     */
    public Integer getVisitway() {
        return visitway;
    }

    /**
     * 设置回访类型：1电话回访;2拜访，3邮件回访（备用）
     *
     * @param visitway 回访类型：1电话回访;2拜访，3邮件回访（备用）
     */
    public void setVisitway(Integer visitway) {
        this.visitway = visitway;
    }

    /**
     * 获取回访类型名称
     *
     * @return visitwayname - 回访类型名称
     */
    public String getVisitwayname() {
        return visitwayname;
    }

    /**
     * 设置回访类型名称
     *
     * @param visitwayname 回访类型名称
     */
    public void setVisitwayname(String visitwayname) {
        this.visitwayname = visitwayname;
    }

    /**
     * 获取备注
     *
     * @return visitremarks - 备注
     */
    public String getVisitremarks() {
        return visitremarks;
    }

    /**
     * 设置备注
     *
     * @param visitremarks 备注
     */
    public void setVisitremarks(String visitremarks) {
        this.visitremarks = visitremarks;
    }

    /**
     * 获取创建人
     *
     * @return createby - 创建人
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * 设置创建人
     *
     * @param createby 创建人
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * 获取创建时间
     *
     * @return createdate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取更新人
     *
     * @return updateby - 更新人
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * 设置更新人
     *
     * @param updateby 更新人
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    /**
     * 获取更新时间
     *
     * @return updatedate - 更新时间
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置更新时间
     *
     * @param updatedate 更新时间
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取备用字段
     *
     * @return standby1 - 备用字段
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置备用字段
     *
     * @param standby1 备用字段
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 获取备用字段2
     *
     * @return standby2 - 备用字段2
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置备用字段2
     *
     * @param standby2 备用字段2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

	public Integer getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(Integer readstatus) {
		this.readstatus = readstatus;
	}

	public Integer getReplystatus() {
		return replystatus;
	}

	public void setReplystatus(Integer replystatus) {
		this.replystatus = replystatus;
	}

	public String getReplycontext() {
		return replycontext;
	}

	public void setReplycontext(String replycontext) {
		this.replycontext = replycontext;
	}
    
}