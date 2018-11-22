package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_role_authority")
public class RoleAuthorityPo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raid;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 权限id
     */
    private Integer authorityid;

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
     * @return raid - 主键
     */
    public Integer getRaid() {
        return raid;
    }

    /**
     * 设置主键
     *
     * @param raid 主键
     */
    public void setRaid(Integer raid) {
        this.raid = raid;
    }

    /**
     * 获取角色id
     *
     * @return roleid - 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取权限id
     *
     * @return authorityid - 权限id
     */
    public Integer getAuthorityid() {
        return authorityid;
    }

    /**
     * 设置权限id
     *
     * @param authorityid 权限id
     */
    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
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