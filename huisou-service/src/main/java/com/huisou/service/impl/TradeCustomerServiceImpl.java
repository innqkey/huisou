package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TradeCustomerPoMapper;
import com.huisou.po.TradeCustomerPo;
import com.huisou.service.TradeCustomerService;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月26日
*/
@Service
public class TradeCustomerServiceImpl implements TradeCustomerService{
	
	@Autowired
	TradeCustomerPoMapper tradeCustomerPoMapper;
	
	/*
	 * 判断成单客户显示字段是否为空
	 * @param employee
	 */
	public String EmployeeField(TradeCustomerPo tradeCustomePo){
		if (StringUtils.isNotBlank(tradeCustomePo.getBigcontact()) &&
				StringUtils.isNotBlank(tradeCustomePo.getBigphone()) &&
				StringUtils.isNotBlank(tradeCustomePo.getBigcompanyname())){	
			return ResUtils.sucCode;	
		}
		else
			return ResUtils.exceCode;
	}
	
	/*
	 * 保存成单的大客户
	 * @param tradecustomerpo
	 */
	@Override
	public String insertTradeCustomer(TradeCustomerPo po){
		if ("404".equals(EmployeeField(po))){
			return ResUtils.errRes("102", "添加的成单客户中有非空字段");
		}
		tradeCustomerPoMapper.insertSelective(po);
		return ResUtils.okRes();
	}
	
	/*
	 * 返回所有成单客户
	 * @param pagetemp
	 */
	@Override
	public PageInfo<TradeCustomerPo> findAll(PageTemp pageTemp,Map reqMap){
		
		List<TradeCustomerPo> resultMap = tradeCustomerPoMapper.listAll(reqMap);
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		return new PageInfo<TradeCustomerPo>(resultMap);
	}
}
