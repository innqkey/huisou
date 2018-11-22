package com.huisou.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.AccidentRecordPo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月13日
*/

public interface AccidentRecordService {

	// 多条件查找事故记录表
	PageInfo<AccidentRecordPo> queryByMultiParas(Map<String, String> maps, PageTemp pageTemp);

	// 保存一条事故记录
	int insertAccidentPo(AccidentRecordPo accidentRecordPo);

	// 根据id查询事故记录
	AccidentRecordPo queryAccidentPoById(int accidentid);

	// 更新事故记录
	int updateAccidentPo(AccidentRecordPo accidentRecordPo);

}
