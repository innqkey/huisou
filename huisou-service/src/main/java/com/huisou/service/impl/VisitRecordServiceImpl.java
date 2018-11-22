package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.common.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.VisitRecordPoMapper;
import com.huisou.po.SalesManPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VisitRecrdVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月20日 上午10:21:11 
* 类说明 
*/
@Service
public class VisitRecordServiceImpl implements VisitRecordService{
	
	@Autowired
	VisitRecordPoMapper visitRecordPoMapper;
	
	@Override
	public PageInfo findItemsAndVisit(PageTemp pageTemp,SalesManPo salesManPo, boolean leader) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List visitRecordVoList = null;
		if(leader){
			visitRecordVoList=visitRecordPoMapper.findItemsAndVisitBySale(salesManPo.getSaleid());
		}else{
			visitRecordVoList=visitRecordPoMapper.findItemsAndVisit();
		}
		
		
		return new PageInfo(visitRecordVoList);
	}

	@Override
	public PageInfo findVisitByItemid(PageTemp pageTemp, String itemid) {
		// TODO Auto-generated method stub
		//PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List visitlist = visitRecordPoMapper.findVisitByItemid(Integer.parseInt(itemid));
		
		return new PageInfo(visitlist);
	}

	@Override
	public PageInfo findItemsAndVisitByParam(String salename, String salephone, String phone, 
			String contact, String itemname, String startTime, String endTime,
			PageTemp pageTemp, SalesManPo salesManPo, boolean leader,String replystatus,int userId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		Date starttime = null;
		Date endtime = null;
		if(!StringUtils.isEmpty(startTime)){
			starttime = DateUtils.format(startTime, DateUtils.Y_M_D);
		}if(!StringUtils.isEmpty(endTime)){
			endtime = DateUtils.format(endTime, DateUtils.Y_M_D);
		}
		List visit = null;
		//(leader!=0)
			if(!leader){
				visit = visitRecordPoMapper.findItemsAndVisitByParam(salename, salephone, phone, contact, itemname, startTime, endTime,replystatus);
			}else{
				if(null!=salesManPo.getSaleid()){
					visit = visitRecordPoMapper.findItemsAndVisitByParam2(salesManPo.getSalename(), salesManPo.getSalephone(), phone, contact, itemname, startTime, endTime,replystatus,null);
				}else{
					visit = visitRecordPoMapper.findItemsAndVisitByParam2(salesManPo.getSalename(), salesManPo.getSalephone(), phone, contact, itemname, startTime, endTime,replystatus,userId);
				}
			}
			
		return new PageInfo(visit);
	}

	@Override
	public void updateVisit(VisitRecordPo visitRecordPo) {
		// TODO Auto-generated method stub
		visitRecordPoMapper.updateVisit(visitRecordPo);
	}

	@Override
	public void add(VisitRecordPo visitRecordPo) {
		// TODO Auto-generated method stub
//		visitRecordPoMapper.insert(visitRecordPo);
		visitRecordPoMapper.insertSelective(visitRecordPo);
	}

	@Override
	public PageInfo findVisitByCustid(PageTemp pageTemp, String custid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List list = visitRecordPoMapper.findVisitByCustid(Integer.parseInt(custid));
		return new PageInfo<>(list);
	}

	@Override
	public void addVisitList(List<VisitRecordPo> list) {
		// TODO Auto-generated method stub
		int m = list.size()/50;
		for (int i = 0; i < m; i++) {
			List<VisitRecordPo> partList = list.subList(i*50,(i+1)*50);
			visitRecordPoMapper.insertList(partList);
			//visitRecordPoMapper.addList(partList);
		}
		List<VisitRecordPo> endpartList = list.subList(m*50, list.size());
		if(endpartList.size()>0){
			visitRecordPoMapper.insertList(endpartList);
		}
		//visitRecordPoMapper.addList(endpartList);
	}

	@Override
	public VisitRecrdVo findVisitByVisitid(String visitid) {
		// TODO Auto-generated method stub
		VisitRecrdVo visitRecordVo = visitRecordPoMapper.findVisitByVisitid(Integer.parseInt(visitid));
		return visitRecordVo;
	}

	
}
