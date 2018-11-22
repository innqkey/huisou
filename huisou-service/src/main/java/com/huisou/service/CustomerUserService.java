package com.huisou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CustomerPo;
import com.huisou.po.CustomerUserPo;
import com.huisou.vo.CustomerUserVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月16日 上午9:00:04 
* 类说明 
*/
public interface CustomerUserService {

	PageInfo<CustomerUserVo> findCustomerUserByContactAndPhone(String contact, String phone, Integer custuserid,Integer userid,boolean leader,PageTemp pageTemp);

	CustomerUserPo select(Integer custuserid);

	void updateCustomerUser(Integer custuserid, String phone, String password);

	void addCustomerUser(Integer custid, String contact, String phone, String password,Date createdate);
	/**
	 * 根据一个实体用户对象
	 * @param custUserPo
	 * @return
	 */
	CustomerUserPo findOne(CustomerUserPo custUserPo);

	/**
	 * 更新一个用户
	 * @param custUserPo
	 * @param return
	 */
	void update(CustomerUserPo po);

	/**
	 * 客户id 禁用/启用
	 * @param status
	 * @param custuserid
	 * @return
	 */
	void updateStatus(Integer status, Integer custuserid);

	List<CustomerPo> addList(ArrayList<Integer> custids);

}
