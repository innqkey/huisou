package com.huisou.mapper;

import java.util.List;

import com.common.MyMapper;
import com.huisou.po.RenewTracePo;
import com.huisou.po.UniversalPo;

public interface RenewTraceMapper extends MyMapper<RenewTracePo>{

	List<UniversalPo> findRenewTraceByItemid(Integer itemid);

}
