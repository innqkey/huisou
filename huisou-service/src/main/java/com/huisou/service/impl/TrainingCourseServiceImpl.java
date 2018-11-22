package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.DateUtil;
import com.common.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TrainingCoursePoMapper;
import com.huisou.po.TrainingCoursePo;
import com.huisou.service.TrainingCourseService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:15:10 
* 类说明 
*/
@Service
public class TrainingCourseServiceImpl implements TrainingCourseService{

	@Autowired
	private TrainingCoursePoMapper trainingCoursePoMapper;
	
	@Override
	public int addTrainingCourse(TrainingCoursePo trainingCoursePo) {
		// TODO Auto-generated method stub
		trainingCoursePoMapper.insert(trainingCoursePo);
		return trainingCoursePo.getCourseid();
	}

	@Override
	public void updateTrainingCourse(String courseid,String coursename,String courseaddress,String coursedays,String coursetime) {
		// TODO Auto-generated method stub
		trainingCoursePoMapper.updateOne(Integer.parseInt(courseid),coursename,courseaddress,coursedays,coursetime);
	}

	@Override
	public PageInfo<TrainingCourseVo> getIndex(String coursename, String startDate, String endDate, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<TrainingCourseVo> list = trainingCoursePoMapper.getIndex(coursename,startDate,endDate);
		for (TrainingCourseVo trainingCourseVo : list) {
		 Integer invitecount =	trainingCoursePoMapper.getInvitecount(trainingCourseVo.getCourseid());
		 Integer traincount = trainingCoursePoMapper.getTraincount(trainingCourseVo.getCourseid());
		 trainingCourseVo.setInvitecount(invitecount);
		 trainingCourseVo.setTraincount(traincount);
		}
	
		return new PageInfo<>(list);
	}

	@Override
	public TrainingCoursePo selectOne(Integer courseid) {
		// TODO Auto-generated method stub
		return trainingCoursePoMapper.findTrainingCourseBycourseid(courseid);
	}

	@Override
	public List<TrainingCourseVo> getCoursenameAndCoursetime() {
		// TODO Auto-generated method stub
		List<TrainingCourseVo> list = trainingCoursePoMapper.findAll();
		for (TrainingCourseVo trainingCourseVo : list) {
			Date courseDate = trainingCourseVo.getCoursetime();
			String coursetime = DateUtil.formatDate(courseDate, "yyyy-MM-dd");
			trainingCourseVo.setCoursenameandtime(trainingCourseVo.getCoursename()+"("+coursetime+")");
		}
		return list;
	}

	@Override
	public void updateCourse(TrainingCoursePo selectOne) {
		trainingCoursePoMapper.updateByPrimaryKeySelective(selectOne);
	}

}
