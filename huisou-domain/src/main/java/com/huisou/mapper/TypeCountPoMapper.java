package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.ItemsPo;

/** 
* @author qinkai 
* @date 2017年9月11日
*/

public interface TypeCountPoMapper extends MyMapper<ItemsPo> {

	//根据saleId查询每个项目类型的总数
	List itemTypeCountBySaleId(@Param("saleid")Integer saleid);

	//查询每个项目类型的总数
	List itemTypeCount();

	//根据saleId查询每个关联合同类型的总数
	List contactTypeCountBySaleId(@Param("saleid")Integer saleid);

	//查询每个关联合同类型的总数
	List contactTypeCount();
	

}
