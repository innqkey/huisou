package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.huisou.constant.ContextConstant;
import com.huisou.po.ItemRequirePo;
import com.huisou.po.ItemsPo;
import com.huisou.po.NoticePo;
import com.huisou.po.PicRecordPo;
import com.huisou.service.ItemRequireService;
import com.huisou.service.ItemService;
import com.huisou.service.NoticeService;
import com.huisou.service.PicRecordService;
import com.huisou.service.RequireChangeService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午8:52:32 
* 类说明 
*/
@RestController
@RequestMapping("/itemRequire")
public class ItemRequireController extends BaseController{

	@Autowired
	private ItemRequireService itemRequireService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
    PicRecordService picRecordService;
	
	@Autowired
	private RequireChangeService requireChangeService;
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 在项目修改页面的时候，添加制作需求
	 * @param request
	 * @param itemid
	 * @param requirecontent
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Integer itemid, String requirecontent ,@RequestParam(required=false,value = "itemdocUrl2[]") List<String> itemdocUrl2){
		
		if(itemid == null || itemid <= 0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		
		try {
			int createby = super.getUserId(request);
			Integer requireid =	itemRequireService.add(itemid,requirecontent,createby);
			if(itemdocUrl2 != null && itemdocUrl2.size()>0){
				List<PicRecordPo> list = new ArrayList<>();
	                createPic(list,itemid, itemdocUrl2, ContextConstant.FILES_STEM_XQBG, ContextConstant.doc, requireid , getUserId(request));
	            if (list.size() > 0) {
	                picRecordService.insertList(list);
	            }
			}
			Integer changetype = 3;
			Integer dealstatus = ContextConstant.ITEM_REQUIRE_CHANGE_UNTREATED;
			requireChangeService.add(itemid,changetype,dealstatus,createby,requireid);
			
			NoticePo noticePo = new NoticePo();
			ItemsPo itemsPo = itemService.findItemPoById(itemid);
			
			noticePo.setItemid(itemid);
			noticePo.setItemname(itemsPo.getItemname());
			noticePo.setCreateby(super.getUserId(request));
			noticePo.setNoticetype(ContextConstant.NOTICETYPE_REQUIRE_CHANGE);
			noticePo.setNoticeacceptype(ContextConstant.NOTICEACCEPTYPE_SERVICE);
			noticePo.setNoticemessage(requirecontent);
			noticeService.insert(noticePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据项目id查询所有的项目需求
	 * @param request
	 * @param itemid
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Integer itemid){
		if(itemid == null || itemid <= 0){
			return ResUtils.errRes("102", "请求参数错误");
		}
		try {
			List itemRequires = itemRequireService.findAllByItemid(itemid);
			List list = new ArrayList<>();
			for (Object object : itemRequires) {
				ItemRequirePo requirePo = (ItemRequirePo) ConvertUtil.convertDtoAndVo(object, ItemRequirePo.class);;
				if (StringUtils.isNotBlank(requirePo.getRequirecontent())){
					list.add(object);
				}
			}
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	  /**
     * 构造 pic对象
     *
     * @param list    pic对象集合
     * @param urls    图片或者文档地址集合
     * @param stem    来源于哪个（当前是日志）
     * @param type    类型( 图片还是文档）
     * @param dailyid 日志id
     * @param userid  用户id
     * @return
     */
    public List<PicRecordPo> createPic(List<PicRecordPo> list, Integer itemid, List<String> urls, String stem, Integer type, Integer dailyid, Integer userid) {
        for (String url : urls) {
            PicRecordPo picRecordPo = new PicRecordPo();
            picRecordPo.setItemid(itemid);
            picRecordPo.setCreateby(userid);
            picRecordPo.setCreatedate(new Date());
            picRecordPo.setFromid(dailyid);
            picRecordPo.setStemfrom(stem);
            picRecordPo.setPictype(type);
            picRecordPo.setPicurl(url);
            picRecordPo.setPicstatus(0);
            list.add(picRecordPo);
        }
        return list;
    }

}
