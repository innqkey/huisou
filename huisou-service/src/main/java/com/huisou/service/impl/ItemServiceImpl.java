package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ConvertUtil;
import com.huisou.mapper.CustomerPoMapper;
import com.huisou.mapper.ItemsPoMapper;
import com.huisou.mapper.PicRecordPoMapper;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.PicRecordPo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.po.UniversalPo;
import com.huisou.service.ItemService;
import com.huisou.vo.ItemDetailVo;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemsPoMapper itemsPoMapper;
	
	@Autowired
	private CustomerPoMapper customerPoMapper;
	@Autowired
	private PicRecordPoMapper picRecordPoMapper;
	
	@Override
	public void updateSalesman(SalesCustHistoryPo salesCustHistoryPo) {
		itemsPoMapper.updateSalesman(salesCustHistoryPo.getItemid(),salesCustHistoryPo.getEndsaleid());
	}

	@Override
	public ItemDetailVo findItemInfoByItemid(Integer itemid) throws Exception {
		UniversalPo universalPo = customerPoMapper.findDetailedInfoByitemid(itemid);
		if(universalPo==null)
			return null;
		ItemDetailVo itemDetailVo = (ItemDetailVo) ConvertUtil.convertDtoAndVo(universalPo, ItemDetailVo.class);
		PicRecordPo picRecordPo = new PicRecordPo();
		picRecordPo.setItemid(itemid);
		List<PicRecordPo> pics = picRecordPoMapper.findPicByItemidAndNoDelete(itemid);
		itemDetailVo.setPics(pics);
		return itemDetailVo;
	}

	@Override
	public void updateItemByItemid(ItemsPo record) {
		// TODO Auto-generated method stub
		itemsPoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ItemsPo findItemPoById(Integer itemid) {
		// TODO Auto-generated method stub
//		return itemsPoMapper.selectByPrimaryKey(itemid);
		return itemsPoMapper.findItemById(itemid);
	}

	@Override
	public void markStatus(List<Integer> itemids, String markstatus) {
		itemsPoMapper.changemarkStatus(itemids,markstatus);
	}
	@Override
	public List<ItemsPo> findAll() {
//		return itemsPoMapper.selectAll();
		return itemsPoMapper.selectAllItems();
	}

	@Override
	public boolean findOne(int itemid, int saleid) {
		ItemsPo itemsPo = new ItemsPo();
		itemsPo.setItemid(itemid);
		itemsPo.setSaleid(saleid);
		List<ItemsPo> select = itemsPoMapper.select(itemsPo);
		if (select.size() > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void changItemStatus(Integer itemid, Integer status) {
		// TODO Auto-generated method stub
		itemsPoMapper.changStatus(itemid,status);
	}

	@Override
	public List<ItemsPo> findItemsByCustid(Integer custid) {
		// TODO Auto-generated method stub
		List<ItemsPo> list = itemsPoMapper.selectItemsPoByCustid(custid);
		return list;
	}

}
