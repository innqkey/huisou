package com.huisou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.IntentionCustPo;
import com.huisou.service.IntentionCustService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月28日 下午3:09:36 
* 类说明 
*/
@RestController
@RequestMapping("/intentionCust")
public class IntentionCustController extends BaseController{
	
	@Autowired
	private IntentionCustService custService;
	
	/**
	 * 培训课程列表首页数据展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findAll")
	public String findAll(HttpServletRequest request){
		try {
			String name = request.getParameter("name");
			List<IntentionCustPo> list = custService.findAll(name);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
