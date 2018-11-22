package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.SaleRemindSetPo;
import com.huisou.service.SaleRemSetService;

@RestController
@RequestMapping("/saleremSetController")
public class SaleRemSetController extends BaseController{

	@Autowired
	SaleRemSetService service;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,SaleRemindSetPo record){
		List<SaleRemindSetPo> list = service.list(record);
		
//		service.getTimeSpan(1, 1);
		return ResUtils.okRes(list);
	}
	
	@RequestMapping(value = "/saveOrUpd")
	public String saveOrUpd(HttpServletRequest request, SaleRemindSetPo po){
		if(null==po){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getSalebusicode()||po.getSalebusicode()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getSaleremindtimespan()||po.getSaleremindtimespan()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		
		if(null==po.getSaleremindid()||po.getSaleremindid()<=0){
			po.setSaleid(super.getSaleId(request));
			po.setSalename(super.getSaleName(request));
			po.setCreateby(super.getUserId(request));
			po.setCreatedate(new Date());
			service.saveOrUpdate(po);
		}else{
			po.setUpdateby(super.getUserId(request));
			service.saveOrUpdate(po);
		}
		return ResUtils.okRes();
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, SaleRemindSetPo po){
		if(null==po){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getSaleremindid()||po.getSaleremindid()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getSalebusicode()||po.getSalebusicode()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getSaleremindtimespan()||po.getSaleremindtimespan()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		po.setUpdateby(super.getUserId(request));
		service.saveOrUpdate(po);
		return ResUtils.okRes();
	}
}
