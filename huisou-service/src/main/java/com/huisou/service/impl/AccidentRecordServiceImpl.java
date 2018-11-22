package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.AccidentRecordPoMapper;
import com.huisou.po.AccidentRecordPo;
import com.huisou.service.AccidentRecordService;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月13日
*/

@Service
public class AccidentRecordServiceImpl implements AccidentRecordService{

	@Autowired
	AccidentRecordPoMapper accidentRecordPoMapper;
	
	@Override
	public PageInfo<AccidentRecordPo> queryByMultiParas(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<AccidentRecordPo> resultMap = accidentRecordPoMapper.queryByMultiParas(maps);
		return new PageInfo<AccidentRecordPo>(resultMap);
	}

	@Override
	public int insertAccidentPo(AccidentRecordPo accidentRecordPo) {
		accidentRecordPoMapper.insertSelective(accidentRecordPo);	
		return accidentRecordPoMapper.selectOne(accidentRecordPo).getAccidentid();		
	}

	@Override
	public AccidentRecordPo queryAccidentPoById(int accidentid) {
		AccidentRecordPo po = new AccidentRecordPo();
		po.setAccidentid(accidentid);
		return accidentRecordPoMapper.selectOne(po);
	}

	@Override
	public int updateAccidentPo(AccidentRecordPo accidentRecordPo) {
		return accidentRecordPoMapper.updateByPrimaryKeySelective(accidentRecordPo);
	}

}
