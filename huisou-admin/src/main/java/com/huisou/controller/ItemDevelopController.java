package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.AccidentRecordPo;
import com.huisou.po.ItemDevelopPo;
import com.huisou.po.ItemRequirePo;
import com.huisou.po.ItemsPo;
import com.huisou.po.PicRecordPo;
import com.huisou.service.ItemDevelopService;
import com.huisou.service.ItemRequireService;
import com.huisou.service.ItemService;
import com.huisou.service.PicRecordService;
import com.huisou.vo.AccidentVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月20日
*/

@RestController
@RequestMapping(value = "/itemDevelop")
public class ItemDevelopController extends BaseController {
	
	@Autowired
	ItemDevelopService itemDevelopService;
	
	@Autowired
	PicRecordService picRecordService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemRequireService itemRequireService;
	
	/*
	 * 返回/查找 项目进度列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,PageTemp pageTemp){
		try {
			String itemname = StringUtils.stripToEmpty(request.getParameter("itemname"));
			String devid = request.getParameter("devid");
			Map<String, String> map = new HashMap<>();
			String createby = String.valueOf(super.getUserId(request));

			if (StringUtils.isNotBlank(devid) && Integer.parseInt(devid) > 0){
				ItemDevelopPo selectedPo = itemDevelopService.selectOnePo(Integer.parseInt(devid));
				return ResUtils.okRes(selectedPo);
			}else {
//				if (!super.getLeader(request)){
//					map.put("itemname", itemname);
//					map.put("createby", "");
//					PageInfo<ItemDevelopPo> poList = itemDevelopService.queryByMultiParas(map,pageTemp);
//			        return ResUtils.okRes(poList);
//				}else {
					map.put("itemname", itemname);
					map.put("createby", "");
					PageInfo<ItemDevelopPo> poList = itemDevelopService.queryByMultiParas(map,pageTemp);
			        return ResUtils.okRes(poList);
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
	
	/*
	 * 保存项目进度记录
	 * 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request){
		ItemDevelopPo itemDevelopPo = new ItemDevelopPo();
		itemDevelopPo.setItemname(request.getParameter("itemname"));
		itemDevelopPo.setCreateby(super.getUserId(request));
		if (StringUtils.isNotBlank(request.getParameter("itemid"))){
			itemDevelopPo.setItemid(Integer.parseInt(request.getParameter("itemid")));
		}
		itemDevelopPo.setDevdatebegin(
				DateUtils.format(request.getParameter("devdatebegin"), DateUtils.Y_M_D));
		itemDevelopPo.setDevdateend(
				DateUtils.format(request.getParameter("devdateend"), DateUtils.Y_M_D));
		if (StringUtils.isNotBlank(request.getParameter("devstatus"))){
			itemDevelopPo.setDevstatus(Integer.parseInt(request.getParameter("devstatus")));
		}
		if (StringUtils.isNotBlank(request.getParameter("delystatus"))){
			itemDevelopPo.setDelystatus(Integer.parseInt(request.getParameter("delystatus")));
		}
		if (StringUtils.isNotBlank(request.getParameter("createdate"))){
			itemDevelopPo.setCreatedate(
					DateUtils.format(request.getParameter("createdate"), DateUtils.Y_M_D));
		} else{
			itemDevelopPo.setCreatedate(new Date());
		}
		
		itemDevelopService.saveItemDev(itemDevelopPo);
		return ResUtils.okRes();
	}
	
	/*
	 * 根据devid 更新项目进度
	 * @parameter devid
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(HttpServletRequest request){
		if (StringUtils.isBlank(request.getParameter("devid")) || 
				Integer.parseInt(request.getParameter("devid")) < 0){
			return ResUtils.errRes("102", "请求参数解析错误");
		}
		int devid = Integer.parseInt(request.getParameter("devid"));
		ItemDevelopPo selectedPo = itemDevelopService.selectOnePo(devid);
		selectedPo.setUpdateby(super.getUserId(request));
		selectedPo.setItemname(request.getParameter("itemname"));
		if (StringUtils.isNotBlank(request.getParameter("itemid"))){
			selectedPo.setItemid(Integer.parseInt(request.getParameter("itemid")));
		} else{
			selectedPo.setItemid(0);
		}
		selectedPo.setDevdatebegin(
				DateUtils.format(request.getParameter("devdatebegin"), DateUtils.Y_M_D));
		selectedPo.setDevdateend(
				DateUtils.format(request.getParameter("devdateend"), DateUtils.Y_M_D));
		selectedPo.setDevstatus(Integer.parseInt(request.getParameter("devstatus")));
		selectedPo.setDelystatus(Integer.parseInt(request.getParameter("delystatus")));
//		if (StringUtils.isNotBlank(request.getParameter("createdate"))){
//			selectedPo.setCreatedate(
//					DateUtils.format(request.getParameter("createdate"), DateUtils.Y_M_D));
//		} else{
//			selectedPo.setCreatedate(new Date());
//		}
		itemDevelopService.updateDevelop(selectedPo);
		return ResUtils.okRes();
	}
	
	/*
	 * 根据项目id 返回项目需求和文档
	 * @param itemid
	 */
	@RequestMapping(value = "/itemDocs")
	public String itemDocs(HttpServletRequest request, Integer itemid) throws IllegalAccessException, InstantiationException{
		ArrayList<String> requireLists = new ArrayList<>();
		String itemrequire = itemService.findItemPoById(itemid).getItemrequire();
		if (StringUtils.isNotBlank(itemrequire)){
			requireLists.add(itemrequire);
		}
		List itemRequires = itemRequireService.findAllByItemid(itemid);
		for (Object object : itemRequires) {
			ItemRequirePo requirePo = (ItemRequirePo) ConvertUtil.convertDtoAndVo(object, ItemRequirePo.class);;
			if (StringUtils.isNotBlank(requirePo.getRequirecontent())){
				requireLists.add(requirePo.getRequirecontent());
			}
		}

		ArrayList<String> picLists = new ArrayList<>();
		List<PicRecordPo> picPos = picRecordService.findDocByItemId(Integer.toString(itemid));

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("itemrequire", requireLists);
		map.put("file", picLists);
		return ResUtils.okRes(map); 
	}
	
}
