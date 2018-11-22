package com.huisou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.UrlPo;
import com.huisou.service.UrlService;


/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 下午4:49:48 
* 类说明 
*/
@RestController
@RequestMapping(value = "/url")
public class UrlController extends BaseController{

	@Autowired
	private UrlService urlService;
	
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request){
		try {
			List<UrlPo> list = urlService.findAll();
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
