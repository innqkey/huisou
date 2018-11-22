package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_notice_read")
public class NoticeReadPo {
    /**
     * 消息阅读记录id
     */
    @Id
    private Integer readid;

    /**
     * 消息id
     */
    private Integer noticeid;

    /**
     * 阅读人
     */
    private Integer readby;

    /**
     * 获取消息阅读记录id
     *
     * @return readid - 消息阅读记录id
     */
    public Integer getReadid() {
        return readid;
    }

    /**
     * 设置消息阅读记录id
     *
     * @param readid 消息阅读记录id
     */
    public void setReadid(Integer readid) {
        this.readid = readid;
    }

    /**
     * 获取消息id
     *
     * @return noticeid - 消息id
     */
    public Integer getNoticeid() {
        return noticeid;
    }

    /**
     * 设置消息id
     *
     * @param noticeid 消息id
     */
    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    /**
     * 获取阅读人
     *
     * @return readby - 阅读人
     */
    public Integer getReadby() {
        return readby;
    }

    /**
     * 设置阅读人
     *
     * @param readby 阅读人
     */
    public void setReadby(Integer readby) {
        this.readby = readby;
    }
}