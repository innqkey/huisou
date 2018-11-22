package com.huisou.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.EmployeePo;
import com.huisou.po.IntentionCustPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.TrainingCoursePo;
import com.huisou.po.TrainingRecordPo;
import com.huisou.po.UserPo;
import com.huisou.service.IntentionCustService;
import com.huisou.service.IntentionItemService;
import com.huisou.service.SalesmanService;
import com.huisou.service.TrainingCourseService;
import com.huisou.service.TrainingRecordService;
import com.huisou.service.UserService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TrainingRecordVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/
@RestController
@RequestMapping(value="/training")
public class TrainingRecordController extends BaseController{

	@Autowired
	private TrainingRecordService trainingRecordService;
	@Autowired
	private IntentionCustService intentionCustService;
	@Autowired
	private UserService userService;
	@Autowired
	private IntentionItemService intentionItemService;
	@Autowired
	private TrainingCourseService trainingCourseService;
	
	/**
	 * 保存一条培训人记录,保存意向客户
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request){
		try {
			String custName = request.getParameter("name");
			String custPhone = request.getParameter("phone");
			String courseId = request.getParameter("courseid");
			if (StringUtils.isEmpty(custName) || StringUtils.isEmpty(custPhone)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			int intentioncustid = 0;
			int saleid = 0;
			boolean custExist = intentionCustService.checkIntentionCustExist(custPhone, custName);
			if (custExist){
				IntentionCustPo intentionCustPo = intentionCustService.findCustIdByParams(custPhone, custName);
				intentionCustPo.setAddress(request.getParameter("address"));
				intentionCustPo.setCompanyname(request.getParameter("companyname"));
				intentionCustService.updateIntentionCust(intentionCustPo);
				intentioncustid = intentionCustPo.getIntentioncustid();
				saleid = intentionCustPo.getCreateby();
			} else {
				IntentionCustPo intentionCustPo = new IntentionCustPo();
				intentionCustPo.setName(custName);
				intentionCustPo.setPhone(custPhone);
				intentionCustPo.setAddress(request.getParameter("address"));
				intentionCustPo.setCompanyname(request.getParameter("companyname"));
				intentionCustPo.setCreateby(super.getUserId(request));
				intentionCustPo.setCreatedate(new Date());
				intentioncustid = intentionCustService.addIntentionCust(intentionCustPo);
			}
			int courseid = 0;
			if (StringUtils.isEmpty(request.getParameter("courseid"))){
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
				courseid = trainingCourseService.addTrainingCourse(trainingCoursePo);
			} else {
				courseid = Integer.valueOf(request.getParameter("courseid"));				
			}
			TrainingRecordPo trainingRecordPo = new TrainingRecordPo();
			trainingRecordPo.setCourseid(courseid);
			trainingRecordPo.setIntentioncustid(intentioncustid);
			trainingRecordPo.setCreateby(super.getUserId(request));
			trainingRecordPo.setCreatedate(new Date());
			int trainingCount = trainingRecordService.findMaxTrainingCount(intentioncustid);

			trainingRecordPo.setTrainingcount(trainingCount + 1);
			if (saleid != 0){
				trainingRecordPo.setSaleid(Integer.valueOf(saleid));
			}
			trainingRecordService.saveOne(trainingRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResUtils.okRes();
	}
	
	/**
	 * 修改一条培训记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(HttpServletRequest request){
		try {
			String custName = request.getParameter("name");
			String custPhone = request.getParameter("phone");
			String courseId = request.getParameter("courseid");
			String trainingrecordid = request.getParameter("trainingrecordid");
			if (StringUtils.isEmpty(custName) || StringUtils.isEmpty(custPhone) || 
					StringUtils.isEmpty(courseId) || StringUtils.isEmpty(trainingrecordid)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			TrainingRecordPo trainingRecordPo = trainingRecordService.selectOneById(Integer.valueOf(trainingrecordid));
			int intentioncustid = 0;
			boolean custExist = intentionCustService.checkIntentionCustExist(custPhone, custName);
			if (custExist){
				IntentionCustPo intentionCustPo = intentionCustService.findCustIdByParams(custPhone, custName);
				intentioncustid = intentionCustPo.getIntentioncustid();
				intentionCustPo.setName(custName);
				intentionCustPo.setPhone(custPhone);
				intentionCustPo.setAddress(request.getParameter("address"));
				intentionCustPo.setCompanyname(request.getParameter("companyname"));
				intentionCustPo.setUpdateby(super.getUserId(request));
				intentionCustService.updateIntentionCust(intentionCustPo);
			} else {
				IntentionCustPo intentionCustPo = new IntentionCustPo();
				TrainingRecordPo trainingPo = new TrainingRecordPo();
				intentionCustPo.setName(custName);
				intentionCustPo.setPhone(custPhone);
				intentionCustPo.setAddress(request.getParameter("address"));
				intentionCustPo.setCompanyname(request.getParameter("companyname"));
				intentionCustPo.setCreateby(super.getUserId(request));
				intentionCustPo.setCreatedate(new Date());
				intentioncustid = intentionCustService.addIntentionCust(intentionCustPo);
				trainingPo.setCourseid(Integer.valueOf(courseId));
				trainingPo.setIntentioncustid(intentioncustid);
				trainingPo.setCreateby(super.getUserId(request));
				trainingPo.setCreatedate(new Date());
				trainingPo.setTrainingcount(1);
				trainingRecordService.saveOne(trainingPo);
				return ResUtils.okRes();
			}
			int courseid = Integer.valueOf(courseId);
			TrainingCoursePo selectOne = trainingCourseService.selectOne(courseid);
			selectOne.setCoursetime(DateUtils.format(request.getParameter("coursetime"), DateUtils.Y_M_D));
			if (StringUtils.isNotBlank(request.getParameter("courseaddress"))){
				selectOne.setCourseaddress(request.getParameter("courseaddress"));
				selectOne.setUpdateby(super.getUserId(request));
			}
			trainingCourseService.updateCourse(selectOne);
			intentionItemService.updateItemByCourseid(courseid, intentioncustid,super.getUserId(request));
			trainingRecordPo.setCourseid(courseid);
			trainingRecordPo.setIntentioncustid(intentioncustid);
			trainingRecordPo.setUpdateby(super.getUserId(request));
			trainingRecordService.updateTrainingRecord(trainingRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResUtils.okRes();
	}
	
	/**
	 * 根据id查找一条培训记录
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/find")
	public String find(String trainingrecordid){
		try {
			if (StringUtils.isEmpty(trainingrecordid)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			int recordid = Integer.valueOf(trainingrecordid);
			TrainingRecordVo trainingRecordVo = trainingRecordService.queryByParams(recordid);
			return ResUtils.okRes(trainingRecordVo);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResUtils.okRes();
	}
	/**
	 * 条件查找培训记录
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, PageTemp pageTemp){
		try {
			String name = StringUtils.stripToEmpty(request.getParameter("name"));
			String companyname = StringUtils.stripToEmpty(request.getParameter("companyname"));
			String salename = StringUtils.stripToEmpty(request.getParameter("salename"));
			String username = StringUtils.stripToEmpty(request.getParameter("username"));
			String coursename = StringUtils.stripToEmpty(request.getParameter("coursename"));
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("name", name);
			maps.put("companyname", companyname);
			maps.put("salename", salename);
			maps.put("username", username);
			maps.put("coursename", coursename);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			if (super.getLeader(request)){
				String createby = String.valueOf(super.getUserId(request));
				maps.put("createby", createby);
			} else{
				maps.put("createby", null);
			}
			PageInfo<TrainingRecordVo> poList = trainingRecordService.queryByMultiParas(maps,pageTemp);
	        return ResUtils.okRes(poList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
}
