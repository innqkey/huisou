package com.huisou.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.po.TradeCustomerPo;
import com.huisou.service.TradeCustomerService;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月26日
*/
@RestController
@RequestMapping(value = "/tradecustomer")
public class TradeCustomerController extends BaseController{

	@Autowired
	TradeCustomerService tradeCustomer;

	/*
	 * 返回成单客户列表
	 */
	@RequestMapping(value = "/list" )
	public String list(HttpServletRequest request, PageTemp pageTemp){
		
		String bigcontact = StringUtils.stripToEmpty(request.getParameter("bigContact"));
		String bigphone = StringUtils.stripToEmpty(request.getParameter("bigPhone"));
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("bigcontact", bigcontact);
		maps.put("bigphone", bigphone);
		//如果是业务员只能查看自己的分配客户
		maps.put("saleid", super.getSaleId(request));
		
		PageInfo<TradeCustomerPo> poList = tradeCustomer.findAll(pageTemp,maps);
	    return ResUtils.okRes(poList);
	}
}
