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
import com.common.ReflectionUtil;
import com.common.ResUtils;
import com.huisou.constant.DictConConstant;
import com.huisou.mapper.NoticeReadPoMapper;
import com.huisou.po.NoticePo;
import com.huisou.po.NoticeReadPo;
import com.huisou.po.ServiceItemPo;
import com.huisou.po.UserPo;
import com.huisou.po.UserRolePo;
import com.huisou.service.ItemService;
import com.huisou.service.NoticeReadService;
import com.huisou.service.NoticeService;
import com.huisou.service.ServiceItemService;
import com.huisou.service.UserRoleService;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.NoticeVo;

/** 
* @author qinkai 
* @date 2017年11月6日
*/

@RestController
@RequestMapping(value = "/noticeRead")
public class NoticeReadController extends BaseController{
	
	@Autowired
	private NoticeReadService noticeReadService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private ServiceItemService serviceItemService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private VisitRecordService visitRecordService;
	
	/*
	 * 处理一条消息提醒
	 * @Param noticeid
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request){
		try {
			if (StringUtils.isNotBlank(request.getParameter("noticeid"))){
				int noid = Integer.valueOf(request.getParameter("noticeid"));
				NoticeReadPo noticeReadPo = new NoticeReadPo();
				noticeReadPo.setNoticeid(noid);
				noticeReadPo.setReadby(super.getUserId(request));
				noticeReadService.saveNotice(noticeReadPo);
				return ResUtils.okRes();
			} else {
				return ResUtils.errRes("102", "消息未处理");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数错误");
		}
	}
	
	/*
	 * 根据userid 查询 事件通知信息
	 * @param userid
	 */
	@RequestMapping(value = "/findNotices")
	public String findNotices(HttpServletRequest request){
		try {
			if (super.getCustomerUserId(request) > 0){
				return null;
			}
			List<NoticePo> poList = null;
			List<NoticeVo> voList = new ArrayList<>();
			if(super.getSaleId(request) > 0){
				poList = noticeService.findAllNotices(2);
				if (super.getLeader(request)){
					//普通业务员
					if (poList!= null && poList.size() > 0){
						Iterator<NoticePo>iterator = poList.iterator();
						while (iterator.hasNext()) {
							NoticePo po = iterator.next();
							int itemid = po.getItemid();
							int saleid = super.getSaleId(request);
							if (!itemService.findOne(itemid, saleid)){
								iterator.remove();
							}
						}						
					}		
				}
				
			}else{
				int userid = super.getUserId(request);
				UserRolePo findOne = userRoleService.findOne(userid);
				if (null == findOne){
					return null;
				}
				if (findOne.getRoleid().equals(777) || findOne.getRoleid().equals(1001)){
					poList = noticeService.findAllNotices(1);
					//普通客服
					if (poList!= null && poList.size() > 0 && findOne.getRoleid().equals(777)){
						Iterator<NoticePo>iterator = poList.iterator();
						while (iterator.hasNext()) {
							NoticePo po = iterator.next();
							int itemid = po.getItemid();
							if (!serviceItemService.findOne(itemid, userid)){
								iterator.remove();
							}
						}						
					}
				}
			}
				
			// 过滤处理过的消息
			if (poList!= null && poList.size() > 0){
				Iterator<NoticePo>iterator = poList.iterator();
				while (iterator.hasNext()) {
					NoticePo po = iterator.next();
					int noticeid = po.getNoticeid();
					int readby = super.getUserId(request);
					if (noticeReadService.findOne(noticeid, readby)){
						iterator.remove();
					} else {
						NoticeVo noticeVo = new NoticeVo();
						ReflectionUtil.copyPorperties(noticeVo, po);
						noticeVo.setNoticename(DictConConstant.getDicName("NoticeType", po.getNoticetype()));
//						visitRecordService.findOne(po.getItemid(),);
//						noticeVo.setVisitid();
						voList.add(noticeVo);
					}
				}						
			}
			return ResUtils.okRes(voList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数错误");
		}
	}
	
	/*
	 * 根据userid 查找角色
	 * @param userid
	 */
	@RequestMapping(value = "/findRole")
	public String findRole(HttpServletRequest request, String userid){
		try {
			if (StringUtils.isNotBlank(userid) && Integer.valueOf(userid) > 0){
				UserRolePo findOne = userRoleService.findOne(Integer.valueOf(userid));
				return ResUtils.okRes(findOne.getRoleid());
			} else {
				return ResUtils.errRes("102", "请求参数错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
