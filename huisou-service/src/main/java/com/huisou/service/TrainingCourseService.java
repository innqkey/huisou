package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TrainingCoursePo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:14:33 
* 类说明 
*/
public interface TrainingCourseService {

	int addTrainingCourse(TrainingCoursePo trainingCoursePo);

	void updateTrainingCourse(String courseid,String coursename,String courseaddress,String coursedays,String coursetime);
	
	/**
	 * 课程列表首页数据获取
	 * @param coursename
	 * @param startDate
	 * @param endDate
	 * @param pageTemp
	 * @return
	 */
	PageInfo<TrainingCourseVo> getIndex(String coursename, String startDate, String endDate,PageTemp pageTemp);

	TrainingCoursePo selectOne(Integer courseid);

	List<TrainingCourseVo> getCoursenameAndCoursetime();

	/**
	 * 更新一个课程实体
	 * @param selectOne
	 */
	void updateCourse(TrainingCoursePo selectOne);
}
