package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_user")
public class UserPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 相关连的用户id 如：saleman 的saleid
     */
    private Integer keyid;

    /**
     * 登陆员工的类别 如：财务、行政等  3为业务员
     */
    private Integer type;

    /**
     * 用户状态信息 1正常 0 禁用
     */
    private Integer status;

    /**
     * 预留字段2
     */
    private String petname;

    /**
     * 是否为销售经理 1是0不是
     */
    private Integer leader;


    /**
     * 对应员工id
     */
    private Integer empid;

    private Integer orgid;
    private Integer postid;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取相关连的用户id 如：saleman 的saleid
     *
     * @return keyid - 相关连的用户id 如：saleman 的saleid
     */
    public Integer getKeyid() {
        return keyid;
    }

    /**
     * 设置相关连的用户id 如：saleman 的saleid
     *
     * @param keyid 相关连的用户id 如：saleman 的saleid
     */
    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    /**
     * 获取登陆员工的类别 如：财务、行政等
     *
     * @return type - 登陆员工的类别 如：财务、行政等
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置登陆员工的类别 如：财务、行政等
     *
     * @param type 登陆员工的类别 如：财务、行政等
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }
}