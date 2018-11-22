package com.huisou.service;

import java.util.List;

import com.huisou.po.RenewTracePo;
import com.huisou.vo.RenewTraceVo;

public interface RenewTraceService {

	void save(RenewTracePo renewTracePo);

	List<RenewTraceVo> findRenewTraceByItemid(Integer itemid);

}
