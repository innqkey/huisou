package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.IntentionCustPoMapper;
import com.huisou.po.IntentionCustPo;
import com.huisou.service.IntentionCustService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午3:57:07 
* 类说明 
*/
@Service
public class IntentionCustServiceImpl implements IntentionCustService{
	
	@Autowired
	private IntentionCustPoMapper intentionCustPoMapper;

	@Override
	public int addIntentionCust(IntentionCustPo intentionCustPo) {
		// TODO Auto-generated method stub
		intentionCustPoMapper.insertOne(intentionCustPo);
		return intentionCustPo.getIntentioncustid();
	}

	@Override
	public void updateIntentionCust(IntentionCustPo intentionCustPo) {
		// TODO Auto-generated method stub
		intentionCustPoMapper.update(intentionCustPo.getIntentioncustid(),intentionCustPo.getName(),intentionCustPo.getPhone(),intentionCustPo.getCompanyname(),intentionCustPo.getAddress(),intentionCustPo.getUpdateby());	
	}

	@Override
	public boolean checkIntentionCustExist(String phone, String name) {
		// TODO Auto-generated method stub
		IntentionCustPo intentionCustPo = intentionCustPoMapper.selectOneByPhoneAndName(phone,name);
		if(intentionCustPo==null){
			return false;
		}
		return true;
	}

	@Override
	public IntentionCustPo findCustIdByParams(String custPhone, String custName) {
		// TODO Auto-generated method stub
		IntentionCustPo intentionCustPo = new IntentionCustPo();
		intentionCustPo.setPhone(custPhone);
		intentionCustPo.setName(custName);
		IntentionCustPo selectOne = intentionCustPoMapper.selectOne(intentionCustPo);
		return selectOne;
	}

	@Override
	public List<IntentionCustPo> findAll(String name) {
		// TODO Auto-generated method stub
		List<IntentionCustPo> list = intentionCustPoMapper.findAll(name);
		return list;
	}
	
}
