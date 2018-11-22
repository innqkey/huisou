package com.huisou.service;

import com.huisou.po.NoticeReadPo;

/** 
* @author qinkai 
* @date 2017年11月6日
*/

public interface NoticeReadService {
	
	void saveNotice(NoticeReadPo noticeReadPo);

	boolean findOne(int noticeid, int readby);

}
