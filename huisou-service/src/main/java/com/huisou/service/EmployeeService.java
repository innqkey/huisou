package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.EmployeePo;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月19日
*/
public interface EmployeeService {

	/*
	 * 添加新的员工
	 * @Param EmployeePo
	 */
	public String insertEmployee(EmployeePo employeePo); 
	
	/*
	 * 查看员工属性
	 * @Param 员工id
	 */
	public EmployeePo selectByEmpId(int empId);
	
	/*
	 * 根据Id号修改员工属性
	 * @param id 
	 */
	public String updateEmployee(EmployeePo employeePo);
	
	/*
	 * 根据手机号 时间查找
	 * @param empphone 
	 * @param entrydate 
	 */
	public PageInfo<EmployeePo> queryByEmpParas(Map<String, String>paras,PageTemp pageTemp);

	/*
	 * 批量插入员工数据
	 */
	public void addBatchList(List<EmployeePo> list);

	/*
	 * 添加员工时查看手机号是否存在
	 * @param empphone
	 */
	public boolean phoneIsExist(String empphone);

	//返回所有员工
	public List<EmployeePo> findAllEmpolyee();
	
}
