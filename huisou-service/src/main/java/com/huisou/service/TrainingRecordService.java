package com.huisou.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TrainingRecordPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingRecordVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/

public interface TrainingRecordService {

	/**
	 * 保存一条培训记录
	 * @param trainingRecordPo
	 */
	void saveOne(TrainingRecordPo trainingRecordPo);

	/**
	 * 根据id查询一条培训记录
	 * @param trainingrecordid
	 * @return
	 */
	TrainingRecordPo selectOneById(Integer trainingrecordid);

	/**
	 * 更新一条培训记录
	 * @param trainingRecordPo
	 */
	void updateTrainingRecord(TrainingRecordPo trainingRecordPo);

	/**
	 * 查找培训记录
	 * @param trainingrecordid
	 * @return
	 */
	TrainingRecordVo queryByParams(Integer trainingrecordid);

	/**
	 * 条件查询培训记录
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<TrainingRecordVo> queryByMultiParas(Map<String, String> maps, PageTemp pageTemp);

	/**
	 * 查找客户最大培训次数
	 * @param intentioncustid
	 * @return
	 */
	int findMaxTrainingCount(int intentioncustid);

}
