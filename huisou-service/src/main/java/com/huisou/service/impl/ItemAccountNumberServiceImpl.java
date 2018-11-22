package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.DictConConstant;
import com.huisou.mapper.CustomerUserPoMapper;
import com.huisou.mapper.ItemAccountNumberPoMapper;
import com.huisou.mapper.ItemsPoMapper;
import com.huisou.mapper.UrlPoMapper;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.ItemAccountNumberPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.UrlPo;
import com.huisou.po.UserPo;
import com.huisou.service.ItemAccountNumberService;
import com.huisou.vo.ItemAccountNumberListVo;
import com.huisou.vo.ItemAccountNumberVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequestAccountVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 下午3:28:51 
* 类说明 
*/
@Service
public class ItemAccountNumberServiceImpl implements ItemAccountNumberService{

	@Autowired
	private ItemAccountNumberPoMapper itemAccountNumberPoMapper;
	
	@Autowired
	private CustomerUserPoMapper customerUserPoMapper;
	
	@Autowired
	private ItemsPoMapper itemsPoMapper;
	
	@Autowired
	private UrlPoMapper urlPoMapper;
	
	@Autowired
	private UserPoMapper userPoMapper;
	
	@Override
	public void add(Integer custuserid, Integer itemid, Integer urlid, String url, String username, String password,
			String comments, Date createdate, String contact, String phone, String itemname, Integer itemtype) {
		// TODO Auto-generated method stub
		itemAccountNumberPoMapper.add(custuserid,itemid,urlid,url,username,password,comments,createdate,contact,phone,itemname,itemtype);
	}

	@Override
	public List<ItemAccountNumberPo> findByItemid(Integer itemid) {
		// TODO Auto-generated method stub
		List<ItemAccountNumberPo> list = itemAccountNumberPoMapper.findByItemid(itemid);
		return list;
	}

	@Override
	public void update(Integer accountid, String username, String password, String url, String comments) {
		// TODO Auto-generated method stub
		itemAccountNumberPoMapper.update(accountid,username,password,url,comments);
	}

	@Override
	public PageInfo<ItemAccountNumberVo> search(String contact, String phone, String itemname,Integer userid,boolean leader,PageTemp pageTemp) {
		// TODO Auto-generated method stub
		List<ItemAccountNumberVo> list = new ArrayList<>();
		UserPo userPo = userPoMapper.selectByPrimaryKey(userid);
		Integer saleid = userPo.getKeyid();
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		if(leader){
			list = itemAccountNumberPoMapper.searchBySaleid(contact,phone,itemname,saleid);
		}else{
			list = itemAccountNumberPoMapper.search(contact,phone,itemname);
		}
		
		for (ItemAccountNumberVo itemAccountNumberVo : list) {
			itemAccountNumberVo.setItemtypename(DictConConstant.getDicName("Itemtype", itemAccountNumberVo.getItemtype()));
			UrlPo urlPo = urlPoMapper.findUrlPoByid(itemAccountNumberVo.getUrlid());
			UserPo po=userPoMapper.findOne(itemAccountNumberVo.getCreateby());
			itemAccountNumberVo.setCreatebyName(po.getPetname());
			itemAccountNumberVo.setAccountypename(urlPo.getAccountypename());
		}
		PageInfo<ItemAccountNumberVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<ItemAccountNumberListVo> findAccountListByItemid(Integer custuserid) {
		// TODO Auto-generated method stub
		List<ItemAccountNumberListVo> list = new ArrayList<>();
		CustomerUserPo customerUserPo = customerUserPoMapper.selectCustomerUser(custuserid);
		if (null == customerUserPo){
			return list;
		}
		List<ItemsPo> itemList = itemsPoMapper.selectItemsPoByCustid(customerUserPo.getCustid());
		if (null != itemList && itemList.size() > 0){
			for (ItemsPo itemsPo : itemList) {
				List<ItemAccountNumberVo> itemAccountNumberPoList =  itemAccountNumberPoMapper.findItemAccountNumberPoList(itemsPo.getItemid());
				for (ItemAccountNumberVo itemAccountNumberVo : itemAccountNumberPoList) {
					itemAccountNumberVo.setAccountypename(urlPoMapper.findUrlPoByid(itemAccountNumberVo.getUrlid()).getAccountypename());
				}
				ItemAccountNumberListVo itemAccountNumberListVo = new ItemAccountNumberListVo();
				itemAccountNumberListVo.setItemid(itemsPo.getItemid());
				itemAccountNumberListVo.setItemname(itemsPo.getItemname());
				itemAccountNumberListVo.setItemtype(itemsPo.getItemtype());
				itemAccountNumberListVo.setItemtypedetail(itemsPo.getItemtypedetail());
				itemAccountNumberListVo.setItemAccountNumberList(itemAccountNumberPoList);
				list.add(itemAccountNumberListVo);
			}
		}
		return list;
	}

	@Override
	public PageInfo<ItemAccountNumberVo> findAccountListByCusterid(Integer custuserid, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ItemAccountNumberVo> list = itemAccountNumberPoMapper.findAccountListByCusterid(custuserid);
		for (ItemAccountNumberVo itemAccountNumberVo : list) {
			itemAccountNumberVo.setItemtypename(DictConConstant.getDicName("Itemtype", itemAccountNumberVo.getItemtype()));
			UrlPo urlPo = urlPoMapper.findUrlPoByid(itemAccountNumberVo.getUrlid());
			itemAccountNumberVo.setAccountypename(urlPo.getAccountypename());
		}
		return new PageInfo<>(list);
	}

	@Override
	public ItemAccountNumberPo findByAccountid(Integer accountid) {
		// TODO Auto-generated method stub
		ItemAccountNumberPo accountNumberPo = itemAccountNumberPoMapper.findByAccountid(accountid);
		return accountNumberPo;
	}

	@Override
	public String addAccountList(List<RequestAccountVo> requestAccountVos, ArrayList<Integer> itemids) {
		// TODO Auto-generated method stub
		String msg = "";
		ItemsPo itemsPo = itemsPoMapper.findItemById(itemids.get(0));
		Date date = new Date();
		CustomerUserPo customerUserPo = customerUserPoMapper.findByCustid(itemsPo.getCustid());
		Integer custuserid = customerUserPo.getCustuserid();
		for (Integer itemid : itemids) {
			ItemsPo itemsPo1 = itemsPoMapper.findItemById(itemid);
			for (RequestAccountVo requestAccountVo : requestAccountVos) {
				String accountypename = requestAccountVo.getAccountypename();
				UrlPo urlPo = urlPoMapper.findUrlPo(accountypename);
				List<ItemAccountNumberPo> itemAccountNumberPo = itemAccountNumberPoMapper.findByItemIdAndUrlId(itemid,urlPo.getUrlid());
				if(itemAccountNumberPo.size()!=0){
					msg = msg+accountypename+" ,";
				}else{
					itemAccountNumberPoMapper.addAccount(custuserid,itemid,urlPo.getUrlid(),requestAccountVo.getUrl(),requestAccountVo.getUsername(),requestAccountVo.getPassword(),requestAccountVo.getComments(),date,customerUserPo.getContact(),customerUserPo.getPhone(),itemsPo1.getItemname(),itemsPo1.getItemtype(),itemsPo1.getSaleid());
				}
			}
		}
		return msg;
	}


}
