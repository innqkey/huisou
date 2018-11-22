package com.huisou.vo;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-09-04 上午9:23
 */
public class DailyInfoVo implements Serializable {

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

    private Integer orgid;
    private String departname;
    private Integer postid;
    //    private Integer userid;
    private String postname;
    private String petname;

    private String readername;

    private List<String> imgs;
    private List<String> zips;


    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<String> getZips() {
        return zips;
    }

    public void setZips(List<String> zips) {
        this.zips = zips;
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
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

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }
}
