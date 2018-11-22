package com.huisou.service;

import com.huisou.po.DailyPo;

import java.util.List;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午4:11
 */
public interface DailyService {

    List<DailyPo> findAll(Map map);

    List<DailyPo> findAllUnRead(Map map);

    DailyPo findOne(Integer dailyid);

    int updateStatus(Integer dailyid);

    int updateDaily(DailyPo dailyPo);

    int insert(DailyPo dailyPo);
}
