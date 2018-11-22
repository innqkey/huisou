package com.huisou.service;

import java.util.List;

import com.huisou.po.CustomerUserPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.vo.ItemDetailVo;

public interface ItemService {

	void updateSalesman(SalesCustHistoryPo salesCustHistoryPo);

	ItemDetailVo findItemInfoByItemid(Integer itemid) throws Exception;

	void updateItemByItemid(ItemsPo record);


	
	ItemsPo findItemPoById(Integer itemid);


	void markStatus(List<Integer> itemids, String markstatus);


	List<ItemsPo> findAll();

	boolean findOne(int itemid, int saleid);

	void changItemStatus(Integer itemid, Integer status);

	List<ItemsPo> findItemsByCustid(Integer custid);

	



}
