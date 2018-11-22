package com.huisou.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.RenewMarkPo;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserPo;
import com.huisou.service.CustomerService;
import com.huisou.service.RenewMarkService;
import com.huisou.vo.CustomerInfoVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RenewMarkVo;

@RequestMapping(value = "/renewMark")
@RestController
public class RenewMarkController extends BaseController {

	private  static  final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RenewMarkService markService;

	//待续费客户标记
	@RequestMapping(value = "/renewMarkSign")
	public String renewTraceSign(HttpServletRequest request,@RequestParam("itemIds[]") List<Integer> itemids) throws Exception {
		if(null==itemids&&itemids.size()<1){
			return ResUtils.errRes("102", "请求参数错误");
		}
		for(Integer itemid:itemids){
			UniversalPo mixPo = customerService.findDetailedInfo(itemid);

			CustomerInfoVo customerInfoVo = (CustomerInfoVo) ConvertUtil
					.convertDtoAndVo(mixPo, CustomerInfoVo.class);
			
			RenewMarkPo markPo = new RenewMarkPo();
			markPo.setItemid(itemid);
			markPo.setItemname(customerInfoVo.getItemname());
			markPo.setItemtype(customerInfoVo.getItemtype());
			markPo.setItembegindate(customerInfoVo.getItembegindate());
			markPo.setItemenddate(customerInfoVo.getItemenddate());
			markPo.setCustid(customerInfoVo.getCustid());
			markPo.setContact(customerInfoVo.getContact());
			markPo.setCompanyname(customerInfoVo.getCompanyname());
			markPo.setPhone(customerInfoVo.getPhone());
			markPo.setSaleid(customerInfoVo.getSaleid());
			markPo.setSalename(customerInfoVo.getSalename());
			markPo.setCreateby(super.getUserId(request));
			markPo.setCreatedate(new Date());
			markService.save(markPo);
			
		}
		
		return ResUtils.okRes();
	}

	//待续费客户列表查询
	@RequestMapping(value = "/listMark")
	public String listMark(HttpServletRequest request, PageTemp pageTemp) {
		Map reMap = new HashMap();
		reMap.put("searchValue", request.getParameter("searchValue"));
		reMap.put("beginDate", request.getParameter("beginDate"));
		reMap.put("endDate", request.getParameter("endDate"));
		reMap.put("itemType", request.getParameter("itemType"));
		PageInfo<RenewMarkVo> markVoList = markService.selectMarkList(pageTemp, reMap);
		return ResUtils.okRes(markVoList);
	}
	
	//待续费客户列表查询
		@RequestMapping(value = "/listMarkSuc")
		public String listMarkSuc(HttpServletRequest request, PageTemp pageTemp) {
			Map reMap = new HashMap();
			reMap.put("searchValue", request.getParameter("searchValue"));
			reMap.put("beginDate", request.getParameter("beginDate"));
			reMap.put("endDate", request.getParameter("endDate"));
			reMap.put("itemType", request.getParameter("itemType"));
			PageInfo<RenewMarkVo> markVoList = markService.selectRenewSucList(pageTemp, reMap);
			return ResUtils.okRes(markVoList);
		}
}
