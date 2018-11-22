package com.huisou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huisou.service.NoticeService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月6日 下午2:41:31 
* 类说明 
*/
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
}
