package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.common.MyMapper;
import com.huisou.po.TradeCecordPo;
import com.huisou.po.UniversalPo;
import com.huisou.vo.TradeCecordVo;
import com.huisou.vo.TradeVo;

@Repository
public interface TradeCecordPoMapper extends MyMapper<TradeCecordPo> {
	List findAllMap(TradeCecordPo po);
	
	List<UniversalPo> findTradeReByParasAndUnviersalPo(@Param("itemid")Integer itemid);
	
	TradeCecordVo findTradeLastByParas(TradeCecordPo po);
}