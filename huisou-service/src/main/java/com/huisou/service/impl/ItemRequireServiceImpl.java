package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.ItemRequirePoMapper;
import com.huisou.po.ItemRequirePo;
import com.huisou.service.ItemRequireService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午8:53:44 
* 类说明 
*/
@Service
public class ItemRequireServiceImpl implements ItemRequireService{

	@Autowired
	ItemRequirePoMapper itemRequirePoMapper;
	
	@Override
	public Integer add(Integer itemid, String requirecontent, int createby) {
		// TODO Auto-generated method stub
		ItemRequirePo itemRequirePo = new ItemRequirePo();
		itemRequirePo.setItemid(itemid);
		itemRequirePo.setRequirecontent(requirecontent);
		itemRequirePo.setCreateby(createby);
		itemRequirePo.setCreatedate(new Date());
		itemRequirePoMapper.insert(itemRequirePo);
		ItemRequirePo itemRequirePo1 = itemRequirePoMapper.selectOne(itemRequirePo);
		return itemRequirePo1.getRequireid();
	}

	@Override
	public List findAllByItemid(Integer itemid) {
		// TODO Auto-generated method stub
		List list = itemRequirePoMapper.findAllByItemid(itemid);
		return list;
	}

	@Override
	public ItemRequirePo findByrequireid(Integer requireid) {
		// TODO Auto-generated method stub
		ItemRequirePo itemRequirePo = itemRequirePoMapper.findByrequireid(requireid);
		return itemRequirePo;
	}

}
