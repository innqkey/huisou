package com.huisou.service;

import java.util.List;

import com.huisou.po.ClickUrlRecordPo;
import com.huisou.vo.ClickUrlRecordVo;

/** 
* @author qinkai 
* @date 2018年1月16日
*/

public interface ClickUrlRecordService {

	/**
	 * 客户帐号点击统计
	 * @return
	 */
	List<ClickUrlRecordVo> custClickCount(Integer custuserid);

	/**
	 * 保存一条点击记录
	 * @return 
	 */
	void insert(ClickUrlRecordPo clickUrlRecordPo);

}
