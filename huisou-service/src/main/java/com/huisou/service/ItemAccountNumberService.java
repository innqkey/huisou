package com.huisou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.ItemAccountNumberPo;
import com.huisou.vo.ItemAccountNumberListVo;
import com.huisou.vo.ItemAccountNumberVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequestAccountVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 下午3:11:27 
* 类说明 
*/
public interface ItemAccountNumberService {

	void add(Integer custuserid, Integer itemid, Integer urlid, String url, String username, String password, String comments, Date createdate,
			String contact,String phone, String itemname,Integer itemtype );

	List<ItemAccountNumberPo> findByItemid(Integer itemid);

	void update(Integer accountid, String username, String password, String url, String comments);

	PageInfo<ItemAccountNumberVo> search(String contact, String phone, String itemname,Integer userid,boolean leader, PageTemp pageTemp);

	List<ItemAccountNumberListVo> findAccountListByItemid(Integer custuserid);

	PageInfo<ItemAccountNumberVo> findAccountListByCusterid(Integer custuserid, PageTemp pageTemp);

	ItemAccountNumberPo findByAccountid(Integer accountid);

	String addAccountList(List<RequestAccountVo> requestAccountVos, ArrayList<Integer> itemids);


}
