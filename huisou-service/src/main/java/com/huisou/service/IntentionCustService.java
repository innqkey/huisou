package com.huisou.service;

import java.util.List;

import com.huisou.po.IntentionCustPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午3:55:47 
* 类说明 
*/
public interface IntentionCustService {
	
	int addIntentionCust(IntentionCustPo intentionCustPo); 
	void updateIntentionCust(IntentionCustPo intentionCustPo);
	
	/**
	 * 根据手机号和客户姓名判断意向客户是否存在，如果存在返回true，不存在返回false
	 * @param phone
	 * @param name
	 * @return
	 */
	boolean checkIntentionCustExist(String phone, String name);
	IntentionCustPo findCustIdByParams(String custPhone, String custName);
	List<IntentionCustPo> findAll(String name);
}
