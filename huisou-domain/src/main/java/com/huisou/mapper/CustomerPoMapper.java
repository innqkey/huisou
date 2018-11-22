package com.huisou.mapper;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.CustomerPo;
import com.huisou.po.UniversalPo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface CustomerPoMapper extends MyMapper<CustomerPo> {

	List<UniversalPo> findAllInfo(@Param("tradetype")String tradetype);

	List<UniversalPo> findInfoUsername(@Param("saleid")Integer saleid,@Param("tradetype")String tradetype);

	void batchChangeCustomerStatus(@Param("custids")ArrayList<String> custids,@Param("custStatus")String custStatus);

	List<UniversalPo> searchByConditionAndUsername(@Param("searchType")String searchType,
			@Param("value")String value, @Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("custstatus")String custstatus,
			@Param("searchTable")String searchTable,@Param("saleid") Integer saleid, @Param("tradetype") String tradetype,@Param("isrenew")String isrenew, @Param("date")Integer date,@Param("phonetype")String phonetype,@Param("salename")String salename,@Param("salenull")String salenull,@Param("overdue")String overdue,@Param("itemType") String itemType,@Param("contractStatus") String contractStatus,@Param("sortCondition") String sortCondition,@Param("pendingflag") String pendingflag);

	void changCustomerStatus(@Param("custid")Integer custid,@Param("status") Integer status);

	UniversalPo findDetailedInfoByitemid(Integer itemid);

	List<UniversalPo> findRenewCustomerAll(@Param("date")Integer date,@Param("beginDate")String beginDate,@Param("endDate")String endDate);

	List<UniversalPo> findRenewCustomerBysaid(@Param("saleid")Integer saleid,@Param("date")Integer date,@Param("beginDate")String beginDate,@Param("endDate")String endDate);

	List<UniversalPo> findInfosByList(@Param("itemids")ArrayList<String> itemids);

	List<UniversalPo> searchCustomerViaCondition(@Param("condition")String condition,@Param("content")String content,@Param("custstatus")String custstatus, @Param("saleid")Integer saleid);


	void insertCustomerReturnId(@Param("customerPo")CustomerPo customerPo);

	Integer findCountBySaleid(@Param("saleid")Integer saleid,@Param("timeSpan")Integer timeSpan, @Param("beginDate")String beginDate,@Param("endDate")String endDate);

	Integer selectcountByItemTradetype(@Param("saleid")Integer saleid,@Param("tradetype")String tradetype, @Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("pendingflag")String pendingflag);

	Integer countRelatecustid();

	List<UniversalPo> findRenewCustomerAll2(@Param("searchValue")String searchValue,@Param("itemType")String itemType,@Param("date")Integer date,@Param("beginDate")String beginDate,@Param("endDate")String endDate);

	List<UniversalPo> findRenewCustomerByuserid(@Param("searchValue")String searchValue,@Param("itemType")String itemType,@Param("userid")Integer userid, @Param("date")Integer date,@Param("beginDate")String beginDate,@Param("endDate")String endDate);

	CustomerPo selectByCustid(@Param("custid")Integer custid);
}