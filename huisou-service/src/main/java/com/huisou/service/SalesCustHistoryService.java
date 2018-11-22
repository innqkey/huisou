package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SaleCustHistUserVo;
import com.huisou.vo.SaleCustHistoryVo;

public interface SalesCustHistoryService {

	void save(SalesCustHistoryPo salesCustHistoryPo);

	List<SalesCustHistoryPo> findHistoryBypara(SalesCustHistoryPo salesCustHistoryPo);

	List<SaleCustHistoryVo> findCustomerHistoryBycustid (Integer custid)throws Exception;
	
	PageInfo<SaleCustHistUserVo> findCustomerHistUserList( PageTemp pageT, String searchValue, String beginDate, String endDate);
}
