package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_authority")
public class AuthorityPo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityid;

    /**
     * 菜单id
     */
    private Integer menuid;

    /**
     * 资源id
     */
    private Integer sourcesid;

    /**
     * 资源可用状态：1为可用 0为不可用
     */
    private Integer status;

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
     * @return authorityid - 主键
     */
    public Integer getAuthorityid() {
        return authorityid;
    }

    /**
     * 设置主键
     *
     * @param authorityid 主键
     */
    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
    }

    /**
     * 获取菜单id
     *
     * @return menuid - 菜单id
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * 设置菜单id
     *
     * @param menuid 菜单id
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取资源id
     *
     * @return sourcesid - 资源id
     */
    public Integer getSourcesid() {
        return sourcesid;
    }

    /**
     * 设置资源id
     *
     * @param sourcesid 资源id
     */
    public void setSourcesid(Integer sourcesid) {
        this.sourcesid = sourcesid;
    }

    /**
     * 获取资源可用状态：1为可用 0为不可用
     *
     * @return status - 资源可用状态：1为可用 0为不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置资源可用状态：1为可用 0为不可用
     *
     * @param status 资源可用状态：1为可用 0为不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
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