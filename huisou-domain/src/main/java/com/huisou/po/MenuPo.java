package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_menu")
public class MenuPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuid;

    /**
     * 菜单的url
     */
    private String url;

    /**
     * 菜单名称
     */
    private String menuname;

    /**
     * 是否为主菜单(1为主菜单，0不是主菜单)
     */
    private Integer menucode;

    /**
     * 上级菜单id 没有则为空
     */
    private Integer fatherid;

    /**
     * 目标html名称
     */
    private String target;

    /**
     * 预留字段1
     */
    private String standby1;

    /**
     * 预留字段2
     */
    private String standby2;

    /**
     * @return menuid
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * @param menuid
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取菜单的url
     *
     * @return url - 菜单的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单的url
     *
     * @param url 菜单的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取菜单名称
     *
     * @return menuname - 菜单名称
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * 设置菜单名称
     *
     * @param menuname 菜单名称
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    /**
     * 获取是否为主菜单(1为主菜单，0不是主菜单)
     *
     * @return menucode - 是否为主菜单(1为主菜单，0不是主菜单)
     */
    public Integer getMenucode() {
        return menucode;
    }

    /**
     * 设置是否为主菜单(1为主菜单，0不是主菜单)
     *
     * @param menucode 是否为主菜单(1为主菜单，0不是主菜单)
     */
    public void setMenucode(Integer menucode) {
        this.menucode = menucode;
    }

    /**
     * 获取上级菜单id 没有则为空
     *
     * @return fatherid - 上级菜单id 没有则为空
     */
    public Integer getFatherid() {
        return fatherid;
    }

    /**
     * 设置上级菜单id 没有则为空
     *
     * @param fatherid 上级菜单id 没有则为空
     */
    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    /**
     * 获取目标html名称
     *
     * @return target - 目标html名称
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标html名称
     *
     * @param target 目标html名称
     */
    public void setTarget(String target) {
        this.target = target;
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