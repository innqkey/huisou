package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_remind_set")
public class RemindSetPo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 回访记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer remindid;

    /**
     * 编码:1续签提醒;2员工转正;3生日
     */
    private Integer busicode;

    /**
     * 设置时间跨度（天为单位）
     */
    private Integer remindtimespan;

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
     * @return remindid - 回访记录id
     */
    public Integer getRemindid() {
        return remindid;
    }

    /**
     * 设置回访记录id
     *
     * @param remindid 回访记录id
     */
    public void setRemindid(Integer remindid) {
        this.remindid = remindid;
    }

    /**
     * 获取编码:1续签提醒;2员工转正;3生日
     *
     * @return busicode - 编码:1续签提醒;2员工转正;3生日
     */
    public Integer getBusicode() {
        return busicode;
    }

    /**
     * 设置编码:1续签提醒;2员工转正;3生日
     *
     * @param busicode 编码:1续签提醒;2员工转正;3生日
     */
    public void setBusicode(Integer busicode) {
        this.busicode = busicode;
    }

    /**
     * 获取设置时间跨度（天为单位）
     *
     * @return remindtimespan - 设置时间跨度（天为单位）
     */
    public Integer getRemindtimespan() {
        return remindtimespan;
    }

    /**
     * 设置设置时间跨度（天为单位）
     *
     * @param remindtimespan 设置时间跨度（天为单位）
     */
    public void setRemindtimespan(Integer remindtimespan) {
        this.remindtimespan = remindtimespan;
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