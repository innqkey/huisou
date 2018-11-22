package com.huisou.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.NoticePoMapper;
import com.huisou.po.NoticePo;
import com.huisou.service.NoticeService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月6日 下午2:43:16 
* 类说明 
*/
@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticePoMapper noticePoMapper;
	
	/*
	 * 查询消息
	 * @param createby
	 * @param noticeacceptype
	 */
	@Override
	public List<NoticePo> findAllNotices(Integer noticeacceptype) {
		return noticePoMapper.queryAllNotices(noticeacceptype);
	}
	
	/*
	 * 查询消息
	 * @param list
	 */
	@Override
	public List<NoticePo> findAllByList(List<Integer> ids) {
		return noticePoMapper.selectAllByList(ids);
	}
	
	@Override
	public void insert(NoticePo noticePo) {
		// TODO Auto-generated method stub
		noticePoMapper.insert(noticePo);
	}

}
