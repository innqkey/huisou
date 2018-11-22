package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RemindSetPoMapper;
import com.huisou.po.RemindSetPo;
import com.huisou.service.RemindSetService;

@Service
public class RemindSetServiceImpl implements RemindSetService{

	@Autowired
	RemindSetPoMapper mapper;
	
	public List<RemindSetPo> list(RemindSetPo record){
		List<RemindSetPo> list = mapper.select(record);
		return list;
	}

	@Override
	public void saveOrUpdate(RemindSetPo po) {
		// TODO Auto-generated method stub
		if(null==po.getRemindid()||po.getRemindid()<=0){
			mapper.insertSelective(po);
		}else{
			mapper.updateByPrimaryKeySelective(po);
		}
	}

	@Override
	public RemindSetPo selectByid(String key) {
		// TODO Auto-generated method stub
		RemindSetPo po = mapper.selectByPrimaryKey(Integer.valueOf(key));
		return po;
	}
}
