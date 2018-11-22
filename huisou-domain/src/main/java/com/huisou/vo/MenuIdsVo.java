package com.huisou.vo;

import java.io.Serializable;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-07 上午10:28
 */
public class MenuIdsVo implements Serializable {
    Integer fatherId;
    List<Integer> sourcrsIds;

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public List<Integer> getSourcrsIds() {
        return sourcrsIds;
    }

    public void setSourcrsIds(List<Integer> sourcrsIds) {
        this.sourcrsIds = sourcrsIds;
    }
}
