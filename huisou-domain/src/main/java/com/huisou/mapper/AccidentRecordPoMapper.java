package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.common.MyMapper;
import com.huisou.po.AccidentRecordPo;

public interface AccidentRecordPoMapper extends MyMapper<AccidentRecordPo> {

	// 多条件查询事故记录
	List<AccidentRecordPo> queryByMultiParas(Map<String, String> maps);
}