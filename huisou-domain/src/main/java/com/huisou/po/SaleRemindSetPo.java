package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_sale_remind_set")
public class SaleRemindSetPo {
    /**
     * 个人提醒id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleremindid;

    /**
     * 提醒人id
     */
    private Integer saleid;

    /**
     * 提醒人名称
     */
    private String salename;

    /**
     * 编码:1续签提醒;2员工转正;3生日
     */
    private Integer salebusicode;

    /**
     * 设置时间跨度（天为单位）
     */
    private Integer saleremindtimespan;

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
     * 获取个人提醒id
     *
     * @return saleremindid - 个人提醒id
     */
    public Integer getSaleremindid() {
        return saleremindid;
    }

    /**
     * 设置个人提醒id
     *
     * @param saleremindid 个人提醒id
     */
    public void setSaleremindid(Integer saleremindid) {
        this.saleremindid = saleremindid;
    }

    /**
     * 获取提醒人id
     *
     * @return saleid - 提醒人id
     */
    public Integer getSaleid() {
        return saleid;
    }

    /**
     * 设置提醒人id
     *
     * @param saleid 提醒人id
     */
    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    /**
     * 获取提醒人名称
     *
     * @return salename - 提醒人名称
     */
    public String getSalename() {
        return salename;
    }

    /**
     * 设置提醒人名称
     *
     * @param salename 提醒人名称
     */
    public void setSalename(String salename) {
        this.salename = salename;
    }

    /**
     * 获取编码:1续签提醒;2员工转正;3生日
     *
     * @return salebusicode - 编码:1续签提醒;2员工转正;3生日
     */
    public Integer getSalebusicode() {
        return salebusicode;
    }

    /**
     * 设置编码:1续签提醒;2员工转正;3生日
     *
     * @param salebusicode 编码:1续签提醒;2员工转正;3生日
     */
    public void setSalebusicode(Integer salebusicode) {
        this.salebusicode = salebusicode;
    }

    /**
     * 获取设置时间跨度（天为单位）
     *
     * @return saleremindtimespan - 设置时间跨度（天为单位）
     */
    public Integer getSaleremindtimespan() {
        return saleremindtimespan;
    }

    /**
     * 设置设置时间跨度（天为单位）
     *
     * @param saleremindtimespan 设置时间跨度（天为单位）
     */
    public void setSaleremindtimespan(Integer saleremindtimespan) {
        this.saleremindtimespan = saleremindtimespan;
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