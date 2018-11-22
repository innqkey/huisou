package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.NoticeReadPoMapper;
import com.huisou.po.NoticeReadPo;
import com.huisou.service.NoticeReadService;

/** 
* @author qinkai 
* @date 2017年11月6日
*/
@Service
public class NoticeReadServiceImpl implements NoticeReadService{

	@Autowired
	private NoticeReadPoMapper noticeReadPoMapper;
	
	
	// 处理一条通知消息
	@Override
	public void saveNotice(NoticeReadPo noticeReadPo) {
		noticeReadPoMapper.insert(noticeReadPo);
	}

	// 查询通知消息是否存在
	@Override
	public boolean findOne(int noticeid, int readby) {
		NoticeReadPo noticeReadPo = new NoticeReadPo();
		noticeReadPo.setNoticeid(noticeid);
		noticeReadPo.setReadby(readby);
		List<NoticeReadPo> select = noticeReadPoMapper.select(noticeReadPo);
		if (select.size() > 0){
			return true;
		} else{
			return false;
		}
	}

}
