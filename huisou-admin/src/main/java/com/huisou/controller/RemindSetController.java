package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.RemindSetPo;
import com.huisou.service.RemindSetService;

@RestController
@RequestMapping("/remindSetController")
public class RemindSetController extends BaseController{

	@Autowired
	RemindSetService service;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,RemindSetPo record){
		List<RemindSetPo> list = service.list(record);
		return ResUtils.okRes(list);
	}
	
	@RequestMapping(value = "/saveOrUpd")
	public String saveOrUpd(HttpServletRequest request, RemindSetPo po){
		if(null==po){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getBusicode()||po.getBusicode()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getRemindtimespan()||po.getRemindtimespan()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		
		if(null==po.getRemindid()||po.getRemindid()<=0){
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
	public String update(HttpServletRequest request, RemindSetPo po){
		if(null==po){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getRemindid()||po.getRemindid()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getBusicode()||po.getBusicode()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(null==po.getRemindtimespan()||po.getRemindtimespan()<=0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		po.setUpdateby(super.getUserId(request));
		service.saveOrUpdate(po);
		return ResUtils.okRes();
	}
	
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, String tradeid){
		return ResUtils.okRes(service.selectByid(tradeid));
	}
}
