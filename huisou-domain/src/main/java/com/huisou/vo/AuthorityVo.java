package com.huisou.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-07-17 下午7:48
 */
public class AuthorityVo  implements Serializable {
    private String rolename;
    private int menuid;
    private String menuurl;
    private String menuname;
    private String menucode;
    private int menufatherid;
    private String menufathername;
    private int sourcesid;
    private String sourcesurl;
    private String sourcesname;
    private String remark;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
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

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public int getMenufatherid() {
        return menufatherid;
    }

    public void setMenufatherid(int menufatherid) {
        this.menufatherid = menufatherid;
    }

    public String getMenufathername() {
        return menufathername;
    }

    public void setMenufathername(String menufathername) {
        this.menufathername = menufathername;
    }

    public int getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(int sourcesid) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
