package com.huisou.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CustomerUserPo;
import com.huisou.vo.CustomerUserVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月16日
*/

public interface CustomerLoginRecordSerivce {

	/**
	 * 添加客户帐号登陆次数
	 * @param custUserPo
	 */
	public void addLoginNumber(CustomerUserPo custUserPo);

	/**
	 * 登陆总次数/登陆时间 排序
	 * @param custuserid
	 * @param orderVal
	 * @param orderFactor
	 * @return
	 */
	public PageInfo<CustomerUserVo> findCustList(String orderFactor, Integer custuserid,
			PageTemp pageTemp);
	/**
	 * 返回时间段内登陆次数
	 * @param custuserid
	 * @return
	 */
	public int findLastSevenCount(Integer custuserid);

	/**
	 * 返回最新更新的记录
	 * @param custuserid
	 * @return
	 */
	public int findAllList(Integer custuserid);

	public Date getLastDate(int custuserid);
}
