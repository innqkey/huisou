package com.huisou.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.huisou.service.TypeCountService;

/** 
* @author qinkai 
* @date 2017年9月8日
*/

@RestController
@RequestMapping(value = "/count")
public class TypeCountController extends BaseController{

	@Autowired
	TypeCountService typeCountService;
	
	@RequestMapping(value = "/itemTypeCount")
	public String itemTypeCount(HttpServletRequest request){
		boolean leader = super.getLeader(request);
		PageInfo salePageInfo = null;
		if (leader){
			salePageInfo = typeCountService.itemTypeCountBySaleId(super.getSaleId(request));
		}else {
			salePageInfo = typeCountService.itemTypeCount();
		}
		return ResUtils.okRes(salePageInfo);
	}
	
	@RequestMapping(value = "/contactTypeCount")
	public String contactTypeCount(HttpServletRequest request){
		boolean leader = super.getLeader(request);
		PageInfo contactPageInfo = null;
		if (leader){
			contactPageInfo = typeCountService.contactTypeCountBySaleId(super.getSaleId(request));
		}else {
			contactPageInfo = typeCountService.contactTypeCount();
		}
		return ResUtils.okRes(contactPageInfo);
	}
}
