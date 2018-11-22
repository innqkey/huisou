package com.huisou.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.common.MyMapper;
import com.huisou.po.RemindSetPo;

@Mapper
public interface RemindSetPoMapper extends MyMapper<RemindSetPo> {
}