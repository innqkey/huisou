package com.huisou.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-07-20 上午11:40
 */
public class MenuSourcesVo  implements Serializable {

    public Integer menuid;
    public String menuurl;
    public String menuname;
    public String childurl;
    public String childname;
    public Integer sourcesid;
    public String sourcesurl;
    public String sourcesname;
    public Integer sourcesfatherid;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getChildurl() {
        return childurl;
    }

    public void setChildurl(String childurl) {
        this.childurl = childurl;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public Integer getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(Integer sourcesid) {
        this.sourcesid = sourcesid;
    }

    public String getSourcesurl() {
        return sourcesurl;
    }

    public void setSourcesurl(String sourcesurl) {
        this.sourcesurl = sourcesurl;
    }

    public String getSourcesname() {
        return sourcesname;
    }

    public void setSourcesname(String sourcesname) {
        this.sourcesname = sourcesname;
    }

    public Integer getSourcesfatherid() {
        return sourcesfatherid;
    }

    public void setSourcesfatherid(Integer sourcesfatherid) {
        this.sourcesfatherid = sourcesfatherid;
    }
}
