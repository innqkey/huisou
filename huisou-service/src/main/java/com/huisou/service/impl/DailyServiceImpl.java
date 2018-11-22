package com.huisou.service.impl;

import com.github.pagehelper.PageHelper;
import com.huisou.mapper.DailyPoMapper;
import com.huisou.po.DailyPo;
import com.huisou.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午4:11
 */
@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    DailyPoMapper dailyPoMapper;


    @Override
    public List<DailyPo> findAll(Map map) {
        PageHelper.startPage((int) map.get("pageNum"), (int) map.get("pageSize"));
        return dailyPoMapper.findAll(map);
    }

    @Override
    public List<DailyPo> findAllUnRead(Map map) {
//        PageHelper.startPage((int) map.get("pageNum"), (int) map.get("pageSize"));
        return dailyPoMapper.findAllUnRead(map);
    }

    @Override
    public DailyPo findOne(Integer dailyid) {
        return dailyPoMapper.selectByPrimaryKey(dailyid);
    }

    @Override
    public int updateStatus(Integer dailyid) {
        return dailyPoMapper.updateStatus(dailyid);
    }

    @Override
    public int updateDaily(DailyPo dailyPo) {
//        return dailyPoMapper.updateDaily(dailyPo);
        return dailyPoMapper.updateByPrimaryKeySelective(dailyPo);
    }

    @Override
    public int insert(DailyPo dailyPo) {
        return dailyPoMapper.insertBackId(dailyPo);
    }


}
