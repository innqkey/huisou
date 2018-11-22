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

import com.common.JacksonUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.ItemAccountNumberPo;
import com.huisou.service.ItemAccountNumberService;
import com.huisou.vo.ItemAccountNumberListVo;
import com.huisou.vo.ItemAccountNumberVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequestAccountVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 下午3:08:44 
* 类说明 
*/
@RestController
@RequestMapping(value = "/itemAccountNumberController")
public class ItemAccountNumberController extends BaseController{
	
	@Autowired
	private ItemAccountNumberService itemAccountNumberService;
	
	/**
	 * 添加客户的项目的帐号密码和网址
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addItemAccountNumber")
	public String addItemAccountNumber(HttpServletRequest request){
		try {
			String custuserid = request.getParameter("custuserid");
			String itemid = request.getParameter("itemid");
			String urlid = request.getParameter("urlid");
			String url = request.getParameter("url");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String comments = request.getParameter("comments");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String itemname = request.getParameter("itemname");
			String itemtype = request.getParameter("itemtype");
			if(StringUtils.isBlank(custuserid) || StringUtils.isBlank(itemid) || StringUtils.isBlank(urlid) || StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(itemtype) || StringUtils.isBlank(phone)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Date createdate = new Date();
			itemAccountNumberService.add(Integer.parseInt(custuserid),Integer.parseInt(itemid),Integer.parseInt(urlid),url,username,password,comments,createdate,contact,phone,itemname,Integer.parseInt(itemtype));
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据项目id查询帐号密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectItemAccountNumber")
	public String selectItemAccountNumber(HttpServletRequest request){
		try {
			String itemid = request.getParameter("itemid");
			if(StringUtils.isBlank(itemid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<ItemAccountNumberPo> list = itemAccountNumberService.findByItemid(Integer.parseInt(itemid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update")
	public String update(HttpServletRequest request){
		try {
			String accountid = request.getParameter("accountid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String url = request.getParameter("url");
			String comments = request.getParameter("comments");
			if(StringUtils.isBlank(accountid) || StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			itemAccountNumberService.update(Integer.parseInt(accountid),username,password,url,comments);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 所有的帐号记录展示表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String itemname = request.getParameter("itemname");
			Integer userid = super.getUserId(request);
			boolean leader = super.getLeader(request);
			PageInfo<ItemAccountNumberVo> result = itemAccountNumberService.search(contact,phone,itemname,userid,leader,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据项目id获取ItemAccountNumberPo集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findAccountListByItemid")
	public String findAccountListByItemid(HttpServletRequest request){
		try {
			String custuserid = request.getParameter("custuserid");
			if(StringUtils.isBlank("custuserid")){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<ItemAccountNumberListVo> list = itemAccountNumberService.findAccountListByItemid(Integer.parseInt(custuserid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据Custerid获取所有的帐号记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findAccountListByCusterid")
	public String findAccountListByCusterid(HttpServletRequest request,PageTemp pageTemp){
		try {
			String custuserid = request.getParameter("custuserid"); 
			if(StringUtils.isBlank("custuserid")){
				return ResUtils.errRes("102", "请求参数错误");
			}
			PageInfo<ItemAccountNumberVo>  result = itemAccountNumberService.findAccountListByCusterid(Integer.parseInt(custuserid),pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据Accountid获取ItemAccountNumberPo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findAccountByAccountid")
	public String findAccountByAccountid(HttpServletRequest request){
		try {
			String accountid = request.getParameter("accountid");
			if(StringUtils.isBlank(accountid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			ItemAccountNumberPo accountNumberPo = itemAccountNumberService.findByAccountid(Integer.parseInt(accountid));
			return ResUtils.okRes(accountNumberPo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量添加帐号密码
	 * @param request
	 * @param requestAccountVos
	 * @return
	 */
	@RequestMapping(value="/addAccountList")
	public String addAccountList(HttpServletRequest request,String requestAccountVos,@RequestParam(value="itemids[]") ArrayList<Integer> itemids){
		try {
			if(StringUtils.isBlank(requestAccountVos)){
				return ResUtils.errRes("102", "请求参数错误");
			}if(itemids==null || itemids.size()==0){
				return ResUtils.errRes("106", "不能不选择项目");
			}
			List<RequestAccountVo> list = JacksonUtil.toListObject(requestAccountVos, RequestAccountVo.class);
		   String msg =  itemAccountNumberService.addAccountList(list,itemids);
		   if(!StringUtils.isBlank(msg)){
			   return ResUtils.errRes("108","添加失败,所选项目的已经有  "+ msg+" 的项目类型的帐号");
		   }
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
}
