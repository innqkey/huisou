package com.huisou.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.ItemsPoMapper;
import com.huisou.mapper.TradeCecordPoMapper;
import com.huisou.po.ItemsPo;
import com.huisou.po.TradeCecordPo;
import com.huisou.po.UniversalPo;
import com.huisou.service.TradeRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TradeCecordVo;
import com.huisou.vo.TradeVo;

@Service
public class TradeRecordServiceImpl implements TradeRecordService {

	@Autowired
	private TradeCecordPoMapper mapper;
	
	@Autowired
	private ItemsPoMapper itemMapper;

	@Override
	public PageInfo findAllMap(TradeCecordPo po, PageTemp pageT) {
		//分页必加
		PageHelper.startPage(pageT.getPageNum(), pageT.getPageSize());
		List<TradeVo> pos = mapper.findAllMap(po);
        Page page = (Page) pos;
        return new PageInfo(pos);
	}
	
	@Override
	public List<TradeCecordPo> findTradeReByParas(TradeCecordPo po) {
		// TODO Auto-generated method stub
		List<TradeCecordPo> poList = mapper.select(po);
		return poList;
	}
	@Override
	public void save(TradeCecordPo po) {
		//更新
//		ItemsPo itemPo = itemMapper.selectByPrimaryKey(po.getItemid());
//		if(null!=itemPo){
//			po.setCustid(itemPo.getCustid());
//			po.setSaleid(itemPo.getSaleid());
//		}
		// TODO Auto-generated method stub
		if(null!=po.getTradeid()&&po.getTradeid()>0){
			TradeCecordPo tradePo = mapper.selectByPrimaryKey(po.getTradeid());
			//如果此记录审核不通过，则前端为重新提交，新建一条记录
			if(tradePo.getAuditstatus()==2){
				po.setTradeid(null);
				po.setSalename(tradePo.getSalename());
		    	po.setCreateby(tradePo.getUpdateby());
		    	po.setCreatedate(new Date());
				mapper.insertSelective(po);
			}else{
				mapper.updateByPrimaryKeySelective(po);
			}
		}else{
			mapper.insertSelective(po);
		}
	}
	
	@Override
	public void audit(TradeCecordPo po) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(po);
	}
	@Override
	public List<UniversalPo> findTradeReByParasAndUnviersalPo(
			TradeCecordPo tradePo) {
		List<UniversalPo> list=mapper.findTradeReByParasAndUnviersalPo(tradePo.getItemid());
		return list;
	}

	@Override
	public TradeCecordPo findOneTradePo(Integer tradeid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(tradeid);
	}

	@Override
	public TradeCecordVo findTradeLastByParas(TradeCecordPo po) {
		// TODO Auto-generated method stub
		return mapper.findTradeLastByParas(po);
	}

}
