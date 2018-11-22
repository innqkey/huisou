package com.huisou.controller;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.service.SalesmanService;
import com.huisou.service.UserService;
import com.huisou.vo.CustomerVO;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SalesmanVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月17日 上午11:48:55 
* 类说明 
*/
@RestController
@RequestMapping(value="/salesman")
public class SalesmanController extends BaseController{
	@Autowired
	SalesmanService salesmanService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 查询所有的业务员
	 */
	@RequestMapping(value="/index")
	public String findAll(HttpServletRequest request, PageTemp pageTemp){
		try {
			PageInfo salePageInfo = salesmanService.findAll(pageTemp);
			PageInfo<SalesmanVo> salePageInfoVos = (PageInfo<SalesmanVo>) ConvertUtil.convertDtoAndVo(salePageInfo, PageInfo.class);
			return ResUtils.okRes(salePageInfoVos);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 多条件查询业务员
	 */
	@RequestMapping(value="/findSale")
	public String findSale(HttpServletRequest request,PageTemp pageTemp){
		try {
			String  searchType = request.getParameter("searchType");
			String  searchTypeValue = request.getParameter("searchTypeValue");
			String  startTime = request.getParameter("startTime");
			String  endTime = request.getParameter("endTime");
			String  salename = null;
			String  salephone = null;
				if(ContextConstant.SEARCH_SALENAME.equals(searchType)){
					salename = searchTypeValue;
					}else if(ContextConstant.SEARCH_SALEPHONE.equals(searchType)){
						salephone = searchTypeValue;
					}
			Date starttime = null;
			Date endtime = null;
			
			if (!StringUtils.isEmpty(startTime)) {
				starttime = DateUtils.format(startTime, DateUtils.Y_M_D);
			}if(!StringUtils.isEmpty(endTime)){
				endtime = DateUtils.format(endTime, DateUtils.Y_M_D) ;	
			}
			
			PageInfo salePageInfo = salesmanService.findSale(salename,salephone,starttime,endtime,pageTemp);
		List list = salePageInfo.getList();
		if(list.size()==0){
			return null;
		}
		List<SalesmanVo> salesmanVoList = ConvertUtil.convertDtoAndVo(list,
				SalesmanVo.class);
			PageInfo<SalesmanVo> salePageInfoVos=(PageInfo<SalesmanVo>) ConvertUtil.convertDtoAndVo(salePageInfo, PageInfo.class);
			salePageInfoVos.setList(salesmanVoList);
			return ResUtils.okRes(salePageInfoVos);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加业务员
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addSalesMan(HttpServletRequest request){
		try {
			SalesManPo salesManPo = new SalesManPo();
			String  salename = request.getParameter("salename");
			String  salephone = request.getParameter("salephone");
			String  saleweixin = request.getParameter("saleweixin");
			String 	saleemail = request.getParameter("saleemail");
			String  leader = request.getParameter("leader");
			String  salestatus = request.getParameter("salestatus");
			salesManPo.setSalename(salename);
			salesManPo.setSalephone(salephone);
			salesManPo.setSaleweixin(saleweixin);
			salesManPo.setSaleemail(saleemail);
			salesManPo.setSalestatus(Integer.parseInt(salestatus));
			boolean iphoneIsExist=salesmanService.iphoneIsExist(salesManPo.getSalephone());
			if(iphoneIsExist){
				return ResUtils.errRes(ContextConstant.EXIST_PHONE, "手机号已存在");
			}
			Integer userid = super.getUserId(request);
			Date createdate = new Date();
			salesManPo.setCreateby(userid);
			salesManPo.setCreatedate(createdate);
			Integer saleid = salesmanService.insertSalesMan(salesManPo);
			//给业务员添加角色权限
			UserPo userPo = new UserPo();
			userPo.setUsername(salesManPo.getSalephone());
			userPo.setPetname(salesManPo.getSalename());
			userPo.setKeyid(saleid);
			userPo.setType(3);
			userPo.setLeader(Integer.parseInt(leader));
			userService.insertUser(userPo, null);
			
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	
	
	/**
	 * 修改业务员状态,业务员状态：1，正常；2，禁用
	 */
	@RequestMapping(value="/updateStatus")
	public String updateSaleStatus(HttpServletRequest request,String saleid, String salestatus){
		
		try {
			Integer id = Integer.parseInt(saleid);
			Integer status = Integer.parseInt(salestatus);
			if(id==null || status==null ){
				return ResUtils.execRes();
			}
			Integer userid = super.getUserId(request);
			SalesManPo salesManPo=new SalesManPo();
			salesManPo.setSaleid(id);
			salesManPo.setSalestatus(status);
			salesManPo.setCreateby(userid);
			salesmanService.update(salesManPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改业务员
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateSale(HttpServletRequest request,SalesManPo salesManPo){
		try {
			boolean iphoneIsExist=salesmanService.checkUpdatePhone(salesManPo);
			if(iphoneIsExist){
				return ResUtils.errRes(ContextConstant.EXIST_PHONE, "修改的手机号已存在");
			}
			Integer userid = super.getUserId(request);
			salesManPo.setUpdateby(userid);
			salesManPo.setUpdatedate(new Date());
			salesmanService.update(salesManPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量修改业务员状态 
	 */
	@RequestMapping(value="/updateStatusList")
	public String updateStatusList(HttpServletRequest request,@RequestParam("saleids")ArrayList<Integer> saleids,String status){
		try {
			if(saleids==null || saleids.isEmpty()){
				return ResUtils.okRes();
			}
			Integer userid = super.getUserId(request);
			salesmanService.updateList(saleids,Integer.parseInt(status),userid);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 根据业务员手机号查询业务员数据
	 */
	@RequestMapping(value="/findSaleBySalephone")
	public String findSaleBySalephone(HttpServletRequest request,String salephone){
		try {
			if(salephone==null){
				return ResUtils.errRes(ContextConstant.PARAM_NULL, "参数salephone不能为空");
			}
			SalesManPo salesManPo = salesmanService.findSaleBySalephone(salephone);
			return ResUtils.okRes(salesManPo);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	/**
	 * 根据业务员id查询业务员
	 */
	@RequestMapping(value="/findSaleBySaleid")
	public String findSaleBySaleid(HttpServletRequest request,String saleid){
		try {
			if(StringUtils.isEmpty(saleid)){
				return ResUtils.errRes("102", "业务员id不为空");
			}else{
				SalesManPo salesManPo = salesmanService.findSaleById(Integer.parseInt(saleid));
				return ResUtils.okRes(salesManPo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * input下拉选择输入框
	 */
	@RequestMapping(value="/findSaleAll")
	public String findSaleAll(HttpServletRequest request){
		try {
			SalesManPo salesManPo = new SalesManPo();
			salesManPo.setSalestatus(1);
			List<SalesManPo> list = salesmanService.findListByparas(salesManPo);
			List<Map<String,Object>> valMap = new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				for(SalesManPo po:list){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("saleid", po.getSaleid());
					map.put("value", po.getSalename()+"（"+po.getSalephone()+"）");
					map.put("salename", po.getSalename());
					valMap.add(map);
				}
			}
			return ResUtils.okRes(valMap);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
}
