package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.TrainingCoursePo;
import com.huisou.vo.TrainingCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:43:02 
* 类说明 
*/
public interface TrainingCoursePoMapper extends MyMapper<TrainingCoursePo>{

	List<TrainingCourseVo> getIndex(@Param("coursename")String coursename, @Param("startDate")String startDate, @Param("endDate")String endDate);

	TrainingCoursePo findTrainingCourseBycourseid(@Param("courseid")Integer courseid);

	List<TrainingCourseVo> findAll();

	Integer getInvitecount(@Param("courseid")Integer courseid);

	Integer getTraincount(@Param("courseid")Integer courseid);

	void updateOne(@Param("courseid")Integer courseid, @Param("coursename")String coursename, @Param("courseaddress")String courseaddress, @Param("coursedays")String coursedays, @Param("coursetime")String coursetime);



}
