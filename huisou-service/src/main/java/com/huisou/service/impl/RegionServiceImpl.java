package com.huisou.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RegionPoMapper;
import com.huisou.po.RegionPo;
import com.huisou.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionPoMapper regionPoMapper;
	@Override
	public List<RegionPo> findAllProvince() {
		RegionPo regionPo = new RegionPo();
		regionPo.setGrade(1);
		List<RegionPo> list = regionPoMapper.select(regionPo);
		return list;
	}
	@Override
	public List<RegionPo> findCityOrAreaByParentId(Integer regionId) {
		RegionPo regionPo = new RegionPo();
		regionPo.setParent(regionId);
		List<RegionPo> list = regionPoMapper.select(regionPo);
		return list;
	}
	@Override
	public RegionPo findRegionById(String valueOf) {
		if(StringUtils.isNotBlank(valueOf)&&StringUtils.isNumeric(valueOf)){
			RegionPo regionPo = new RegionPo();
			regionPo.setId(Integer.valueOf(valueOf));
			List<RegionPo> select = regionPoMapper.select(regionPo);
			if(null!=select&&select.size()>0){
				return select.get(0);
			}
		}
		return null;
	}

}
