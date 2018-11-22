package com.huisou.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TradeCustomerPo;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月26日
*/
public interface TradeCustomerService {

	// 保存成单的大客户
	public String insertTradeCustomer(TradeCustomerPo po);
	
	// 返回所有成单客户
	public PageInfo<TradeCustomerPo> findAll(PageTemp pageTemp,Map reqMap);
}
