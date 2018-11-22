package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.MD5Util;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.ClickUrlRecordPo;
import com.huisou.po.CustomerPo;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.UserPo;
import com.huisou.service.ClickUrlRecordService;
import com.huisou.service.CustomerLoginRecordSerivce;
import com.huisou.service.CustomerUserService;
import com.huisou.service.ItemAccountNumberService;
import com.huisou.service.ItemService;
import com.huisou.service.UserService;
import com.huisou.vo.ClickUrlRecordVo;
import com.huisou.vo.CustomerAccountVo;
import com.huisou.vo.CustomerUserVo;
import com.huisou.vo.ItemAccountNumberListVo;
import com.huisou.vo.PageTemp;


/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 上午8:58:17 
* 类说明 
*/
@RestController
@RequestMapping(value = "/customerUser")
public class CustomerUserController extends BaseController{

	@Autowired
	private CustomerUserService customerUserService;
	@Autowired
	private CustomerLoginRecordSerivce loginRecordSerivce;
	@Autowired
	private ClickUrlRecordService clickUrlRecordService;
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemAccountNumberService itemAccountNumberService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 帐号管理页面列表首页展示和按条件搜索功能
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value= "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			Integer custuserid = 0;
			if (super.getUserId(request) != 0){
					custuserid = null;
			} else if (super.getCustomerUserId(request) != 0){
				custuserid = super.getCustomerUserId(request);
			}
			Integer userid = super.getUserId(request);
			boolean leader = super.getLeader(request);
			PageInfo<CustomerUserVo> result =  customerUserService.findCustomerUserByContactAndPhone(contact,phone,custuserid,userid,leader,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据客户用户id查询客户用户对象
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/selectOne")
	public String selectOne(HttpServletRequest request){
		try {
			String custuserid = request.getParameter("custuserid");
			if(StringUtils.isBlank(custuserid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CustomerUserPo customerUserPo = customerUserService.select(Integer.parseInt(custuserid));
			return ResUtils.okRes(customerUserPo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改保存客户用户对象
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/updateCustUser")
	public String updateCustUser(HttpServletRequest request){
		try {
			String custuserid = request.getParameter("custuserid");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			if(StringUtils.isBlank(custuserid) || StringUtils.isBlank(phone) || StringUtils.isBlank(password)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			customerUserService.updateCustomerUser(Integer.parseInt(custuserid),phone,MD5Util.md5Encode(MD5Util.md5Encode(password)));
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加客户用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/add")
	public String add(HttpServletRequest request){
		try {
			String custid = request.getParameter("custid");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			Date createdate = new Date();
			if(StringUtils.isBlank(custid) || StringUtils.isBlank(contact) || StringUtils.isBlank(phone) || StringUtils.isBlank(password)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			customerUserService.addCustomerUser(Integer.parseInt(custid),contact,phone,MD5Util.md5Encode(MD5Util.md5Encode(password)),createdate);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 根据custid获取对应的所有项目
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getItemsListByCustid")
	public String getItemsListByCustid(HttpServletRequest request){
		try {
			String  custid = request.getParameter("custid");
			if(StringUtils.isBlank(custid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<ItemsPo> list = itemService.findItemsByCustid(Integer.parseInt(custid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 客户id 禁用/启用
	 * @param status
	 * @param custuserid
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(Integer status,Integer custuserid){
		if (status != 0 || status != 1){
			return ResUtils.errRes("404", "请求参数有误");
		}
		if (custuserid < 0){
			return ResUtils.errRes("404", "请求客户id有误");
		}
		customerUserService.updateStatus(status,custuserid);
		return ResUtils.okRes();
	}
	
	/**
	 * 修改用户登陆密码
	 * @param custuserid
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(Integer custuserid, String password,HttpServletRequest request){
		if (custuserid <= 0 || StringUtils.isBlank(password)){
			return ResUtils.errRes("404", "请求参数有误");
		}
		CustomerUserPo po = new CustomerUserPo();
		po.setCustuserid(custuserid);
		po.setPassword(password);
		if (super.getUserId(request) != 0){
			po.setUpdateby(super.getUserId(request));
		} else if (super.getCustomerUserId(request) != 0){
			po.setUpdateby(super.getCustomerUserId(request));
		}
		customerUserService.update(po);
		return ResUtils.okRes();
	}
	/**
	 * 登陆总次数/登陆时间 排序
	 * @param custuserid
	 * @param orderVal
	 * @param orderFactor
	 * @return
	 */
	@RequestMapping(value = "/custUserOrder")
	public String custUserOrder(String orderFactor, HttpServletRequest request, PageTemp pageTemp){
		if (StringUtils.isBlank(orderFactor)){
			return ResUtils.errRes("404", "请求参数有误");
		}
		Integer custuserid = 0;
		if (super.getUserId(request) != 0){
				custuserid = null;
		} else if (super.getCustomerUserId(request) != 0){
			custuserid = super.getCustomerUserId(request);
		}
		PageInfo<CustomerUserVo> userRecordVos = loginRecordSerivce.findCustList(orderFactor, custuserid, pageTemp);
		return ResUtils.okRes(userRecordVos);
	}
	/**
	 * 客户帐号统计页面
	 * @return
	 */
	@RequestMapping(value = "/accountList")
	public String accountList(HttpServletRequest request){
		Integer custuserid = 0;
		CustomerAccountVo accountVo = new CustomerAccountVo();
		if (super.getUserId(request) != 0){
//			if (super.getUserName(request).equals("admin")){
				custuserid = null;
//			} else {
//				custuserid = super.getUserId(request);
//			}
		} else if (super.getCustomerUserId(request) != 0){
			custuserid = super.getCustomerUserId(request);
		}
		int lastSevenCount = loginRecordSerivce.findLastSevenCount(custuserid);
		int loginCount = loginRecordSerivce.findAllList(custuserid);
		accountVo.setLastSevenCount(lastSevenCount);
		accountVo.setLoginCount(loginCount);
		List<ClickUrlRecordVo> clickUrlVos = clickUrlRecordService.custClickCount(custuserid);
		accountVo.setClickUrlVos(clickUrlVos);
		return ResUtils.okRes(accountVo);
	}
	/**
	 * 客户登陆首页展示
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request){
		Integer custuserid = 0;
		CustomerAccountVo accountVo = new CustomerAccountVo();
		if (super.getUserId(request) != 0){
			custuserid = super.getUserId(request);
		} else if (super.getCustomerUserId(request) != 0){
			custuserid = super.getCustomerUserId(request);
		}
		int lastSevenCount = loginRecordSerivce.findLastSevenCount(custuserid);
		int loginCount = loginRecordSerivce.findAllList(custuserid);
		accountVo.setLastSevenCount(lastSevenCount);
		accountVo.setLastLoginDate(loginRecordSerivce.getLastDate(custuserid));
		accountVo.setLoginCount(loginCount);
		//调用接口
		List<ItemAccountNumberListVo> itemVos = itemAccountNumberService.findAccountListByItemid(custuserid);
		accountVo.setItemsVo(itemVos);
		return ResUtils.okRes(accountVo);
	}
	/**
	 * 客户首页添加url点击次数
	 * @return 
	 */
	@RequestMapping(value = "/addUrlNumber")
	public String addUrlNumber(Integer accountid, Integer custuserid, Integer itemtype,
			Integer itemid, Integer urlid, HttpServletRequest request){
		if (accountid < 0 || custuserid < 0 || itemid < 0 || urlid < 0){
			return ResUtils.errRes("404", "请求参数有误");
		}
		ClickUrlRecordPo clickUrlRecordPo = new ClickUrlRecordPo();
		clickUrlRecordPo.setAccountid(accountid);
		clickUrlRecordPo.setCustuserid(custuserid);
		clickUrlRecordPo.setUrlid(urlid);
		clickUrlRecordPo.setItemid(itemid);
		clickUrlRecordPo.setItemtype(itemtype);
		clickUrlRecordPo.setCreatedate(new Date());
		clickUrlRecordPo.setCreateby(custuserid);
		clickUrlRecordService.insert(clickUrlRecordPo);
		return ResUtils.okRes();
	}
	
	/**
	 * 批量添加默认密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addItemAccountNumberList")
	public String addItemAccountNumberList(HttpServletRequest request,@RequestParam(value="custids[]") ArrayList<Integer> custids){
		try {
			if(custids==null || custids.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<CustomerPo> list = customerUserService.addList(custids);
			
				return ResUtils.okRes(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
