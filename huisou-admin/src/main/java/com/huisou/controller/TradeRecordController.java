package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.DictConConstant;
import com.huisou.po.ItemsPo;
import com.huisou.po.TradeCecordPo;
import com.huisou.po.UniversalPo;
import com.huisou.service.CustomerService;
import com.huisou.service.ItemService;
import com.huisou.service.TradeRecordService;
import com.huisou.service.UserService;
import com.huisou.vo.CustomerInfoVo;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TradeCecordVo;
import com.huisou.vo.TradeVo;

import com.huisou.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tradeController")
public class TradeRecordController extends BaseController{

	@Autowired
    TradeRecordService tradeService;
	@Autowired
	CustomerService custService;
	@Autowired
	private ItemService itemService;
    
    @RequestMapping(value = "/listMap")
    public String listMap(TradeCecordPo po, PageTemp page) throws IllegalAccessException, InstantiationException {
        
        PageInfo poListMap = tradeService.findAllMap(po,page);
        //PageInfo<UserVo> userVos2 = (PageInfo<UserVo>) ConvertUtil.convertDtoAndVo(userDaoList2, PageInfo.class);
        return ResUtils.okRes(poListMap);
    }
    
    @RequestMapping(value = "/tradeListByItem")
    public String tradeListByItem(TradeCecordPo po, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        if(null!=po.getItemid()&&po.getItemid()>0){
        	TradeCecordVo rePo = tradeService.findTradeLastByParas(po);
        	removeRegister();
            return ResUtils.okRes(rePo);
        }else{
        	return ResUtils.errRes("102", "请求参数错误");
        }
    }

    @RequestMapping(value = "/save")
    public String save(TradeCecordPo po, HttpServletRequest request) {
		try {
			if(null!=po.getItemid()&&po.getItemid()>0){
				ItemCustSaleVo allVo = custService.findVoByitemid(po.getItemid());
				
				ItemsPo itempo = new ItemsPo();
				itempo.setItemid(po.getItemid());
		    	itempo.setCommitstatus(2);
		    	
				if(null!=allVo){
					po.setCustid(allVo.getCustid());
					po.setSaleid(allVo.getSaleid());
					if(null!=po.getTradeid()&&po.getTradeid()>0){
						po.setUpdateby(super.getUserId(request));
					}else{
						po.setSalename(allVo.getSalename());
				    	po.setCreateby(super.getUserId(request));
				    	po.setCreatedate(new Date());
				    	itempo.setItemid(po.getItemid());
				    	itempo.setTradetype(po.getTradetype());
				    	itemService.updateItemByItemid(itempo);
					}
			    	tradeService.save(po);
			    	
			    	itemService.updateItemByItemid(itempo);
			    	
			        return ResUtils.okRes();
				}
			}
			return ResUtils.errRes("102", "请求参数错误");
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
		return ResUtils.execRes();
    }
    
    @RequestMapping(value = "/audit")
    public String audit(TradeCecordPo po, HttpServletRequest request) {
    	if(null!=po.getTradeid()&&po.getTradeid()>0){
    		TradeCecordPo resPo = tradeService.findOneTradePo(po.getTradeid());
    		po.setAudituserid(super.getUserId(request));
        	po.setAuditusername(super.getUserName(request));
        	po.setAuditdate(new Date());
        	tradeService.audit(po);
        	//审核通过
        	if(po.getAuditstatus()==DictConConstant.Auditstatus.Auditstatus1.getI()){
        		//缴费类型：1正常;2订金;3尾款;4续费;5退款
            	Integer tradeType = resPo.getTradetype();
            	
            	ItemsPo itempo = new ItemsPo();
    	    	itempo.setItemid(resPo.getItemid());
    	    	itempo.setCommitstatus(1);
    	    	
            	//订金审核通过-->3尾款
            	if(tradeType==DictConConstant.Tradetype.Tradetype2.getI()){
        	    	itempo.setTradetype(DictConConstant.Tradetype.Tradetype3.getI());
        	    	itemService.updateItemByItemid(itempo);
            	}
            	//尾款审核通过-->1正常
            	if(tradeType==DictConConstant.Tradetype.Tradetype3.getI()){
        	    	itempo.setTradetype(DictConConstant.Tradetype.Tradetype1.getI());
        	    	itempo.setCommitstatus(DictConConstant.Commitstatus.Commitstatus0.getI());
        	    	itempo.setAuditstatus(po.getAuditstatus());
        	    	itemService.updateItemByItemid(itempo);
            	}
            	//续费审核通过-->1正常
            	if(tradeType==DictConConstant.Tradetype.Tradetype4.getI()){
        	    	itempo.setTradetype(DictConConstant.Tradetype.Tradetype1.getI());
        	    	itempo.setAuditstatus(po.getAuditstatus());
        	    	itempo.setCommitstatus(DictConConstant.Commitstatus.Commitstatus0.getI());
        	    	itemService.updateItemByItemid(itempo);
            	}
            	//退款审核通过-->1正常
            	if(tradeType==DictConConstant.Tradetype.Tradetype5.getI()){
        	    	itempo.setTradetype(DictConConstant.Tradetype.Tradetype7.getI());
        	    	itempo.setAuditstatus(po.getAuditstatus());
        	    	itempo.setCommitstatus(DictConConstant.Commitstatus.Commitstatus0.getI());
        	    	itemService.updateItemByItemid(itempo);
            	}
            	//全款审核通过-->1正常，审核通过
            	if(tradeType==DictConConstant.Tradetype.Tradetype6.getI()){
        	    	itempo.setTradetype(DictConConstant.Tradetype.Tradetype1.getI());
        	    	itempo.setAuditstatus(po.getAuditstatus());
        	    	itempo.setCommitstatus(DictConConstant.Commitstatus.Commitstatus0.getI());
        	    	itemService.updateItemByItemid(itempo);
            	}
        	}
        	//审核不通过，更新item状态commitstatus
        	else{
        		ItemsPo itempo = new ItemsPo();
    	    	itempo.setItemid(resPo.getItemid());
    	    	itempo.setCommitstatus(DictConConstant.Commitstatus.Commitstatus4.getI());
    	    	itemService.updateItemByItemid(itempo);
        	}
	    	
	    	
            return ResUtils.okRes();
    	}
    	return ResUtils.errRes("102", "请求参数错误");
    }
    @RequestMapping(value = "/page")
    public String page() {
        return "page";
    }
    
    @RequestMapping(value = "/tradeListDetail")
    public String tradeListDetail(TradeCecordPo tradePo, PageTemp page) throws IllegalAccessException, InstantiationException {
        if(null!=tradePo&&null!=tradePo.getItemid()&&tradePo.getItemid()>0){
        	List<UniversalPo> tradeCecordPos = tradeService.findTradeReByParasAndUnviersalPo(tradePo);
        	List<TradeCecordVo> converttradeCecordPos = ConvertUtil.convertDtoAndVo(tradeCecordPos, TradeCecordVo.class);
            return ResUtils.okRes(converttradeCecordPos);
        }
        return ResUtils.errRes("102", "请求参数错误");
    }
}
