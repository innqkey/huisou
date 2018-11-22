package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.RenewMarkPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RenewMarkVo;

public interface RenewMarkService {
	public void save(RenewMarkPo markPo);

	public PageInfo<RenewMarkVo> selectMarkList(PageTemp pageTemp, Map reMap);
	public PageInfo<RenewMarkVo> selectRenewSucList(PageTemp pageTemp, Map reMap);
}
