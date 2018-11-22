package com.huisou.service;

import java.util.List;

import com.huisou.po.RegionPo;

public interface RegionService {

	List<RegionPo> findAllProvince();

	List<RegionPo> findCityOrAreaByParentId(Integer regionId);

	RegionPo findRegionById(String valueOf);

}
