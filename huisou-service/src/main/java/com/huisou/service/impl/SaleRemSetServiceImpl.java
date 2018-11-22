package com.huisou.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RemindSetPoMapper;
import com.huisou.mapper.SaleRemindSetPoMapper;
import com.huisou.po.RemindSetPo;
import com.huisou.po.SaleRemindSetPo;
import com.huisou.service.SaleRemSetService;

@Service
public class SaleRemSetServiceImpl implements SaleRemSetService{

	@Autowired
	SaleRemindSetPoMapper mapper;
	
	@Autowired
	RemindSetPoMapper remindMapper;
	
	public List<SaleRemindSetPo> list(SaleRemindSetPo record){
		List<SaleRemindSetPo> list = mapper.select(record);
		return list;
	}

	@Override
	public void saveOrUpdate(SaleRemindSetPo po) {
		// TODO Auto-generated method stub
		if(null!=po.getSaleremindid()&&po.getSaleremindid()>0){
			mapper.updateByPrimaryKeySelective(po);
		}else{
			mapper.insertSelective(po);
		}
	}

	@Override
	public Integer getTimeSpan(Integer saleid, Integer salebusicode) {
		// TODO Auto-generated method stub
		//saleid为空，则表示非业务员，查询所有设置表
		int time = 0;
		RemindSetPo remPo = new RemindSetPo();
		remPo.setBusicode(salebusicode);
		List<RemindSetPo> poList = remindMapper.select(remPo);
		if(null!=poList&&poList.size()>0){
			RemindSetPo resPo = poList.get(0);
			time = null!=resPo.getRemindtimespan()?resPo.getRemindtimespan():0;
		}
		if(null!=saleid){
			SaleRemindSetPo po = new SaleRemindSetPo();
			po.setSaleid(saleid);
			po.setSalebusicode(salebusicode);
			List<SaleRemindSetPo> list = mapper.select(po);
			
			if(null!=list&&list.size()>0){
				SaleRemindSetPo salePo = list.get(0);
				time = null!=salePo.getSaleremindtimespan()?salePo.getSaleremindtimespan():0;
			}
		}
		
		return time;
//    	try {
//    		if(null!=list&&list.size()>0){
//    			salePo = new SaleRemindSetPo();
//    			BeanUtils.copyProperties(salePo, list.get(0));
//    		}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
	}
}
