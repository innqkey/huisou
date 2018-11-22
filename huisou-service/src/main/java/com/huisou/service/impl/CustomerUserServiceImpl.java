package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.CustomerPoMapper;
import com.huisou.mapper.CustomerUserPoMapper;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.CustomerPo;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.UserPo;
import com.huisou.service.CustomerUserService;
import com.huisou.vo.CustomerUserVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 上午9:00:58 
* 类说明 
*/
@Service
public class CustomerUserServiceImpl implements CustomerUserService{

	@Autowired
	private CustomerUserPoMapper customerUserPoMapper;
	
	@Autowired
	private CustomerPoMapper customerPoMapper;
	
	@Autowired
	private UserPoMapper userPoMapper;
	
	@Override
	public PageInfo<CustomerUserVo> findCustomerUserByContactAndPhone(String contact, String phone,Integer custuserid,Integer userid,boolean leader, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		UserPo userPo = userPoMapper.selectByPrimaryKey(userid);
		Integer saleid = userPo.getKeyid();
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<CustomerUserVo> list = new ArrayList<>();
		if(leader){
			list = customerUserPoMapper.selectCustomerUserPoBySaleid(contact,phone,custuserid,saleid);
		}else{
			list = customerUserPoMapper.selectCustomerUserPo(contact,phone,custuserid);
		}
		
		PageInfo<CustomerUserVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public CustomerUserPo select(Integer custuserid) {
		// TODO Auto-generated method stub
		CustomerUserPo customerUserPo = customerUserPoMapper.selectCustomerUser(custuserid);
		return customerUserPo;
	}

	@Override
	public void updateCustomerUser(Integer custuserid, String phone, String password) {
		// TODO Auto-generated method stub
		customerUserPoMapper.updateCustomerUser(custuserid,phone,password);
	}

	@Override
	public void addCustomerUser(Integer custid, String contact, String phone, String password, Date createdate) {
		// TODO Auto-generated method stub
		customerUserPoMapper.add(custid,contact,phone,password,createdate);
	}
	
	@Override
	public CustomerUserPo findOne(CustomerUserPo custUserPo) {
		return customerUserPoMapper.selectOne(custUserPo);
	}

	@Override
	public void update(CustomerUserPo po) {
		CustomerUserPo userPo = new CustomerUserPo();
		userPo.setCustuserid(po.getCustuserid());
		CustomerUserPo selectOne = customerUserPoMapper.selectOne(userPo);
		selectOne.setUpdateby(po.getUpdateby());
		selectOne.setUpdatedate(new Date());
//		selectOne.setPassword(po.getPassword());
		selectOne.setPassword(MD5Util.md5Encode(MD5Util.md5Encode(po.getPassword())));
		customerUserPoMapper.updateByPrimaryKeySelective(selectOne);
	}

	@Override
	public void updateStatus(Integer status, Integer custuserid) {
		customerUserPoMapper.updateStatus(status,custuserid);
	}

	@Override
	public List<CustomerPo> addList(ArrayList<Integer> custids) {
		// TODO Auto-generated method stub
		List<CustomerPo> list = new ArrayList<>();
		for (Integer custid : custids) {
			CustomerPo customerPo = customerPoMapper.selectByCustid(custid);
			CustomerUserPo customerUserPo = customerUserPoMapper.selectByContactAndPhone(customerPo.getPhone());
			if(customerUserPo!=null){
				list.add(customerPo);
			}else{
				customerUserPoMapper.add(custid, customerPo.getContact(), customerPo.getPhone(), MD5Util.md5Encode(MD5Util.md5Encode("123456")), new Date());

			}
		}
		return list;
	}

}
