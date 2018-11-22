package com.huisou.po;

import javax.persistence.Transient;

/**
 * author:  xueyuan
 * date  :  2017-07-05 15:43.
 */
public class BasePo {
    @Transient
    private Integer page = 1;

    @Transient
    private Integer number = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
