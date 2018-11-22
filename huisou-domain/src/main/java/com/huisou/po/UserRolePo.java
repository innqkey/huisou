package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_user_role")
public class UserRolePo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer urid;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 角色ID
     */
    private Integer roleid;

    /**
     * 预留字段1
     */
    private String standby1;

    /**
     * 预留字段2
     */
    private String standby2;

    /**
     * 获取主键
     *
     * @return urid - 主键
     */
    public Integer getUrid() {
        return urid;
    }

    /**
     * 设置主键
     *
     * @param urid 主键
     */
    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    /**
     * 获取用户ID
     *
     * @return userid - 用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取角色ID
     *
     * @return roleid - 角色ID
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置角色ID
     *
     * @param roleid 角色ID
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取预留字段1
     *
     * @return standby1 - 预留字段1
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置预留字段1
     *
     * @param standby1 预留字段1
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 获取预留字段2
     *
     * @return standby2 - 预留字段2
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置预留字段2
     *
     * @param standby2 预留字段2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }
}