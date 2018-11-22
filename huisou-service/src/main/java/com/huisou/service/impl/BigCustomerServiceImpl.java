package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.BigCustomePoMapper;
import com.huisou.po.BigCustomePo;
import com.huisou.po.EmployeePo;
import com.huisou.po.TradeCustomerPo;
import com.huisou.service.BigCustomerService;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月24日
*/
@SuppressWarnings("all")
@Service
public class BigCustomerServiceImpl implements BigCustomerService{
	
	@Autowired
	BigCustomePoMapper bigCustomePoMapper;
	
	/*
	 * 判断大客户显示字段是否为空
	 * @param employee
	 */
	public String EmployeeField(BigCustomePo bigCustomePo){
		if (StringUtils.isNotBlank(bigCustomePo.getBigcontact()) &&
				StringUtils.isNotBlank(bigCustomePo.getBigphone()) &&
				StringUtils.isNotBlank(bigCustomePo.getBigcompanyname())) {	
			return ResUtils.sucCode;	
		}
		else
			return ResUtils.exceCode;
	}
	
	/*
	 * 添加大客户
	 * @param BigCustomerPo
	 */
	@Override
	public String insertBigCustomer(BigCustomePo bigCustomePo){
		if ("404".equals(EmployeeField(bigCustomePo))){
			return ResUtils.errRes("102", "添加的大客户类中有非空字段");
		}
		bigCustomePoMapper.insertSelective(bigCustomePo);
		return ResUtils.okRes();
	}
	
	/*
	 * 修改大客户
	 * @param BigCustomerPo
	 */
	@Override
	public String updateBigCustomer(BigCustomePo bigCustomePo){
		if ("404".equals(EmployeeField(bigCustomePo))){
			return ResUtils.errRes("102", "添加的大客户类中有非空字段");
		}
		bigCustomePoMapper.updateByPrimaryKeySelective(bigCustomePo);
		return ResUtils.okRes();
	}
	
	/*
	 * 查看详情
	 * @param bigid
	 */
	@Override
	public BigCustomePo selectByBigCusId(int bigId){
		BigCustomePo bigCustomePo = new BigCustomePo();
		bigCustomePo.setBigid(bigId);
		return bigCustomePoMapper.selectOne(bigCustomePo);
	}
	
	/*
	 * 根据ID号禁用某个大客户
	 * @param bigid
	 */
	@Override
	public String forbidBigStatus(int bigid,int constatus){
		BigCustomePo bigCustomePo = new BigCustomePo();
		bigCustomePo.setBigid(bigid);
		bigCustomePo.setBigcontstatus(constatus);
		bigCustomePoMapper.updateByPrimaryKeySelective(bigCustomePo);
		return ResUtils.okRes();
	}
	
	/*
	 * 根据ID号移除某个大客户
	 * @param
	 */
	@Override 
	public String removeBigStatus(int bigid){
		BigCustomePo bigCustomePo = new BigCustomePo();
		bigCustomePo.setBigid(bigid);
		bigCustomePo.setBigsalestatus(1);
		bigCustomePo.setSaleid(0);
		bigCustomePo.setSalename("");
		bigCustomePoMapper.updateByPrimaryKeySelective(bigCustomePo);
		return ResUtils.okRes();
	}
	
	/*
	 * 多条件查询
	 * @param bigsalestatus
	 * @param bigcontstatus
	 * @param bigcontact
	 * @param bigphone
	 */
	@Override
	public PageInfo<BigCustomePo> queryByBigCusParas(Map<String, String>maps,PageTemp pageTemp){
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<BigCustomePo> resultMap = bigCustomePoMapper.selectByBigCusParas(maps);
		return new PageInfo<BigCustomePo>(resultMap);
	}
	
	/*
	 * 批量指配客户给指定业务员
	 * @param bigSaleStatus
	 * @param saleid
	 * @param bigId
	 * @param salename
	 */
	@Override
	public String allocatedBigCustomer(Map map){
		bigCustomePoMapper.updateAllocatedCusStatus(map);
		return ResUtils.okRes();
	}
	
	/*
	 * 复制大客户属性到成单客户
	 * @param bigid
	 */
	@Override
//	public TradeCustomerPo copyPropertiesById(String bigid,String saleid,String salename){
	public TradeCustomerPo copyPropertiesById(String bigid){
		BigCustomePo bigCustomePo = new BigCustomePo();
		bigCustomePo.setBigid(Integer.parseInt(bigid));
		//bigCustomePo.setSaleid(Integer.parseInt(StringUtils.isNotBlank(saleid)?saleid:"0"));
		//bigCustomePo.setSalename(salename);
		//bigCustomePoMapper.updateByPrimaryKeySelective(bigCustomePo);
		
		TradeCustomerPo resultTradeCustomer = null;
		try {
			Object obj = bigCustomePoMapper.selectOne(bigCustomePo);
			if(null!=obj){
				resultTradeCustomer = new TradeCustomerPo();
				BeanUtils.copyProperties(resultTradeCustomer,obj);
			}
				
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bigCustomePoMapper.delete(bigCustomePo);
		return resultTradeCustomer;
	}

	@Override
	public void addBatchList(List<BigCustomePo> list) {
		// TODO Auto-generated method stub
		int m = list.size()/50;
		for (int i = 0; i < m; i++) {
			List<BigCustomePo> partList = list.subList(i*50,(i+1)*50);
			bigCustomePoMapper.addBatchList(partList);
			//visitRecordPoMapper.addList(partList);
		}
		List<BigCustomePo> endpartList = list.subList(m*50, list.size());
		if(endpartList.size()>0){
			bigCustomePoMapper.addBatchList(endpartList);
		}
	}
}
