package com.huisou.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-09-01 上午9:53
 */
public class OrgPostVo implements Serializable {

    private Integer orgid;
    private String departname;
    private Integer postid;
    private Integer userid;
    private String postname;
    private String petname;
    private String dep2;

    public String getDep2() {
        return dep2;
    }

    public void setDep2(String dep2) {
        this.dep2 = dep2;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

