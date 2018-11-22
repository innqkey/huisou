package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.TrainingCoursePo;
import com.huisou.service.CustomerService;
import com.huisou.service.TrainingCourseService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingCourseVo;



/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:08:36 
* 类说明 
*/
@RequestMapping(value = "/trainingCourse")
@RestController
public class TrainingCourseController extends BaseController{
	
	@Autowired
	private TrainingCourseService trainingCourseService;
	
	/**
	 * 添加培训课程
	 * @param request
	 * @param trainingCoursePo
	 * @return
	 */
	@RequestMapping(value="/addTrainingCourse")
	public String addTrainingCourse(HttpServletRequest request){
		try {
			String coursename = request.getParameter("coursename");
			String courseaddress = request.getParameter("courseaddress");
			String coursedays = request.getParameter("coursedays");
			String coursetime = request.getParameter("coursetime");
			TrainingCoursePo trainingCoursePo = new TrainingCoursePo();
			trainingCoursePo.setCoursename(coursename);
			trainingCoursePo.setCourseaddress(courseaddress);
			trainingCoursePo.setCoursedays(coursedays);
			trainingCoursePo.setCoursetime(DateUtils.format(coursetime, DateUtils.Y_M_D));
			trainingCoursePo.setCreateby(super.getUserId(request));
			trainingCoursePo.setCreatedate(new Date());
			trainingCourseService.addTrainingCourse(trainingCoursePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改培训课程
	 * @param request
	 * @param trainingCoursePo
	 * @return
	 */
	@RequestMapping(value="/updateTrainingCourse")
	public String updateTrainingCourse(HttpServletRequest request){
		try {
			String courseid = request.getParameter("courseid");
			String coursename = request.getParameter("coursename");
			String courseaddress = request.getParameter("courseaddress");
			String coursedays = request.getParameter("coursedays");
			String coursetime = request.getParameter("coursetime");
			if(StringUtils.isEmpty(courseid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			/*TrainingCoursePo trainingCoursePo = new TrainingCoursePo();
			trainingCoursePo.setCourseid(Integer.parseInt(courseid));
			trainingCoursePo.setCoursename(coursename);
			trainingCoursePo.setCourseaddress(courseaddress);
			trainingCoursePo.setCoursedays(coursedays);
			trainingCoursePo.setCoursetime(DateUtils.format(coursetime, DateUtils.Y_M_D));*/
			trainingCourseService.updateTrainingCourse(courseid,coursename,courseaddress,coursedays,coursetime);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 培训课程列表首页数据展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String coursename = request.getParameter("coursename");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			PageInfo<TrainingCourseVo> pageinfo = trainingCourseService.getIndex(coursename,startDate,endDate,pageTemp);
			return ResUtils.okRes(pageinfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	/**
	 * 获取所有的课程姓名和课程时间和所有的课程数据
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value="/getCoursenameAndCoursetime")
	public String getCoursenameAndCoursetime(HttpServletRequest request){
		try {
			List<TrainingCourseVo> list = trainingCourseService.getCoursenameAndCoursetime();
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据课程id获取课程信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findCourse")
	public String findCourse(HttpServletRequest request){
		try {
			String courseid = request.getParameter("courseid");
			if(StringUtils.isEmpty("courseid")){
				return ResUtils.errRes("102", "请求参数");
			}
			TrainingCoursePo coursePo = trainingCourseService.selectOne(Integer.parseInt(courseid));
			return ResUtils.okRes(coursePo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
