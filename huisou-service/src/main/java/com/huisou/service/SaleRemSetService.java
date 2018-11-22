package com.huisou.service;

import java.util.List;

import com.huisou.po.SaleRemindSetPo;


public interface SaleRemSetService {
	public List<SaleRemindSetPo> list(SaleRemindSetPo record);

	public void saveOrUpdate(SaleRemindSetPo po);
	
	public Integer getTimeSpan(Integer saleid,Integer salebusicode);
	
}
