package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.common.MyMapper;
import com.huisou.po.TradeCustomerPo;

public interface TradeCustomerPoMapper extends MyMapper<TradeCustomerPo> {
	
	// 返回所有成单客户
	public List<TradeCustomerPo> listAll(Map reqMap);
}