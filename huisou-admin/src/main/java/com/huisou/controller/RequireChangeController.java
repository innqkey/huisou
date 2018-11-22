package com.huisou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.ItemRequirePo;
import com.huisou.po.UniversalPo;
import com.huisou.service.ItemRequireService;
import com.huisou.service.PicRecordService;
import com.huisou.service.RequireChangeService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequireChangeVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:18:01 
* 类说明 
*/
@RestController
@RequestMapping("/requireChange")
public class RequireChangeController extends BaseController{

	@Autowired
	private RequireChangeService requireChangeService;
	
	@Autowired
	PicRecordService picRecordService;
	
	@Autowired
	ItemRequireService itemRequireService;
	
	//添加变更记录
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Integer itemid, Integer changetype,Integer requireid){
		if(itemid == null || itemid <= 0 || changetype == null || changetype<0 || requireid == null || requireid<0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		try {
			Integer createby = super.getUserId(request);
			Integer dealstatus = ContextConstant.ITEM_REQUIRE_CHANGE_UNTREATED;
			requireChangeService.add(itemid,changetype,dealstatus,createby,requireid);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 加载需求变更记录数据
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/search")
	public String search(HttpServletRequest request,PageTemp pageTemp){
		String customerPhone = request.getParameter("phone");
		String contact= request.getParameter("contact");
		String itemname = request.getParameter("itemname");
		String salename = request.getParameter("salename");
		String saleid = request.getParameter("saleid");
		String begindate = request.getParameter("begindate");
		String endDate = request.getParameter("enddate");
		String itemtype = request.getParameter("itemtype");
		 try {
			PageInfo<RequireChangeVo> pageInfo = requireChangeService.serarch(customerPhone,contact,itemname,salename,saleid,pageTemp,begindate,endDate,itemtype);
			return ResUtils.okRes(pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量处理需求变更
	 * @param request
	 * @param itemids
	 * @return
	 */
	@RequestMapping(value="/dispose")
	public String dispose(HttpServletRequest request,@RequestParam("requirechangeids[]")List<Integer> requirechangeids){
		if(requirechangeids==null || requirechangeids.isEmpty() ){
			return ResUtils.errRes("102", "请求参数错误");
		}
		try {
			requireChangeService.dispose(requirechangeids);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	@RequestMapping(value="/requireDetail")
	public String requireDetail(HttpServletRequest request, Integer requireid){
		if(requireid==null || requireid<0 ){
			return ResUtils.errRes("102", "请求参数错误");
		}
		try {
			List<Object> list = new ArrayList<>();
			 //当前日志 所有附件压缩包
            List<String> zips = picRecordService.findInfoByTypeAndId1(ContextConstant.FILES_STEM_XQBG, requireid, ContextConstant.doc);
            ItemRequirePo itemRequirePo = itemRequireService.findByrequireid(requireid);
            list.add(zips);
            list.add(itemRequirePo);
            return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
