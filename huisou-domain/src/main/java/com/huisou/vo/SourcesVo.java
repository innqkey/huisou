package com.huisou.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-07-19 下午3:09
 */
public class SourcesVo  implements Serializable {

    private Integer sourcesid;
    private String url;
    private String sourcesname;
    private Integer menuid;
    private Integer mainmenuid;
    private String target;

    public Integer getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(Integer sourcesid) {
        this.sourcesid = sourcesid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourcesname() {
        return sourcesname;
    }

    public void setSourcesname(String sourcesname) {
        this.sourcesname = sourcesname;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getMainmenuid() {
        return mainmenuid;
    }

    public void setMainmenuid(Integer mainmenuid) {
        this.mainmenuid = mainmenuid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
