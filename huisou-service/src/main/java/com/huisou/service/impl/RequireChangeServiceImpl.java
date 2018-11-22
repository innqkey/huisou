package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.DictConConstant;
import com.huisou.mapper.RegionPoMapper;
import com.huisou.mapper.RequireChangePoMapper;
import com.huisou.po.RegionPo;
import com.huisou.po.RequireChangePo;
import com.huisou.service.RequireChangeService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequireChangeVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:19:09 
* 类说明 
*/
@Service
public class RequireChangeServiceImpl implements RequireChangeService{

	@Autowired
	RequireChangePoMapper requireChangePoMapper;
	
	@Autowired
	private RegionPoMapper regionPoMapper;
	
	@Override
	public void add(Integer itemid, Integer changetype, Integer dealstatus, Integer createby,Integer requireid) {
		// TODO Auto-generated method stub
		RequireChangePo requireChangePo = new  RequireChangePo();
		requireChangePo.setItemid(itemid);
		//查找最近一次的变动记录
		Integer changenum = 1;
		RequireChangePo po = requireChangePoMapper.findRecentlyChangePo(itemid);
		if(po!=null){
			changenum = po.getChangenum()+1;
		}
		requireChangePo.setChangenum(changenum);
		requireChangePo.setChangetype(changetype);
		requireChangePo.setDealstatus(dealstatus);
		requireChangePo.setCreateby(createby);
		requireChangePo.setCreatedate(new Date());
		requireChangePo.setRequireid(requireid);
		requireChangePoMapper.insert(requireChangePo);
	}

	@Override
	public PageInfo<RequireChangeVo> serarch(String phone, String contact, String itemname, String salename,
			String saleid, PageTemp pageTemp, String beginDate, String endDate, String itemtype) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		
		List<RequireChangeVo> list = requireChangePoMapper.serarch(phone,contact,itemname,salename,Integer.parseInt(saleid),beginDate,endDate,Integer.parseInt(itemtype));
		for (RequireChangeVo requireChangeVo : list) {
			String province = getRegionInfo(requireChangeVo.getProvince());
			String city = getRegionInfo(requireChangeVo.getCity() );
			String area = getRegionInfo(requireChangeVo.getArea() );
			requireChangeVo.setAddressInfo(province + city + area + requireChangeVo.getAddress());
			requireChangeVo.setItemstatusName(DictConConstant.getDicName("custstatus", requireChangeVo.getStatus()));
			requireChangeVo.setCuststatusName(DictConConstant.getDicName("custstatus", requireChangeVo.getCuststatus()));
			requireChangeVo.setDealstatusName(DictConConstant.getDicName("dealstatus", requireChangeVo.getDealstatus()));
			requireChangeVo.setItemtypeName(DictConConstant.getDicName("itemtype",requireChangeVo.getItemtype()));
		}
		PageInfo<RequireChangeVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 处理省市区
	 * @param province
	 * @return
	 */
	private String getRegionInfo(String province) {
		if (StringUtils.isNotBlank(province)) {
			RegionPo regionPo = new RegionPo();
			if (StringUtils.isNotBlank(province)&&StringUtils.isNumeric(province)) {
				regionPo.setId(Integer.valueOf(province));
				List<RegionPo> result = regionPoMapper.select(regionPo);
				if (result != null && result.size() > 0) {
					return result.get(0).getName();
				} else {
					return null;
				}
			} else {
				return "";
			}
		}
		return "";

	}

	@Override
	public void dispose(List<Integer> requirechangeids) {
		// TODO Auto-generated method stub
		for (Integer requirechangeid : requirechangeids) {
			requireChangePoMapper.updateDealstatus(requirechangeid);
		}
	}
}
