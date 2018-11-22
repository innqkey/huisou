package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.DateUtil;
import com.common.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.CustomerUserLoginRecordPoMapper;
import com.huisou.mapper.CustomerUserPoMapper;
import com.huisou.po.CustomerUserLoginRecordPo;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.EmployeePo;
import com.huisou.po.UserPo;
import com.huisou.service.CustomerLoginRecordSerivce;
import com.huisou.vo.CustomerUserVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月16日
*/
@Service
public class CustomerLoginRecordSerivceImpl implements CustomerLoginRecordSerivce{

	@Autowired
	private CustomerUserPoMapper customerUserPoMapper;
	@Autowired
	private CustomerUserLoginRecordPoMapper custLoginRecordPoMapper;
	
	@Override
	public void addLoginNumber(CustomerUserPo custUserPo) {
		CustomerUserPo selectOne = customerUserPoMapper.selectOne(custUserPo);
		CustomerUserLoginRecordPo po = new CustomerUserLoginRecordPo();
        try {
            if (null != selectOne) {
                BeanUtils.copyProperties(po, selectOne);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (null != po.getCustid() && null != po.getCustuserid()) {
        	custLoginRecordPoMapper.insertSelective(po);
        }
	}

	@Override
	public PageInfo<CustomerUserVo> findCustList(String orderFactor, Integer custuserid,
			PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());	
		List<CustomerUserVo> resultMap = custLoginRecordPoMapper.selectByParas(orderFactor,custuserid);
		return new PageInfo<CustomerUserVo>(resultMap);
	}

	@Override
	public int findLastSevenCount(Integer custuserid) {
		String beginDate = DateUtils.format(DateUtil.addDays(-7), DateUtils.Y_M_D);
		String endDate = DateUtils.format(new Date(), DateUtils.Y_M_D);
		List<CustomerUserLoginRecordPo> poList = custLoginRecordPoMapper.findAll(beginDate,endDate,custuserid);
		if (poList!= null && poList.size() > 0){
			return poList.size();
		}else {
			return 0;
		}
	}

	@Override
	public int findAllList(Integer custuserid) {
		List<CustomerUserLoginRecordPo> pos = custLoginRecordPoMapper.selectAllByParas(custuserid);
		if (pos != null && pos.size() > 0){
			return pos.size();
		} else {
			return 0;
		}
	}

	@Override
	public Date getLastDate(int custuserid) {
		List<CustomerUserLoginRecordPo> pos = custLoginRecordPoMapper.selectAllByParas(custuserid);
		if (pos != null && pos.size() > 0){
			return pos.get(0).getCreatedate();
		} else {
			return null;
		}
	}

}
