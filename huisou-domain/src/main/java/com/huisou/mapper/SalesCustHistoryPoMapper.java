package com.huisou.mapper;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.po.UniversalPo;
import com.huisou.vo.SaleCustHistUserVo;

public interface SalesCustHistoryPoMapper extends MyMapper<SalesCustHistoryPo>{

	List<SalesCustHistoryPo> selectByItemId(Integer itemid);

	List<SalesCustHistoryPo> findCustomerHistoryBycustid(Integer custid);

	List<SalesCustHistoryPo> selectHistoryByEndSaleidAndCustid(
			@Param("endsaleid")Integer endsaleid,@Param("custid") Integer custid);
	
	List<SaleCustHistUserVo> findCustomerHistUserList(@Param("searchValue")String searchValue, @Param("beginDate")String beginDate, @Param("endDate")String endDate);
}