package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.huisou.constant.DictConConstant;
import com.huisou.po.BigVisitRecordPo;
import com.huisou.service.BigVisitRecordService;
import com.huisou.service.SalesmanService;
import com.huisou.service.VisitRecordService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月25日 下午4:18:17 
* 类说明 
*/
@RestController
@RequestMapping(value="/bigVisitRecord")
public class BigVisitRecordController extends BaseController{

	@Autowired
	BigVisitRecordService bigVisitRecordSrevice;
	
	@Autowired
	SalesmanService salesmanService;
	
	/**
	 * 添加一条回访记录
	 * @param request
	 * @param bigVisitRecordPo
	 * @return
	 */
	@RequestMapping(value="/addBigVisit", method = RequestMethod.POST)
	public String addBigVisit(HttpServletRequest request){
		try {
			BigVisitRecordPo bigVisitRecordPo = new BigVisitRecordPo();
			String bigid = request.getParameter("bigid");
			String saleid = request.getParameter("saleid");
			String salename = request.getParameter("salename");
			String visitway = request.getParameter("visitway");
			String visitremarks = request.getParameter("visitremarks");
			String createdate = request.getParameter("createdate");
			if(StringUtils.isEmpty(createdate)){
				bigVisitRecordPo.setCreatedate(new Date());
			}
			bigVisitRecordPo.setCreatedate(DateUtils.format(createdate, DateUtils.Y_M_D));
			bigVisitRecordPo.setBigid(Integer.parseInt(bigid));
			bigVisitRecordPo.setSaleid(Integer.parseInt(saleid));
			bigVisitRecordPo.setSalename(salename);
			bigVisitRecordPo.setVisitremarks(visitremarks);
			bigVisitRecordPo.setVisitway(Integer.parseInt(visitway));
			bigVisitRecordPo.setVisitwayname(DictConConstant.getDicName("VisitWay", bigVisitRecordPo.getVisitway()));
			bigVisitRecordSrevice.add(bigVisitRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据大客户id查询大客户的拜访记录
	 * @param request
	 * @param bigid
	 * @return
	 */
	@RequestMapping(value="/findBigVisit")
	public String findBigVisit(HttpServletRequest request, String bigid){
		try {
			if(bigid==null){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<BigVisitRecordPo> bigVisitList = bigVisitRecordSrevice.findBigVisit(Integer.parseInt(bigid));
			return ResUtils.okRes(bigVisitList);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改单条拜访记录
	 * @param request
	 * @param bigVisitRecordPo
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, BigVisitRecordPo bigVisitRecordPo){
		try {
			if(bigVisitRecordPo.getVisitid()==null){
				ResUtils.execRes();
			}
			bigVisitRecordPo.setUpdateby(super.getUserId(request));
			bigVisitRecordSrevice.update(bigVisitRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
		
	}
	/**
	 * 一键添加拜访记录
	 * @param request
	 * @param bigids
	 * @return
	 */
	@RequestMapping(value="/addBigVisitList")
	public String addBigVisitList(HttpServletRequest request, @RequestParam("bigids[]")List<Integer> bigids){
		try {
			if(bigids==null || bigids.isEmpty()){
				return ResUtils.errRes("102","请求参数错误");
			}
			List<BigVisitRecordPo> list = new ArrayList<>();
			String visitremarks = "";
			Integer createby = super.getUserId(request);
			Integer saleid = super.getSaleId(request);
			String salename = super.getSaleName(request);
			Date date = new Date();
			for (Integer bigid : bigids) {
				BigVisitRecordPo bigVisitRecordPo = new BigVisitRecordPo();
				bigVisitRecordPo.setBigid(bigid);
				bigVisitRecordPo.setSalename(salename);
				bigVisitRecordPo.setCreateby(createby);
				bigVisitRecordPo.setCreatedate(date);
				bigVisitRecordPo.setSaleid(saleid);
				bigVisitRecordPo.setVisitremarks(visitremarks);
				//回访类型，1为正常，2为禁用
				bigVisitRecordPo.setVisitway(1);
				bigVisitRecordPo.setVisitwayname(DictConConstant.getDicName("VisitWay", bigVisitRecordPo.getVisitway()));
				list.add(bigVisitRecordPo);
			}
			bigVisitRecordSrevice.addVisitList(list);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
}
