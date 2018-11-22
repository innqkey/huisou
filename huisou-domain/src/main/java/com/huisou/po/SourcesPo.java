package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_sources")
public class SourcesPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sourcesid;

    /**
     * url
     */
    private String url;

    /**
     * 资源名称
     */
    private String sourcesname;

    /**
     * 菜单id
     */
    private Integer menuid;

    /**
     * 预留字段1
     */
    private String target;

    /**
     * 预留字段2
     */
    private String standby2;

    /**
     * @return sourcesid
     */
    public Integer getSourcesid() {
        return sourcesid;
    }

    /**
     * @param sourcesid
     */
    public void setSourcesid(Integer sourcesid) {
        this.sourcesid = sourcesid;
    }

    /**
     * 获取url
     *
     * @return url - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取资源名称
     *
     * @return sourcesname - 资源名称
     */
    public String getSourcesname() {
        return sourcesname;
    }

    /**
     * 设置资源名称
     *
     * @param sourcesname 资源名称
     */
    public void setSourcesname(String sourcesname) {
        this.sourcesname = sourcesname;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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