package com.huisou.vo;

import java.io.Serializable;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 下午2:16
 */
public class MenuVo implements Serializable {

    private Integer menuid;
    private String url;
    private String menuname;
    private Integer menucode;
    private Integer fatherid;
    private String target;
    private List<menu> menuList;

    public List<menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<menu> menuList) {
        this.menuList = menuList;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public static class menu implements Serializable {
        private Integer menuid;
        private String url;
        private String menuname;
        private Integer menucode;
        private Integer fatherid;
        private List<SourcesVo> sourcesVoList;

        public List<SourcesVo> getSourcesVoList() {
            return sourcesVoList;
        }

        public void setSourcesVoList(List<SourcesVo> sourcesVoList) {
            this.sourcesVoList = sourcesVoList;
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
    }
}
