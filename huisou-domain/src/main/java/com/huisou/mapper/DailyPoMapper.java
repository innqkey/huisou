package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.DailyPo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DailyPoMapper extends MyMapper<DailyPo> {

    List<DailyPo> findAll(Map map);

    List<DailyPo> findAllUnRead(Map map);

    int updateStatus(Integer dailyid);

    int updateDaily(DailyPo dailyPo);

    int insertBackId(DailyPo dailyPo);
}