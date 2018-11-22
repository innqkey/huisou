package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.UrlPoMapper;
import com.huisou.po.UrlPo;
import com.huisou.service.UrlService;


/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 下午4:52:40 
* 类说明 
*/
@Service
public class UrlServiceImpl implements UrlService{

	@Autowired
	private UrlPoMapper urlPoMapper;

	@Override
	public List<UrlPo> findAll() {
		// TODO Auto-generated method stub
		List<UrlPo> list = urlPoMapper.findAll();
		return list;
	}
}
