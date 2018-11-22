package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import tk.mybatis.mapper.entity.Example;

import com.common.ConvertUtil;
import com.huisou.mapper.RenewTraceMapper;
import com.huisou.po.RenewTracePo;
import com.huisou.po.UniversalPo;
import com.huisou.service.RenewTraceService;
import com.huisou.vo.RenewTraceVo;
@Service
public class RenewTraceServiceImpl implements RenewTraceService {
	
	@Autowired
	private RenewTraceMapper renewTraceMapper;
	@Override
	public void save(RenewTracePo renewTracePo) {
		renewTraceMapper.insert(renewTracePo);
	}
	@Override
	public List<RenewTraceVo> findRenewTraceByItemid(Integer itemid) {
		
		List<UniversalPo> renewTracePoList = renewTraceMapper.findRenewTraceByItemid(itemid);
		if (renewTracePoList.size() < 1) {
			return null;
		}
		List<RenewTraceVo> list = null;
		try {
			 list = ConvertUtil.convertDtoAndVo(renewTracePoList,RenewTraceVo.class);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
  
}
