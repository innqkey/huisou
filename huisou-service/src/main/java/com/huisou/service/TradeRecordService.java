package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TradeCecordPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TradeCecordVo;
import com.huisou.vo.TradeVo;
import com.huisou.po.UniversalPo;

public interface TradeRecordService {
	PageInfo findAllMap(TradeCecordPo po, PageTemp page);
	
	public List<TradeCecordPo> findTradeReByParas(TradeCecordPo po);
	
	public TradeCecordVo findTradeLastByParas(TradeCecordPo po);
	
	public void save(TradeCecordPo po);
	void audit(TradeCecordPo po);
	public List<UniversalPo> findTradeReByParasAndUnviersalPo(
			TradeCecordPo tradePo);
	
	public TradeCecordPo findOneTradePo(Integer tradeid);
}
