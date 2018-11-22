package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.IntentionItemPoMapper;
import com.huisou.po.IntentionItemPo;
import com.huisou.service.IntentionItemService;
import com.huisou.vo.ItemCourseVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/

@Service
public class IntentionItemServiceImpl implements IntentionItemService{

	@Autowired
	private IntentionItemPoMapper intentionItemPoMapper;
	
	@Override
	public void saveItem(String name, Integer type, Integer custid,Integer userid, Integer courseid) {
		// TODO Auto-generated method stub
		IntentionItemPo intentionItemPo = new IntentionItemPo();
		intentionItemPo.setName(name);
		intentionItemPo.setItemtype(type);
		intentionItemPo.setIntentioncustid(custid);
		intentionItemPo.setCreatedate(new Date());
		intentionItemPo.setCreateby(userid);
		intentionItemPo.setCourseid(courseid);
		intentionItemPoMapper.insertSelective(intentionItemPo);
	}

	@Override
	public List<ItemCourseVo> selectByParams(int custid, int courseid, int userid) {
		// TODO Auto-generated method stub
		return intentionItemPoMapper.selectByParams(custid,courseid,userid);
	}

	@Override
	public void updateItemByCourseid(int courseid, int intentioncustid, int userid) {
		// TODO Auto-generated method stub
		intentionItemPoMapper.updateItemCourseId(courseid,intentioncustid,userid);
	}

}
