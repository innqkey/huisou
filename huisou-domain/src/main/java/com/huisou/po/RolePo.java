package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_role")
public class RolePo {
    /**
     * 主键
     */
    @Id
    private Integer roleid;

    /**
     * 角色名字
     */
    private String rolename;

    /**
     * 角色状态：  1为有效  0 为无效
     */
    private Integer rolestatus;

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
     * @return roleid - 主键
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置主键
     *
     * @param roleid 主键
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取角色名字
     *
     * @return rolename - 角色名字
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名字
     *
     * @param rolename 角色名字
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * 获取角色状态：  1为有效  0 为无效
     *
     * @return rolestatus - 角色状态：  1为有效  0 为无效
     */
    public Integer getRolestatus() {
        return rolestatus;
    }

    /**
     * 设置角色状态：  1为有效  0 为无效
     *
     * @param rolestatus 角色状态：  1为有效  0 为无效
     */
    public void setRolestatus(Integer rolestatus) {
        this.rolestatus = rolestatus;
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