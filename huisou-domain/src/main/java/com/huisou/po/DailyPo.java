package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_daily")
public class DailyPo {
    /**
     * 日报id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dailyid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 日志类型： 日报，周报或者月报
     */
    private String type;

    /**
     * 完成
     */
    private String complete;

    /**
     * 未完成
     */
    private String uncomplete;

    /**
     * 需要协调
     */
    private String needhelp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 提交时间
     */
    private String createtime;

    /**
     * 审批人id
     */
    private Integer reader;

    /**
     * 评论
     */
    private String comment;

    /**
     * 是否查看 1已查看 0未查看
     */
    private Integer status;

    private String standby1;

    private String standby2;

    /**
     * 获取日报id
     *
     * @return dailyid - 日报id
     */
    public Integer getDailyid() {
        return dailyid;
    }

    /**
     * 设置日报id
     *
     * @param dailyid 日报id
     */
    public void setDailyid(Integer dailyid) {
        this.dailyid = dailyid;
    }

    /**
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取日志类型： 日报，周报或者月报
     *
     * @return type - 日志类型： 日报，周报或者月报
     */
    public String getType() {
        return type;
    }

    /**
     * 设置日志类型： 日报，周报或者月报
     *
     * @param type 日志类型： 日报，周报或者月报
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取完成
     *
     * @return complete - 完成
     */
    public String getComplete() {
        return complete;
    }

    /**
     * 设置完成
     *
     * @param complete 完成
     */
    public void setComplete(String complete) {
        this.complete = complete;
    }

    /**
     * 获取未完成
     *
     * @return uncomplete - 未完成
     */
    public String getUncomplete() {
        return uncomplete;
    }

    /**
     * 设置未完成
     *
     * @param uncomplete 未完成
     */
    public void setUncomplete(String uncomplete) {
        this.uncomplete = uncomplete;
    }

    /**
     * 获取需要协调
     *
     * @return needhelp - 需要协调
     */
    public String getNeedhelp() {
        return needhelp;
    }

    /**
     * 设置需要协调
     *
     * @param needhelp 需要协调
     */
    public void setNeedhelp(String needhelp) {
        this.needhelp = needhelp;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取提交时间
     *
     * @return createtime - 提交时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置提交时间
     *
     * @param createtime 提交时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取审批人id
     *
     * @return reader - 审批人id
     */
    public Integer getReader() {
        return reader;
    }

    /**
     * 设置审批人id
     *
     * @param reader 审批人id
     */
    public void setReader(Integer reader) {
        this.reader = reader;
    }

    /**
     * 获取评论
     *
     * @return comment - 评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论
     *
     * @param comment 评论
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取是否查看 1已查看 0未查看
     *
     * @return status - 是否查看 1已查看 0未查看
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否查看 1已查看 0未查看
     *
     * @param status 是否查看 1已查看 0未查看
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return standby1
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * @param standby1
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * @return standby2
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * @param standby2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }
}