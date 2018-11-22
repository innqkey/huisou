package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.DateUtil;
import com.common.DateUtils;
import com.huisou.mapper.ClickUrlRecordPoMapper;
import com.huisou.po.ClickUrlRecordPo;
import com.huisou.service.ClickUrlRecordService;
import com.huisou.vo.ClickUrlRecordVo;

/** 
* @author qinkai 
* @date 2018年1月16日
*/
@Service
public class ClickUrlRecordServiceImpl implements ClickUrlRecordService{
	@Autowired
	private ClickUrlRecordPoMapper clickUrlRecordPoMapper;
	@Override
	public List<ClickUrlRecordVo> custClickCount(Integer custuserid) {
		String beginDate = DateUtils.format(DateUtil.addDays(-7), DateUtils.Y_M_D);
		String endDate = DateUtils.format(new Date(), DateUtils.Y_M_D);
		List<ClickUrlRecordVo> poList = clickUrlRecordPoMapper.findAll(beginDate,endDate,custuserid);
		return poList;
	}
	@Override
	public void insert(ClickUrlRecordPo clickUrlRecordPo) {
		clickUrlRecordPoMapper.insert(clickUrlRecordPo);
	}

}
