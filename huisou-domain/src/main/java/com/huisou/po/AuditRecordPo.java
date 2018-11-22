package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_audit_record")
public class AuditRecordPo {
    /**
     * 回访记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditid;

    /**
     * 关联交易id
     */
    private Integer tradeid;

    /**
     * 关联项目id
     */
    private Integer itemid;

    /**
     * 审核人id(财务)
     */
    private Integer audituserid;

    /**
     * 审核人名称
     */
    private String auditusername;

    /**
     * 审核状态：1审核通过;2审核不通过
     */
    private Integer auditstatus;

    /**
     * 备注
     */
    private String auditremarks;

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
     * 获取回访记录id
     *
     * @return auditid - 回访记录id
     */
    public Integer getAuditid() {
        return auditid;
    }

    /**
     * 设置回访记录id
     *
     * @param auditid 回访记录id
     */
    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    /**
     * 获取关联交易id
     *
     * @return tradeid - 关联交易id
     */
    public Integer getTradeid() {
        return tradeid;
    }

    /**
     * 设置关联交易id
     *
     * @param tradeid 关联交易id
     */
    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
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
     * 获取审核人id(财务)
     *
     * @return audituserid - 审核人id(财务)
     */
    public Integer getAudituserid() {
        return audituserid;
    }

    /**
     * 设置审核人id(财务)
     *
     * @param audituserid 审核人id(财务)
     */
    public void setAudituserid(Integer audituserid) {
        this.audituserid = audituserid;
    }

    /**
     * 获取审核人名称
     *
     * @return auditusername - 审核人名称
     */
    public String getAuditusername() {
        return auditusername;
    }

    /**
     * 设置审核人名称
     *
     * @param auditusername 审核人名称
     */
    public void setAuditusername(String auditusername) {
        this.auditusername = auditusername;
    }

    /**
     * 获取审核状态：1审核通过;2审核不通过
     *
     * @return auditstatus - 审核状态：1审核通过;2审核不通过
     */
    public Integer getAuditstatus() {
        return auditstatus;
    }

    /**
     * 设置审核状态：1审核通过;2审核不通过
     *
     * @param auditstatus 审核状态：1审核通过;2审核不通过
     */
    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    /**
     * 获取备注
     *
     * @return auditremarks - 备注
     */
    public String getAuditremarks() {
        return auditremarks;
    }

    /**
     * 设置备注
     *
     * @param auditremarks 备注
     */
    public void setAuditremarks(String auditremarks) {
        this.auditremarks = auditremarks;
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
}