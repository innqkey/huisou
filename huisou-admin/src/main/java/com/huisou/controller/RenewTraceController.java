package com.huisou.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.CustomerPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.RenewMarkPo;
import com.huisou.po.RenewTracePo;
import com.huisou.service.CustomerService;
import com.huisou.service.ItemService;
import com.huisou.service.RenewMarkService;
import com.huisou.service.RenewTraceService;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.RenewTraceVo;

/**
 * 用于项目续费的增删该查等内容
 * @author Administrator
 * @Date 2017年11月7日 下午8:14:57
 *
 */
@RestController
@RequestMapping(value="/itemRenew")
public class RenewTraceController extends BaseController{
	@Autowired
	private RenewTraceService renewTraceService;
	
	@Autowired
	private RenewMarkService renewMarkService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemService itemService;
	/**
	 * 保存内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request){
		RenewTracePo renewTracePo = new RenewTracePo();
		RenewMarkPo renewMarkPo = new RenewMarkPo();
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		String itemid = request.getParameter("itemid");
		ItemsPo itemsPo = itemService.findItemPoById(Integer.parseInt(itemid));
		CustomerPo customerPo =  customerService.findCustomerPoByCustid(itemsPo.getCustid());
		ItemCustSaleVo custSaleVo = customerService.findVoByitemid(Integer.parseInt(itemid));
		renewMarkPo.setSalename(custSaleVo.getSalename());
		renewMarkPo.setItemtype(itemsPo.getItemtype());
		renewMarkPo.setPhone(customerPo.getPhone());
		renewMarkPo.setCreateby(super.getUserId(request));
		renewMarkPo.setCreatedate(new Date());
		try {
			registerConvert();
			BeanUtils.copyProperties(renewTracePo, parameterMap);
			BeanUtils.copyProperties(renewMarkPo, parameterMap);
			removeRegister();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		if (renewTracePo == null || renewTracePo.getItemid() == null) {
			return ResUtils.errRes("404", "参数不能为空");
		}
		
		int userId = getUserId(request);
		
		renewTracePo.setCreateby(Integer.valueOf(userId));
		renewTracePo.setCreatedate(new Date());
		renewTraceService.save(renewTracePo);
		renewMarkService.save(renewMarkPo);
		String custrenewstatus = request.getParameter("custrenewstatus");
		if(StringUtils.isNotBlank(custrenewstatus)&&"1".equals(custrenewstatus)){
			String renewitembegindate = request.getParameter("renewitembegindate");
			String renewitemenddate = request.getParameter("renewitemenddate");
			if(StringUtils.isNotBlank(renewitembegindate)&&StringUtils.isNotBlank(renewitemenddate)){
				ItemsPo itemPo = new ItemsPo();
				itemPo.setItemid(renewTracePo.getItemid());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					itemPo.setItembegindate(sdf.parse(renewitembegindate));
					itemPo.setItemenddate(sdf.parse(renewitemenddate));
					itemService.updateItemByItemid(itemPo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ResUtils.okRes();
		
	}
	
	/**
	 * 根据项目的id查询续费的记录
	 * @param itemid 项目的id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getTraceRecord")
	public String getTraceRecord(Integer itemid, HttpServletRequest request){
		if (itemid == null) {
			return ResUtils.errRes("405", "itemid不能为空");
		}
		List<RenewTraceVo> recoredList = renewTraceService.findRenewTraceByItemid(itemid);
		if (recoredList != null && recoredList.size() < 1) {
			return null;
		}
		return ResUtils.okRes(recoredList);
		
	}
	
}
