package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.BigVisitRecordPoMapper;
import com.huisou.po.BigVisitRecordPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.BigVisitRecordService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月25日 下午4:31:55 
* 类说明 
*/
@Service
public class BigVisitRecordServiceImpl implements BigVisitRecordService {

	@Autowired
	BigVisitRecordPoMapper bigVisitRecordPoMapper;
	
	@Override
	public List<BigVisitRecordPo> findBigVisit(Integer bigid) {
		// TODO Auto-generated method stub
		List<BigVisitRecordPo> list = bigVisitRecordPoMapper.findBigVisit(bigid);
		return list;
	}

	@Override
	public void update(BigVisitRecordPo bigVisitRecordPo) {
		// TODO Auto-generated method stub
		bigVisitRecordPoMapper.updatebigVisit(bigVisitRecordPo);
		//bigVisitRecordPoMapper.updateByPrimaryKey(bigVisitRecordPo);
	}

	@Override
	public void add(BigVisitRecordPo bigVisitRecordPo) {
		// TODO Auto-generated method stub
		bigVisitRecordPoMapper.insertBigVisit(bigVisitRecordPo);
	}

	@Override
	public void addVisitList(List<BigVisitRecordPo> list) {
		// TODO Auto-generated method stub
		int m = list.size()/50;
		for (int i = 0; i < m; i++) {
			List<BigVisitRecordPo> partList = list.subList(i*50,(i+1)*50);
			bigVisitRecordPoMapper.insertList(partList);
			
		}
		List<BigVisitRecordPo> endpartList = list.subList(m*50, list.size());
		if(endpartList.size()>0){
			bigVisitRecordPoMapper.insertList(endpartList);
		}
		
	}
	

}
