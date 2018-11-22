package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.TrainingRecordPo;
import com.huisou.vo.TrainingRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午3:11:40 
* 类说明 
*/
public interface TrainingRecordPoMapper extends MyMapper<TrainingRecordPo>{

	TrainingRecordVo queryByParams(@Param("trainingrecordid") Integer trainingrecordid);

	List<TrainingRecordVo> selectByMultiParas(Map<String, String> maps);

	TrainingRecordPo selectMaxTrainingCount(@Param("intentioncustid")int intentioncustid);

}
