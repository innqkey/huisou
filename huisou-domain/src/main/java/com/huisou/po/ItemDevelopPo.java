package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_item_develop")
public class ItemDevelopPo {
    /**
     * 开发进度id
     */
    @Id
    private Integer devid;

    /**
     * 项目名称
     */
    private String itemname;

    /**
     * 项目id,若手动输入则为0
     */
    private Integer itemid;

    /**
     * 开发时间起
     */
    private Date devdatebegin;

    /**
     * 开发时间止
     */
    private Date devdateend;

    /**
     * 开发状态：1-前期；2-开发中；3-逾期；4-完成
     */
    private Integer devstatus;

    /**
     * 交付状态：1-正常交付；2-逾期交付
     */
    private Integer delystatus;

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
     * 备用字段1
     */
    private String standby1;

    /**
     * 备用字段2
     */
    private String standby2;

    /**
     * 获取开发进度id
     *
     * @return devid - 开发进度id
     */
    public Integer getDevid() {
        return devid;
    }

    /**
     * 设置开发进度id
     *
     * @param devid 开发进度id
     */
    public void setDevid(Integer devid) {
        this.devid = devid;
    }

    /**
     * 获取项目名称
     *
     * @return itemname - 项目名称
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * 设置项目名称
     *
     * @param itemname 项目名称
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * 获取项目id,若手动输入则为0
     *
     * @return itemid - 项目id,若手动输入则为0
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置项目id,若手动输入则为0
     *
     * @param itemid 项目id,若手动输入则为0
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取开发时间起
     *
     * @return devdatebegin - 开发时间起
     */
    public Date getDevdatebegin() {
        return devdatebegin;
    }

    /**
     * 设置开发时间起
     *
     * @param devdatebegin 开发时间起
     */
    public void setDevdatebegin(Date devdatebegin) {
        this.devdatebegin = devdatebegin;
    }

    /**
     * 获取开发时间止
     *
     * @return devdateend - 开发时间止
     */
    public Date getDevdateend() {
        return devdateend;
    }

    /**
     * 设置开发时间止
     *
     * @param devdateend 开发时间止
     */
    public void setDevdateend(Date devdateend) {
        this.devdateend = devdateend;
    }

    /**
     * 获取开发状态：1-前期；2-开发中；3-逾期；4-完成
     *
     * @return devstatus - 开发状态：1-前期；2-开发中；3-逾期；4-完成
     */
    public Integer getDevstatus() {
        return devstatus;
    }

    /**
     * 设置开发状态：1-前期；2-开发中；3-逾期；4-完成
     *
     * @param devstatus 开发状态：1-前期；2-开发中；3-逾期；4-完成
     */
    public void setDevstatus(Integer devstatus) {
        this.devstatus = devstatus;
    }

    /**
     * 获取交付状态：1-正常交付；2-逾期交付
     *
     * @return delystatus - 交付状态：1-正常交付；2-逾期交付
     */
    public Integer getDelystatus() {
        return delystatus;
    }

    /**
     * 设置交付状态：1-正常交付；2-逾期交付
     *
     * @param delystatus 交付状态：1-正常交付；2-逾期交付
     */
    public void setDelystatus(Integer delystatus) {
        this.delystatus = delystatus;
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
     * 获取备用字段1
     *
     * @return standby1 - 备用字段1
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置备用字段1
     *
     * @param standby1 备用字段1
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