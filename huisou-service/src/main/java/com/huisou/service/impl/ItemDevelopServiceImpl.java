package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.ItemDevelopPoMapper;
import com.huisou.po.AccidentRecordPo;
import com.huisou.po.ItemDevelopPo;
import com.huisou.service.ItemDevelopService;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月20日
*/
@Service
public class ItemDevelopServiceImpl implements ItemDevelopService{

	@Autowired
	ItemDevelopPoMapper itemDevelopPoMapper;
	
	@Override
	public void saveItemDev(ItemDevelopPo itemDevelopPo) {
		// TODO Auto-generated method stub
		itemDevelopPoMapper.insertSelective(itemDevelopPo);
	}

	@Override
	public ItemDevelopPo selectOnePo(int devid) {
		// TODO Auto-generated method stub
		ItemDevelopPo itemDevelopPo = new ItemDevelopPo();
		itemDevelopPo.setDevid(devid);
		return itemDevelopPoMapper.selectOne(itemDevelopPo);
	}

	@Override
	public void updateDevelop(ItemDevelopPo selectedPo) {
		// TODO Auto-generated method stub
		itemDevelopPoMapper.updateByPrimaryKeySelective(selectedPo);
	}

	@Override
	public PageInfo<ItemDevelopPo> queryByMultiParas(Map<String, String> map, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ItemDevelopPo> resultMap = itemDevelopPoMapper.queryByMultiParas(map);
		return new PageInfo<ItemDevelopPo>(resultMap);
	}

}
