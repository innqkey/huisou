package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_salesman")
public class SalesManPo {
    /**
     * 业务员id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 备用字段
     */
    private String standby1;

    /**
     * 备用字段2
     */
    private String standby2;

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
     * 获取业务员手机号
     *
     * @return salephone - 业务员手机号
     */
    public String getSalephone() {
        return salephone;
    }

    /**
     * 设置业务员手机号
     *
     * @param salephone 业务员手机号
     */
    public void setSalephone(String salephone) {
        this.salephone = salephone;
    }

    /**
     * 获取业务员微信号
     *
     * @return saleweixin - 业务员微信号
     */
    public String getSaleweixin() {
        return saleweixin;
    }

    /**
     * 设置业务员微信号
     *
     * @param saleweixin 业务员微信号
     */
    public void setSaleweixin(String saleweixin) {
        this.saleweixin = saleweixin;
    }

    /**
     * 获取业务员邮箱
     *
     * @return saleemail - 业务员邮箱
     */
    public String getSaleemail() {
        return saleemail;
    }

    /**
     * 设置业务员邮箱
     *
     * @param saleemail 业务员邮箱
     */
    public void setSaleemail(String saleemail) {
        this.saleemail = saleemail;
    }

    /**
     * 获取业务员状态：1，正常；2，禁用
     *
     * @return salestatus - 业务员状态：1，正常；2，禁用
     */
    public Integer getSalestatus() {
        return salestatus;
    }

    /**
     * 设置业务员状态：1，正常；2，禁用
     *
     * @param salestatus 业务员状态：1，正常；2，禁用
     */
    public void setSalestatus(Integer salestatus) {
        this.salestatus = salestatus;
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