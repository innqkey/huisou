package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TrainingRecordPoMapper;
import com.huisou.po.EmployeePo;
import com.huisou.po.TrainingRecordPo;
import com.huisou.service.TrainingRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingRecordVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/
@Service
public class TrainingRecordServiceImpl implements TrainingRecordService{

	@Autowired
	private TrainingRecordPoMapper trainingRecordPoMapper;
	
	@Override
	public void saveOne(TrainingRecordPo trainingRecordPo) {
		// TODO Auto-generated method stub
		trainingRecordPoMapper.insertSelective(trainingRecordPo);
	}
	
	@Override
	public TrainingRecordPo selectOneById(Integer trainingrecordid) {
		// TODO Auto-generated method stub
		TrainingRecordPo trainingRecordPo = new TrainingRecordPo();
		trainingRecordPo.setTrainingrecordid(trainingrecordid);
		return trainingRecordPoMapper.selectOne(trainingRecordPo);
	}

	@Override
	public void updateTrainingRecord(TrainingRecordPo trainingRecordPo) {
		// TODO Auto-generated method stub
		trainingRecordPoMapper.updateByPrimaryKeySelective(trainingRecordPo);
	}

	@Override
	public TrainingRecordVo queryByParams(Integer trainingrecordid) {
		// TODO Auto-generated method stub
		return trainingRecordPoMapper.queryByParams(trainingrecordid);
	}

	@Override
	public PageInfo<TrainingRecordVo> queryByMultiParas(Map<String, String> maps, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());	
		List<TrainingRecordVo> resultMap = trainingRecordPoMapper.selectByMultiParas(maps);
		return new PageInfo<TrainingRecordVo>(resultMap);
	}

	@Override
	public int findMaxTrainingCount(int intentioncustid) {
		// TODO Auto-generated method stub
		TrainingRecordPo select = trainingRecordPoMapper.selectMaxTrainingCount(intentioncustid);
		if (select == null){
			return 0;
		} else {
			return select.getTrainingcount();
		}
	}

}
