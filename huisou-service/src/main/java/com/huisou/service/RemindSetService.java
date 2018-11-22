package com.huisou.service;

import java.util.List;

import com.huisou.po.RemindSetPo;

public interface RemindSetService {
	public List<RemindSetPo> list(RemindSetPo record);

	public void saveOrUpdate(RemindSetPo po);
	
	public RemindSetPo selectByid(String key);
}
