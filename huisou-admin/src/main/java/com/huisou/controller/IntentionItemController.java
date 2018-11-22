package com.huisou.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.huisou.constant.DictConConstant;
import com.huisou.constant.DictConConstant.Itemtype;
import com.huisou.po.IntentionItemPo;
import com.huisou.po.TrainingCoursePo;
import com.huisou.service.IntentionItemService;
import com.huisou.vo.ItemCourseVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/
@RestController
@RequestMapping(value = "/intentionItem")
public class IntentionItemController extends BaseController{
	
	@Autowired
	private IntentionItemService intentionItemService;

	/*
	 * 新增一条项目
	 * @param request
	 * @param name
	 * @param itemtype
	 * @param intentioncustid
	 * @return
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request, String name, String itemtype, String intentioncustid, String courseid){
		try {	
			if (StringUtils.isEmpty(name) || StringUtils.isEmpty(itemtype) || StringUtils.isEmpty(intentioncustid)
					|| StringUtils.isEmpty(courseid)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			Integer type = Integer.valueOf(itemtype);
			Integer custid = Integer.valueOf(intentioncustid);
			Integer courseId = Integer.valueOf(courseid);			
			int userId = super.getUserId(request);
			intentionItemService.saveItem(name, type,custid,userId,courseId);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResUtils.okRes();
	}
	
	/**
	 * 查找所有的新增项目
	 * @param custid
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(String intentioncustid, String courseid,HttpServletRequest request){
		try {
			if (StringUtils.isEmpty(intentioncustid) || StringUtils.isEmpty(courseid)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			int custId = Integer.valueOf(intentioncustid);
			int courseId = Integer.valueOf(courseid);
			int userId = super.getUserId(request);
			List<ItemCourseVo> voList = intentionItemService.selectByParams(custId, courseId,userId);
			for (int i =0; i < voList.size(); i++){
				int id = voList.get(i).getItemtype();
				voList.get(i).setItemtypename(DictConConstant.getDicName("Itemtype", id));
			}
			return ResUtils.okRes(voList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResUtils.okRes();
	}
}
