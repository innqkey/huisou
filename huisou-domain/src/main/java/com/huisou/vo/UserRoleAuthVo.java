package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-07-20 上午10:27
 * 用户角色权限 三合一查询结果表
 */
public class UserRoleAuthVo implements Serializable {
    private Integer menuid;
    private String menuurl;
    private String menuname;
    private String childurl;
    private String childname;
    private Integer sourcesid;
    private Integer status;
    private String sourcesurl;
    private String childtarget;
    private String menutarget;
    private Integer menustatus;
    private String sourcesname;
    private Integer sourcesfatherid;
    private String sourcestarget;
    private String username;
    private String password;
    private String petname;
    private Integer userstatus;
    private Integer roleid;
    private String rolename;
    private Integer userid;
    private String salename;
    private String salephone;
    private String saleweixin;
    private String saleemail;
    private Integer salestatus;
    private String createdate;
    private String updatedate;

    private List<MenuList> menuLists;

    public List<MenuList> getMenuLists() {
        return menuLists;
    }

    public String getSourcestarget() {
        return sourcestarget;
    }

    public void setSourcestarget(String sourcestarget) {
        this.sourcestarget = sourcestarget;
    }

    public Integer getMenustatus() {
        return menustatus;
    }

    public void setMenustatus(Integer menustatus) {
        this.menustatus = menustatus;
    }

    public void setMenuLists(List<MenuList> menuLists) {
        this.menuLists = menuLists;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSalename() {
        return salename;
    }

    public void setSalename(String salename) {
        this.salename = salename;
    }

    public String getSalephone() {
        return salephone;
    }

    public void setSalephone(String salephone) {
        this.salephone = salephone;
    }

    public String getSaleweixin() {
        return saleweixin;
    }

    public void setSaleweixin(String saleweixin) {
        this.saleweixin = saleweixin;
    }

    public String getSaleemail() {
        return saleemail;
    }

    public void setSaleemail(String saleemail) {
        this.saleemail = saleemail;
    }

    public Integer getSalestatus() {
        return salestatus;
    }

    public void setSalestatus(Integer salestatus) {
        this.salestatus = salestatus;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getMenutarget() {
        return menutarget;
    }

    public void setMenutarget(String menutarget) {
        this.menutarget = menutarget;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChildtarget() {
        return childtarget;
    }

    public void setChildtarget(String childtarget) {
        this.childtarget = childtarget;
    }

    public static class MenuList implements Serializable {
        private Integer menuid;
        private String menuurl;
        private String menuname;
        private String menutarget;
        private Integer menustatus;
        private List<ChildList> childList;

        public String getMenutarget() {
            return menutarget;
        }

        public void setMenutarget(String menutarget) {
            this.menutarget = menutarget;
        }

        public Integer getMenustatus() {
            return menustatus;
        }

        public void setMenustatus(Integer menustatus) {
            this.menustatus = menustatus;
        }

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

        public List<ChildList> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildList> childList) {
            this.childList = childList;
        }
    }

    public static class ChildList implements Serializable {
        private Integer childid;
        private String childurl;
        private String childname;
        private String childtarget;
        private Integer childstatus;
//        private List<SourcesList> sourcesLists;

        private  Map map;

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public String getChildtarget() {
            return childtarget;
        }

        public void setChildtarget(String childtarget) {
            this.childtarget = childtarget;
        }

        public Integer getChildstatus() {
            return childstatus;
        }

        public void setChildstatus(Integer childstatus) {
            this.childstatus = childstatus;
        }

        public Integer getChildid() {
            return childid;
        }

        public void setChildid(Integer childid) {
            this.childid = childid;
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

//        public List<SourcesList> getSourcesLists() {
//            return sourcesLists;
//        }
//
//        public void setSourcesLists(List<SourcesList> sourcesLists) {
//            this.sourcesLists = sourcesLists;
//        }
    }

    public static class SourcesList implements Serializable {
        private Integer sourcesid;
        private Integer sourcesstatus;
        private String sourcesurl;
        private String sourcesname;
        private String sourcestarget;
        private Map map;

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public Integer getSourcesid() {
            return sourcesid;
        }

        public void setSourcesid(Integer sourcesid) {
            this.sourcesid = sourcesid;
        }

        public Integer getSourcesstatus() {
            return sourcesstatus;
        }

        public void setSourcesstatus(Integer sourcesstatus) {
            this.sourcesstatus = sourcesstatus;
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

        public String getSourcestarget() {
            return sourcestarget;
        }

        public void setSourcestarget(String sourcestarget) {
            this.sourcestarget = sourcestarget;
        }
    }
}
