package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.EmployeePoMapper;
import com.huisou.po.EmployeePo;
import com.huisou.service.EmployeeService;

import com.huisou.vo.PageTemp;


/**
 * Created by qinkai
 * 2017年7月19日
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeePoMapper employeePoMapper;

    /*
     * 判断员工非空字段是否为空
     * @param employee
     */
    public String EmployeeField(EmployeePo employeePo) {
        if (StringUtils.isNotBlank(employeePo.getEmpname()) &&
                StringUtils.isNotBlank(employeePo.getEmpphone())) {
            return ResUtils.sucCode;
        } else
            return ResUtils.exceCode;
    }

    /*
     * 添加新的员工
     * @Param EmployeePo
     */
    @Override
    public String insertEmployee(EmployeePo employeePo) {

        if ("404".equals(EmployeeField(employeePo))) {
            return ResUtils.errRes("102", "请求的类中有非空字段");
        }
        employeePoMapper.insertSelective(employeePo);
        //修改，新增返回主键 2017年09月08日09:16:37 by 薛园    
//      employeePoMapper.insertUseGeneratedKeys(employeePo); 
        return ResUtils.okRes();

    }

    /*
     * 查看员工属性
     * @Param 员工id
     */
    @Override
    public EmployeePo selectByEmpId(int empId) {
        EmployeePo employeePo = new EmployeePo();
        employeePo.setEmpid(empId);
        return employeePoMapper.selectOne(employeePo);
    }

    /*
     * 根据Id号修改员工属性
     * @param id
     */
    @Override
    public String updateEmployee(EmployeePo employeePo) {
        if ("404".equals(EmployeeField(employeePo))) {
            return ResUtils.errRes("102", "请求的类中有非空字段");
        }
        employeePoMapper.updateByPrimaryKeySelective(employeePo);
        return ResUtils.okRes();
    }

	/*
     * 根据手机号 时间查找
	 * @param empphone
	 * @param entrydate
	 */
	@Override
	public PageInfo<EmployeePo> queryByEmpParas(Map<String, String> paras,PageTemp pageTemp){
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());	
		List<EmployeePo> resultMap = employeePoMapper.selectByEmpParas(paras);
		return new PageInfo<EmployeePo>(resultMap);
	}

	/*
	 * 批量插入员工数据 
	 * @param List<EmployeePo>
	 */
	@Override
	public void addBatchList(List<EmployeePo> list) {
		int m = list.size()/50;
		for (int i = 0; i < m; i++) {
			List<EmployeePo> partList = list.subList(i*50,(i+1)*50);
			employeePoMapper.addBatchList(partList);
			//visitRecordPoMapper.addList(partList);
		}
		List<EmployeePo> endpartList = list.subList(m*50, list.size());
		if(endpartList.size()>0){
			employeePoMapper.addBatchList(endpartList);
		}
	}

	
	@Override
	public boolean phoneIsExist(String empphone) {
		EmployeePo employeePo = new EmployeePo();
		employeePo.setEmpphone(empphone);
		List<EmployeePo> empList = employeePoMapper.select(employeePo);
		if(empList.size()==0){
			return false;
		}
		return true;
	}

	@Override
	public List<EmployeePo> findAllEmpolyee() {
		return employeePoMapper.selectAll();
	}
}
