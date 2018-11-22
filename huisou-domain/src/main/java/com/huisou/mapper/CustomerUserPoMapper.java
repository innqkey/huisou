package com.huisou.mapper;


import java.util.Date;
import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.CustomerUserPo;
import com.huisou.vo.CustomerUserVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午5:51:28 
* 类说明 
*/
public interface CustomerUserPoMapper extends MyMapper<CustomerUserPo>{


	List<CustomerUserVo> selectCustomerUserPo(@Param(value = "contact")String contact, @Param(value = "phone")String phone, @Param(value = "custuserid")Integer custuserid);

	CustomerUserPo selectCustomerUser(@Param(value = "custuserid")Integer custuserid);

	void updateCustomerUser(@Param(value = "custuserid")Integer custuserid, @Param(value = "phone")String phone, @Param(value = "password")String password);

	void add(@Param(value = "custid")Integer custid, @Param(value = "contact")String contact, @Param(value = "phone")String phone, @Param(value = "password")String password, @Param(value = "createdate")Date createdate);

	void updateStatus(@Param("status")Integer status, @Param("custuserid")Integer custuserid);

	CustomerUserPo findByCustid(@Param("custid")Integer custid);

	CustomerUserPo selectByContactAndPhone(@Param("phone")String phone);

	List<CustomerUserVo> selectCustomerUserPoBySaleid(@Param("contact")String contact, @Param("phone")String phone, @Param("custuserid")Integer custuserid, @Param("saleid")Integer saleid);


}
