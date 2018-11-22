package com.huisou.service;

import com.github.pagehelper.PageInfo;

/** 
* @author qinkai 
* @date 2017年9月8日
*/

public interface TypeCountService {

	/*
	 * 根据saleId查询每个项目类型的总数
	 * @Param saleId
	 */
	public PageInfo itemTypeCountBySaleId(int saleid);

	/*
	 * 查询每个项目类型的总数
	 */
	public PageInfo itemTypeCount();

	/*
	 * 根据saleId查询每个关联合同类型的总数
	 * @Param saleId
	 */
	public PageInfo contactTypeCountBySaleId(int saleId);
	/*
	 * 查询每个关联合同类型的总数
	 */
	public PageInfo contactTypeCount();

}
