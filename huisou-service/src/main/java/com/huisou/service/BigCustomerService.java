package com.huisou.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.huisou.po.BigCustomePo;
import com.huisou.po.TradeCustomerPo;
import com.huisou.vo.PageTemp;

/** 
* Created by qinkai 
* 2017年7月24日
*/
@SuppressWarnings("all")
public interface BigCustomerService {

	// 添加 大客户
	public String insertBigCustomer(BigCustomePo bigCustomePo);
	
	// 修改大客户属性
	public String updateBigCustomer(BigCustomePo bigCustomePo);

	// 根据大客户ID查看详情removeBigStatus
	public BigCustomePo selectByBigCusId(int bigId);

	// 根据id禁用某个大客户
	public String forbidBigStatus(int bigid,int constatus);
	
	// 根据id移除某个大客户
	public String removeBigStatus(int bigid);
	
	// 多条件查询客户
	public PageInfo<BigCustomePo> queryByBigCusParas(Map<String, String>maps,PageTemp pageTemp);
	
	// 分配大客户到指定业务员
	public String allocatedBigCustomer(Map map);
	
	// 复制大客户属性到成单客户
//	public TradeCustomerPo copyPropertiesById(String bigid,String saleid,String salename);
	public TradeCustomerPo copyPropertiesById(String bigid);
	
	//批量插入大客户导入excel数据
	public void addBatchList(List<BigCustomePo> list);
}
