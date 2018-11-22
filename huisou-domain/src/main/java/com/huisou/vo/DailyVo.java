package com.huisou.vo;

import java.io.Serializable;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午4:38
 */
public class DailyVo implements Serializable {

    private Integer dailyid;
    private Integer userid;
    private String type;
    private String complete;
    private String uncomplete;
    private String needhelp;
    private String remark;
    private String createtime;
    private Integer reader;
    private String comment;
    private Integer status;



    private OrgPostVo orgPostVo;

    public OrgPostVo getOrgPostVo() {
        return orgPostVo;
    }

    public void setOrgPostVo(OrgPostVo orgPostVo) {
        this.orgPostVo = orgPostVo;
    }

    public Integer getDailyid() {
        return dailyid;
    }

    public void setDailyid(Integer dailyid) {
        this.dailyid = dailyid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getUncomplete() {
        return uncomplete;
    }

    public void setUncomplete(String uncomplete) {
        this.uncomplete = uncomplete;
    }

    public String getNeedhelp() {
        return needhelp;
    }

    public void setNeedhelp(String needhelp) {
        this.needhelp = needhelp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getReader() {
        return reader;
    }

    public void setReader(Integer reader) {
        this.reader = reader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
