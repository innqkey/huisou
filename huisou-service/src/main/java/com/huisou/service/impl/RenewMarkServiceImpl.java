package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.RenewMarkMapper;
import com.huisou.po.RenewMarkPo;
import com.huisou.service.RenewMarkService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RenewMarkVo;

@Service
public class RenewMarkServiceImpl implements RenewMarkService {

	@Autowired
	private RenewMarkMapper markMapper;
	
	@Override
	public void save(RenewMarkPo markPo) {
		// TODO Auto-generated method stub
		markMapper.insertSelective(markPo);
	}

	@Override
	public PageInfo<RenewMarkVo> selectMarkList(PageTemp pageTemp, Map reMap) {
		// TODO Auto-generated method stub
		List<RenewMarkVo> list = null;
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		list = markMapper.selectMarkList(reMap);
		PageInfo<RenewMarkVo> pageInfo = new PageInfo<RenewMarkVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<RenewMarkVo> selectRenewSucList(PageTemp pageTemp, Map reMap) {
		// TODO Auto-generated method stub
		List<RenewMarkVo> list = null;
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		list = markMapper.selectRenewSucList(reMap);
	
		PageInfo<RenewMarkVo> pageInfo = new PageInfo<RenewMarkVo>(list);
		return pageInfo;
	}

}
