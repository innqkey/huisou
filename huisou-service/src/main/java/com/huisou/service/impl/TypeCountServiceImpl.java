package com.huisou.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TypeCountPoMapper;
import com.huisou.service.TypeCountService;


/** 
* @author qinkai 
* @date 2017年9月8日
*/

@Service
public class TypeCountServiceImpl implements TypeCountService{

	@Autowired
	TypeCountPoMapper typeCountPoMapper;
	
	@Override
	public PageInfo itemTypeCountBySaleId(int saleid) {
		List resCountMap = typeCountPoMapper.itemTypeCountBySaleId(Integer.valueOf(saleid));
		PageInfo salePageInfo=new PageInfo(resCountMap);
		return salePageInfo;
	}  

	@Override
	public PageInfo itemTypeCount() {
		List resCountMap = typeCountPoMapper.itemTypeCount();
		PageInfo salePageInfo=new PageInfo(resCountMap);
		return salePageInfo;
	}

	@Override
	public PageInfo contactTypeCountBySaleId(int saleid) {
		List resCountMap = typeCountPoMapper.contactTypeCountBySaleId(Integer.valueOf(saleid));
		PageInfo contactPageInfo=new PageInfo(resCountMap);
		return contactPageInfo;
	}

	@Override
	public PageInfo contactTypeCount() {
		List resCountMap = typeCountPoMapper.contactTypeCount();
		PageInfo contactPageInfo=new PageInfo(resCountMap);
		return contactPageInfo;
	}

}
