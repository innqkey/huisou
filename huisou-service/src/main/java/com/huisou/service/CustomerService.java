package com.huisou.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CustomerPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserPo;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.PageTemp;

public interface CustomerService {

	PageInfo<UniversalPo> findCustomer(Integer pageNum, Integer pageSize, boolean leader, SalesManPo sale, String tradetype) throws Exception;

	PageInfo<UniversalPo> searchByCondition(String searchType, String value,
			String begindate, String endDate, String itemstatus,String tradetype, PageTemp pageTemp, boolean leader, SalesManPo sale,String isrenew, Integer integer,String phonetype,String salename,String salenull,String overdue, String itemType, String contractStatus, String sortCondition, String pendingflag);

	void batchChangeCustomerStatus(ArrayList<String> custids, String custStatus);

	Integer saveInfo(CustomerPo customerPo, ItemsPo itemsPo,
			List<String> iDImageUrl,
			List<String> contractImageUrl,
			List<String> licenseImageUrl,
			List<String> invoiceImageUrl, List<String> itemdocUrl, SalesManPo salesManPo) ;

	void changCustomerStatus(Integer custid, Integer status);

	UniversalPo findDetailedInfo(Integer itemid);

	PageInfo<UniversalPo> findRenewCustomer(boolean leader, SalesManPo sale, PageTemp pageTemp, Integer integer,String beginDate,String endDate);

	List<UniversalPo> findInfos(ArrayList<String> itemids);
    ItemCustSaleVo findVoByitemid(Integer itemid);

	PageInfo<UniversalPo> searchCustomerByCondition(boolean leader, String condition,
			String content, String custstatus, SalesManPo sale, PageTemp pageTemp) throws Exception;

	String customerChangeSalemanBySalePhoneAndCustids(List<Integer> custids, Integer saleid, UserPo userPo);

	String updateInfo(CustomerPo customerPo, ItemsPo itemsPo,
			List<String> iDImageUrl,
			List<String> contractImageUrl,
			List<String> licenseImageUrl,
			List<String> invoiceImageUrl, List<String> itemdocUrl, SalesManPo sale, List<Integer> picid);

	Integer findCountBySaleid(boolean leader,SalesManPo sale, Integer timeSpan,String beginDate,String endDate);

	void updateCustomerOfSaleId(CustomerPo customerPo);

	Integer countByItemTradetype(String tradetype, SalesManPo sale,
			boolean leader,String beginDate,String endDate,String pendingflag);
	
	boolean checkCustByContAndPhone(String contact,String phone);
	
	boolean checkItemByNameAndType(String itemname,Integer itemtype, Integer itemtypedetail);

	PageInfo<UniversalPo> findRenewCustomerRenew(String searchValue, String itemType, Integer userid, boolean leader, SalesManPo sale, PageTemp pageTemp, Integer datecount,
			String beginDate, String endDate);

	CustomerPo findCustomerPoByCustid(Integer custid);
}
