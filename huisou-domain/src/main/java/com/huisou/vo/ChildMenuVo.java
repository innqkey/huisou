package com.huisou.vo;

/**
 * author： xueyuan
 * date  ： 2017-08-03 下午3:03
 */
public class ChildMenuVo {

    private Integer menuid;
    private String url;
    private String menuname;
    private Integer menucode;
    private Integer fatherid;
    private String fathername;
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getMenucode() {
        return menucode;
    }

    public void setMenucode(Integer menucode) {
        this.menucode = menucode;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }
}

