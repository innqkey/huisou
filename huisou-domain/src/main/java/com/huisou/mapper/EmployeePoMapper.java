package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.common.MyMapper;
import com.huisou.po.EmployeePo;

public interface EmployeePoMapper extends MyMapper<EmployeePo> {
		
	// 根据手机号/员工名查询    
	public List<EmployeePo> selectByEmpParas(Map<String, String> maps);
		
	
	// 查找所有员工数据
//	public List<EmployeePo> findAll();

	// 批量插入员工数据
	public void addBatchList(List<EmployeePo> list);
	
	
}