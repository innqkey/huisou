package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.constant.DictConConstant;
import com.huisou.po.ItemsPo;
import com.huisou.po.NoticePo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.CustomerService;
import com.huisou.service.ItemService;
import com.huisou.service.NoticeService;
import com.huisou.service.SalesmanService;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VisitRecrdVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月20日 上午10:09:10 
* 类说明 :回访记录
*/
@RestController
@RequestMapping(value="/visitRecord")
public class VisitRecordController extends BaseController{
	@Autowired
	VisitRecordService visitRecordService;
	
	@Autowired
	SalesmanService salesmanService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CustomerService customerService;
	/**
	 * 售后管理页面
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			SalesManPo salesManPo= super.getSale(request);
		PageInfo pageInfo = visitRecordService.findItemsAndVisit(pageTemp,salesManPo,super.getLeader(request));
		if(pageInfo==null){
			return null;
		}
		List list = pageInfo.getList();
		List<VisitRecrdVo> volist = ConvertUtil.convertDtoAndVo(list, VisitRecrdVo.class);
		PageInfo<VisitRecrdVo> result = (PageInfo<VisitRecrdVo>) ConvertUtil.convertDtoAndVo(pageInfo, PageInfo.class);
		result.setList(volist);
		return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查看该项目的所有日志
	 */
	@RequestMapping(value="/lisitVisit")
	public String lisitVisit(HttpServletRequest request, PageTemp pageTemp, String itemid){
		try {
			if(itemid==null){
				return ResUtils.errRes(ContextConstant.PARAM_NULL, "参数项目id不能为空");
			}
			PageInfo pageinfo = visitRecordService.findVisitByItemid(pageTemp, itemid);
			if(pageinfo==null){
				return null;
			}
			List list = pageinfo.getList();
			List<VisitRecrdVo> visitRecrdVoList = ConvertUtil.convertDtoAndVo(list, VisitRecrdVo.class);
			PageInfo<VisitRecrdVo> result = (PageInfo<VisitRecrdVo>) ConvertUtil.convertDtoAndVo(pageinfo, PageInfo.class);
			result.setList(visitRecrdVoList);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 按条件查询
	 */
	@RequestMapping(value="/search")
	public String search(HttpServletRequest request, PageTemp pageTemp){
		try {
			SalesManPo salesManPo = super.getSale(request);
			UserPo po = super.getUserPo(request);
			String  searchType = request.getParameter("searchType");
			String  searchTypeValue = request.getParameter("searchTypeValue");
			String  startTime = request.getParameter("startTime");
			String  endTime = request.getParameter("endTime");
			String replystatus = request.getParameter("replystatus");
			if(replystatus==null||StringUtils.isBlank(replystatus)){
				replystatus=null;
			}
			
			int userId = getUserId(request);
			String  salename = null;
			String  salephone = null;
			String  customerName = null;
			String 	customerPhone = null;
			String  itemName = null;
				if(ContextConstant.SEARCH_SALENAME.equals(searchType)){
					salename = searchTypeValue;
					}else if(ContextConstant.SEARCH_SALEPHONE.equals(searchType)){
						salephone = searchTypeValue;
					}else if(ContextConstant.SEARCH_PHONE.equals(searchType)){
						customerPhone = searchTypeValue;
					}else if(ContextConstant.SEARCH_CUSTNAME.equals(searchType)){
						customerName = searchTypeValue;
					}else if(ContextConstant.SEARCH_TIEMNAME.equals(searchType)){
						itemName = searchTypeValue;
					}
		boolean leader = super.getLeader(request);
			PageInfo pageinfo = visitRecordService.findItemsAndVisitByParam
					(salename, salephone, customerPhone, customerName, itemName, startTime, endTime, pageTemp,salesManPo, leader,replystatus,userId);
			if(pageinfo==null){
				return null;
			}
			List list = pageinfo.getList();
			List<VisitRecrdVo> visitRecrdVoList = ConvertUtil.convertDtoAndVo(list, VisitRecrdVo.class);
			PageInfo<VisitRecrdVo> result = (PageInfo<VisitRecrdVo>) ConvertUtil.convertDtoAndVo(pageinfo, PageInfo.class);
			result.setList(visitRecrdVoList);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 修改最近的一条拜访记录
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateVisit(HttpServletRequest request){
		try {
			String visitid = request.getParameter("visitid");
			String visitway = request.getParameter("visitway");
			String createdate = request.getParameter("createdate");
			String visitremarks = request.getParameter("visitremarks");
			String replycontext = request.getParameter("replycontext");
			VisitRecordPo visitRecordPo = new VisitRecordPo(); 
			if(StringUtils.isEmpty(visitid)){
				return ResUtils.errRes(ContextConstant.PARAM_NULL, "参数visitid不能为空");
			}
			visitRecordPo.setVisitid(Integer.parseInt(visitid));
			visitRecordPo.setVisitway(Integer.parseInt(visitway));
			//表示已经回复了
			visitRecordPo.setReplycontext(replycontext);
			visitRecordPo.setReplystatus(2);
			if(!StringUtils.isEmpty(createdate)){
				visitRecordPo.setCreatedate(DateUtils.format(createdate,DateUtils.Y_M_D));
				
			}
			
			visitRecordPo.setVisitremarks(visitremarks);
			/*VisitRecordPo visitRecordPo = new VisitRecordPo(); 
			Map<String, String[]> ParameterMap = request.getParameterMap();
			Set<Entry<String, String[]>> entrySet = ParameterMap.entrySet();
			for (Entry<String, String[]> entry : entrySet) {
				System.out.println("key"+entry.getKey()+"value"+entry.getValue());
			}
			registerConvert();
			BeanUtils.copyProperties(visitRecordPo, ParameterMap);
			removeRegister();*/
			
			visitRecordPo.setVisitwayname(DictConConstant.getDicName("visitway", visitRecordPo.getVisitway()));
			visitRecordPo.setUpdateby(super.getUserId(request));
			visitRecordService.updateVisit(visitRecordPo);
			//业务员回复后添加消息提醒
			if(replycontext !=null && replycontext.trim()!=""){
				NoticePo noticePo = new NoticePo();
				VisitRecrdVo recordVo = visitRecordService.findVisitByVisitid(visitid);
				Integer itemid = recordVo.getItemid();
				ItemsPo itemsPo = itemService.findItemPoById(itemid);
				noticePo.setItemid(itemid);
				noticePo.setItemname(itemsPo.getItemname());
				noticePo.setCreateby(super.getUserId(request));
				noticePo.setNoticetype(ContextConstant.NOTICETYPE_SALE_REPLY);
				noticePo.setNoticeacceptype(ContextConstant.NOTICEACCEPTYPE_SERVICE);
				noticePo.setNoticemessage(replycontext);
				noticeService.insert(noticePo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 添加一条回访记录
	 */
	@RequestMapping(value="/addVisit" , method = RequestMethod.POST)
	public String addVisit(HttpServletRequest request){
		try {
			String itemid = request.getParameter("itemid");
			String visitway = request.getParameter("visitway");
			String createdate = request.getParameter("createdate");
			String visitremarks = request.getParameter("visitremarks");
			String replybox = request.getParameter("replybox");
		
			VisitRecordPo visitRecordPo = new VisitRecordPo();
			if(StringUtils.isEmpty(itemid) || Integer.parseInt(itemid) < 0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			if(StringUtils.isEmpty(createdate)){
				visitRecordPo.setCreatedate(new Date());
			}else{
				visitRecordPo.setCreatedate(new Date());
			}
			
			visitRecordPo.setVisitway(Integer.parseInt(visitway));
			visitRecordPo.setVisitremarks(visitremarks);
			if(replybox!=null&&StringUtils.isNotBlank(replybox)&&replybox.equals("true")){
				System.out.println(replybox);
				visitRecordPo.setReplystatus(1);
			}
			ItemCustSaleVo itemCustSaleVo = customerService.findVoByitemid(Integer.parseInt(itemid));
			if(itemCustSaleVo==null){
				return ResUtils.errRes("102", "请求参数错误");
			}
			visitRecordPo.setVisitwayname(DictConConstant.getDicName("Visitway", visitRecordPo.getVisitway()));
			visitRecordPo.setCustid(itemCustSaleVo.getCustid());
			visitRecordPo.setItemid(itemCustSaleVo.getItemid());
			visitRecordPo.setSaleid(itemCustSaleVo.getSaleid());
			visitRecordPo.setSalename(itemCustSaleVo.getSalename());
			visitRecordPo.setCreateby(super.getUserId(request));
			visitRecordService.add(visitRecordPo);
			
			NoticePo noticePo = new NoticePo();
			noticePo.setItemid(itemCustSaleVo.getItemid());
			noticePo.setItemname(itemCustSaleVo.getItemname());
			noticePo.setNoticetype(ContextConstant.NOTICETYPE_NEED_SALE_REPLY);
			noticePo.setNoticemessage(visitremarks);
			noticePo.setNoticeacceptype(ContextConstant.NOTICEACCEPTYPE_SALE);
			noticePo.setCreateby(super.getUserId(request));
			noticeService.insert(noticePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 一键回访
	 */
	@RequestMapping(value="/addVisitList")
	public String addVisitList(HttpServletRequest request, @RequestParam("itemids[]")List<Integer> itemids){
		try {
			if(itemids.isEmpty() || itemids==null){
				return ResUtils.errRes("102", "请求参数错误");
			}
			
			List<VisitRecordPo> list = new ArrayList<VisitRecordPo>();
			Integer createby = super.getUserId(request);
			Date date = new Date();
			String visitremarks="";
			for (Integer itemid : itemids) {
				VisitRecordPo visitRecordPo = new VisitRecordPo();
				ItemCustSaleVo vo = customerService.findVoByitemid(itemid);
				if(vo==null){
					return ResUtils.errRes("102", "请求参数错误");
				}
				visitRecordPo.setItemid(vo.getItemid());
				visitRecordPo.setSaleid(vo.getSaleid());
				visitRecordPo.setCustid(vo.getCustid());
				visitRecordPo.setSalename(vo.getSalename());
				visitRecordPo.setVisitway(1);
				visitRecordPo.setVisitwayname(DictConConstant.getDicName("Visitway",visitRecordPo.getVisitway()));
				visitRecordPo.setCreateby(createby);
				visitRecordPo.setVisitremarks(visitremarks);
				visitRecordPo.setCreatedate(date);
				list.add(visitRecordPo);
			}
			visitRecordService.addVisitList(list);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 根据客户ID查询回访记录
	 */
	@RequestMapping(value="/findByCustid")
	public String findByCustid(HttpServletRequest request, String custid, PageTemp pageTemp){
		try {
			if(custid==null){
				return ResUtils.errRes(ContextConstant.PARAM_NULL, "参数custid不能为空");
			}
			PageInfo pageinfo = visitRecordService.findVisitByCustid(pageTemp, custid);
			List list = pageinfo.getList();
			List<VisitRecrdVo> visitRecrdVoList = ConvertUtil.convertDtoAndVo(list, VisitRecrdVo.class);
			PageInfo<VisitRecrdVo> result = (PageInfo<VisitRecrdVo>) ConvertUtil.convertDtoAndVo(pageinfo, PageInfo.class);
			result.setList(visitRecrdVoList);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据visitid查询visitid和客户姓名
	 * @param request
	 * @param visitid
	 * @return
	 */
	@RequestMapping(value="/findByVisitid")
	public String findByVisitid(HttpServletRequest request, String visitid){
		if(visitid==null){
			return ResUtils.errRes(ContextConstant.PARAM_NULL,"参数visitid不能为空");
		}
		VisitRecrdVo visitRecordVo = visitRecordService.findVisitByVisitid(visitid);
		return ResUtils.okRes(visitRecordVo);
	}
}
