package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.ServiceItemPo;
import com.huisou.service.ServiceItemService;

@RestController
@RequestMapping("/serviceItemController")
public class ServiceItemController extends BaseController{

	@Autowired
	ServiceItemService serviceItem;
	
	@RequestMapping(value="/saveServiceItemBatch")
	public String saveServiceItemBatch(HttpServletRequest request, @RequestParam("itemIds[]") List<Integer> itemids, Integer userid, String serremark){
		
		if(null==itemids||itemids.size()<1){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(StringUtils.isBlank(String.valueOf(userid))){
			return ResUtils.errRes("102", "请求参数错误");
		}
		for(Integer itemid : itemids){
			ServiceItemPo po = new ServiceItemPo();
			po.setItemid(itemid);
			po.setUserid(userid);
			po.setCreateby(super.getUserId(request));
			po.setCreatedate(new Date());
			po.setSerremark(serremark);
			serviceItem.saveServiceItem(po);
		}
		return ResUtils.okRes();
		
	}
}
