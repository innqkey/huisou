package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;

public interface SalesManPoMapper extends MyMapper<SalesManPo> {
	
	 List findAllMap();

	
	List findSale(@Param(value="salename")String salename,@Param(value="salephone")String salephone,@Param(value="startTime")Date startTime,@Param(value="endTime")Date endTime);

	void update(SalesManPo salesManPo);


	SalesManPo findSaleBySalephone(String salephone);


	SalesManPo findSaleById(Integer beforesaleid);


	int insertSelectKey(SalesManPo salesManPo);
}