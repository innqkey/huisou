package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.ItemsPo;

public interface ItemsPoMapper extends MyMapper<ItemsPo> {

	void updateSalesman(@Param("itemid")Integer itemid,@Param("endsaleid")Integer endsaleid);

	List<ItemsPo> selectItemByCustidOrderByBeginDate(Integer custid);

	//void insertItemsReturnId(@Param("itemsPo")ItemsPo itemsPo);
	
	Integer insertItemsReturnId(@Param("itemsPo")ItemsPo itemsPo);
	
	void changemarkStatus(@Param("itemids")List<Integer> itemids, @Param("markstatus")String markstatus);

	List<ItemsPo> selectAllItems();

	ItemsPo findItemById(Integer itemid);

	void changStatus(@Param("itemid")Integer itemid, @Param("status")Integer status);

	List<ItemsPo> selectItemsPoByCustid(@Param("custid")Integer custid);
}