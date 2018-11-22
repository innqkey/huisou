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
import com.huisou.po.IntentionCustPo;
import com.huisou.po.InviteRecordPo;
import com.huisou.po.TrainingCoursePo;
import com.huisou.service.IntentionCustService;
import com.huisou.service.InviteRecordService;
import com.huisou.service.TrainingCourseService;
import com.huisou.vo.InviteRecordVo;
import com.huisou.vo.PageTemp;



/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:42:27 
* 类说明 
*/
@RequestMapping(value = "/inviteRecord")
@RestController
public class InviteRecordController extends BaseController{

	@Autowired
	private InviteRecordService inviteRecordService;
	
	@Autowired
	private IntentionCustService intentionCustService;
	
	@Autowired
	private TrainingCourseService trainingCourseService;
	
	/**
	 * 邀约客户时添加意向客户和邀约记录表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addinvite")
	public String addinvite(HttpServletRequest request){
		try {
			String intentionCustName = request.getParameter("intentionCustName");
			String companyname = request.getParameter("companyname");
			String phone = request.getParameter("phone");
			String intentionCustAddress = request.getParameter("intentionCustAddress"); 
			if(StringUtils.isEmpty(intentionCustName) || StringUtils.isEmpty(phone)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Integer userid = super.getUserId(request);
			IntentionCustPo intentionCustPo = new IntentionCustPo();
			intentionCustPo.setName(intentionCustName);
			intentionCustPo.setPhone(phone);
			intentionCustPo.setCompanyname(companyname);
			intentionCustPo.setAddress(intentionCustAddress);
			intentionCustPo.setCreateby(userid);
			intentionCustPo.setCreatedate(new Date());
			boolean check = intentionCustService.checkIntentionCustExist(phone, intentionCustName);
			if(check){
				intentionCustPo = intentionCustService.findCustIdByParams(phone, intentionCustName);
			}else{
				intentionCustService.addIntentionCust(intentionCustPo);
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
			InviteRecordPo inviteRecordPo = new InviteRecordPo();
			inviteRecordPo.setCourseid(courseid);
			inviteRecordPo.setIntentioncustid(intentionCustPo.getIntentioncustid());
			//获取意向客户的上次应约次数（如果是第一次应约就为0）
			Integer inviteidcount = inviteRecordService.getInviteidcount(intentionCustPo.getIntentioncustid());
			//本次的应约记录在上一次的记录基础上加1
			inviteRecordPo.setInviteidcount(inviteidcount+1);
			inviteRecordPo.setCreateby(super.getUserId(request));
			inviteRecordPo.setCreatedate(new Date());
			inviteRecordService.addInviteRecord(inviteRecordPo);
			
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 *邀约人的首页列表展示和搜索
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value="/inviteRecordIndex")
	public String inviteRecordIndex(HttpServletRequest request,PageTemp pageTemp){
		try {
			//意向客户姓名
			String customerName = request.getParameter("customerName");
			//意向客户公司名字
			String companyname = request.getParameter("companyname");
			//业务员昵称
			String petname = request.getParameter("petname");
			//课程名字
			String coursename = request.getParameter("coursename");
			//开始时间
			String startDate = request.getParameter("startDate");
			//结束时间
			String endDate = request.getParameter("endDate");
			boolean leader = super.getLeader(request);
			Integer userid = super.getUserId(request);
			PageInfo<InviteRecordVo> inviteRecordVoList = inviteRecordService.getInviteRecordList(customerName,companyname,petname,coursename,startDate,endDate,leader,userid,pageTemp);
			return ResUtils.okRes(inviteRecordVoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 邀约记录修改接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update")
	public String update(HttpServletRequest request){
		try {
			String inviterecordid = request.getParameter("inviterecordid");
			String intentioncustid = request.getParameter("intentioncustid");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String companyname = request.getParameter("companyname");
			String address = request.getParameter("address");
			String courseid = request.getParameter("courseid");
			if(StringUtils.isEmpty(intentioncustid) || StringUtils.isEmpty(inviterecordid) || StringUtils.isEmpty(courseid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			inviteRecordService.update(Integer.parseInt(inviterecordid),Integer.parseInt(courseid));
			IntentionCustPo intentionCustPo = new IntentionCustPo();
			intentionCustPo.setIntentioncustid(Integer.parseInt(intentioncustid));
			intentionCustPo.setName(name);
			intentionCustPo.setPhone(phone);
			intentionCustPo.setCompanyname(companyname);
			intentionCustPo.setAddress(address);
			intentionCustPo.setUpdateby(super.getUserId(request));
			intentionCustService.updateIntentionCust(intentionCustPo);
			
			TrainingCoursePo selectOne = trainingCourseService.selectOne(Integer.parseInt(courseid));
			selectOne.setCoursetime(DateUtils.format(request.getParameter("coursetime"), DateUtils.Y_M_D));
			if (!StringUtils.isEmpty(request.getParameter("courseaddress"))){
				selectOne.setCourseaddress(request.getParameter("courseaddress"));
				selectOne.setUpdateby(super.getUserId(request));
			}
			trainingCourseService.updateCourse(selectOne);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据邀约记录id，获取详情
	 * @param request
	 * @param inviterecordid
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request,String inviterecordid){
		try {
			if(StringUtils.isEmpty(inviterecordid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			InviteRecordVo inviteRecordVo = inviteRecordService.getDetail(Integer.parseInt(inviterecordid));
			return ResUtils.okRes(inviteRecordVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
