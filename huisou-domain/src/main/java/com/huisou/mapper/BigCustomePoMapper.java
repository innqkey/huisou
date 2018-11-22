package com.huisou.mapper;

import java.util.List;
import java.util.Map;
import com.common.MyMapper;
import com.huisou.po.BigCustomePo;

@SuppressWarnings("all")
public interface BigCustomePoMapper extends MyMapper<BigCustomePo> {
	
	//大客户属性修改
	public void updateBigCusById(BigCustomePo bigCustomePo);
	
	// 多条件查找
	public List<BigCustomePo> selectByBigCusParas(Map<String, String>maps);
	
	// 更新分配的客户状态
	public void updateAllocatedCusStatus(Map map);

	public void addBatchList(List<BigCustomePo> list);
}