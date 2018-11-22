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
import com.huisou.po.ServiceRenewPo;
import com.huisou.service.ServiceRenewService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月14日 上午9:15:12 
* 类说明 
*/
@RequestMapping(value = "/serviceRenewController")
@RestController
public class ServiceRenewController extends BaseController{
	
	@Autowired
	ServiceRenewService serviceRenewService;
	
	@RequestMapping(value="/saveServiceRenewBatch")
	public String saveServiceRenewBatch(HttpServletRequest request, @RequestParam("itemIds[]") List<Integer> itemids, Integer userid, String serremark){
		
		if(null==itemids||itemids.size()<1){
			return ResUtils.errRes("102", "请求参数错误");
		}
		if(StringUtils.isBlank(String.valueOf(userid))){
			return ResUtils.errRes("102", "请求参数错误");
		}
		for(Integer itemid : itemids){
			ServiceRenewPo po = new ServiceRenewPo();
			po.setItemid(itemid);
			po.setUserid(userid);
			po.setCreateby(super.getUserId(request));
			po.setCreatedate(new Date());
			po.setSerremark(serremark);
			serviceRenewService.saveServiceItem(po);
		}
		return ResUtils.okRes();
		
	}
}
