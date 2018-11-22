package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_post")
public class PostPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 组织id
     */
    private Integer organizeid;

    /**
     * 岗位名称
     */
    private String postname;

    private String standby1;

    private String standby2;

    /**
     * @return postid
     */
    public Integer getPostid() {
        return postid;
    }

    /**
     * @param postid
     */
    public void setPostid(Integer postid) {
        this.postid = postid;
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
     * 获取组织id
     *
     * @return organizeid - 组织id
     */
    public Integer getOrganizeid() {
        return organizeid;
    }

    /**
     * 设置组织id
     *
     * @param organizeid 组织id
     */
    public void setOrganizeid(Integer organizeid) {
        this.organizeid = organizeid;
    }

    /**
     * 获取岗位名称
     *
     * @return postname - 岗位名称
     */
    public String getPostname() {
        return postname;
    }

    /**
     * 设置岗位名称
     *
     * @param postname 岗位名称
     */
    public void setPostname(String postname) {
        this.postname = postname;
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