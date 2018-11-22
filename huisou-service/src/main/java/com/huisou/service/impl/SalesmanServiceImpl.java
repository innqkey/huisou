package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.SaleRemindSetPoMapper;
import com.huisou.mapper.SalesManPoMapper;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;
import com.huisou.service.SalesmanService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SalesmanVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月17日 下午5:58:53 
* 类说明 
*/
@Service
public class SalesmanServiceImpl implements SalesmanService{

	@Autowired
	SalesManPoMapper salesManPoMapper; 
	
	/**
	 * 查询所有业务员并分页
	 */
	@Override
	public PageInfo findAll(PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		//list 集合中存储的是map类型
		List saleListMap = salesManPoMapper.findAllMap();  
		Page page = (Page) saleListMap;
		PageInfo salePageInfo=new PageInfo(saleListMap);
		return salePageInfo;
	}

//	@Override
//	public PageInfo findSaleBysalename(String salename) {
//		// TODO Auto-generated method stub
//		PageHelper.startPage(1,10);
//		List saleListMap=salesManPoMapper.findSaleBysalename(salename);
//		PageInfo salePageInfo=new PageInfo(saleListMap);
//		
//		Page page = (Page) saleListMap;
//		
//		return salePageInfo;
//	}
//
//	@Override
//	public PageInfo findSaleBysalephone(String salephone) {
//		// TODO Auto-generated method stub
//		PageHelper.startPage(1,10);
//		List saleListMap=salesManPoMapper.findSaleBysalephone(salephone);
//		PageInfo salePageInfo=new PageInfo(saleListMap);
//		
//		Page page = (Page) saleListMap;
//		
//		return salePageInfo;
//	}

	@Override
	public PageInfo findSale(String salename, String salephone,Date startTime,Date endTime,PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(),pageTemp.getPageSize());
		List saleListMap=salesManPoMapper.findSale(salename,salephone,startTime,endTime);
		PageInfo salePageInfo=new PageInfo(saleListMap);
		
		Page page = (Page) saleListMap;
		
		return salePageInfo;
	}

	@Override
	public void update(SalesManPo salesManPo) {
		// TODO Auto-generated method stub
		salesManPoMapper.update(salesManPo);
	}

	@Override
	public SalesManPo findSaleBySalephone(String Salephone) {
		// TODO Auto-generated method stub
		return salesManPoMapper.findSaleBySalephone(Salephone);
	}

	@Override
	public void updateList(ArrayList<Integer> saleids, Integer status ,Integer userid) {
		// TODO Auto-generated method stub
		SalesManPo salesManPo = new SalesManPo();
		for (Integer saleid : saleids) {
			salesManPo.setSaleid(saleid);
			salesManPo.setSalestatus(status);
			salesManPo.setCreateby(userid);
			salesManPoMapper.update(salesManPo);
		}
	}

	@Override
	public Integer insertSalesMan(SalesManPo salesManPo) {
		// TODO Auto-generated method stub
		  salesManPoMapper.insertSelectKey(salesManPo);
		 return salesManPo.getSaleid();
	}

	@Override
	public boolean iphoneIsExist(String salephone) {
		// TODO Auto-generated method stub
		SalesManPo salesManPo = new SalesManPo();
		salesManPo.setSalephone(salephone);
		 List<SalesManPo> saleList = salesManPoMapper.select(salesManPo);
		 if(saleList.size()==0){
			 return false;
		 }
		return true;
	}

	@Override
	public SalesManPo findSaleById(Integer beforesaleid) {
		SalesManPo one = salesManPoMapper.findSaleById(beforesaleid);
		return one;
	}

	@Override
	public List<SalesManPo> findListByparas(SalesManPo salesManPo) {
		// TODO Auto-generated method stub
		List<SalesManPo> saleList = salesManPoMapper.select(salesManPo);
		return saleList;
	}
	public boolean checkUpdatePhone(SalesManPo salesManPo) {
		// TODO Auto-generated method stub
		boolean flag = this.iphoneIsExist(salesManPo.getSalephone());
		if(flag){
			SalesManPo saPo = this.findSaleBySalephone(salesManPo.getSalephone());
			if(salesManPo.getSaleid()==saPo.getSaleid()){
				return false;
			}
			return true;
		}
		
		return false;
	}

	
}
