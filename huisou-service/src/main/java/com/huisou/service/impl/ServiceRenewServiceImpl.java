package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.ServiceRenewPoMapper;
import com.huisou.po.ServiceRenewPo;
import com.huisou.po.ServiceRenewPo;
import com.huisou.service.ServiceRenewService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月13日 下午5:35:59 
* 类说明 
*/
@SuppressWarnings("all")
@Service
public class ServiceRenewServiceImpl implements ServiceRenewService {

	@Autowired
	private ServiceRenewPoMapper serviceRenewPoMapper;
	
	@Override
	public void saveServiceItem(ServiceRenewPo po) {
		// TODO Auto-generated method stub
		ServiceRenewPo searchPo = new ServiceRenewPo();
		searchPo.setItemid(po.getItemid());
		List<ServiceRenewPo> list = serviceRenewPoMapper.select(searchPo);
		if(null!=list&&list.size()>0){
			for(ServiceRenewPo vo : list){
				vo.setCreateby(po.getCreateby());
				vo.setUserid(po.getUserid());
				vo.setSerremark(po.getSerremark());
				serviceRenewPoMapper.updateByPrimaryKeySelective(vo);
			}
		}else{
			serviceRenewPoMapper.insertSelective(po);
		}
	}

}
