package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.common.ConvertUtil;
import com.common.NumUtils;
import com.common.ResUtils;
import com.common.UploadUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.CustomerPoMapper;
import com.huisou.mapper.ItemsPoMapper;
import com.huisou.mapper.PicRecordPoMapper;
import com.huisou.mapper.RegionPoMapper;
import com.huisou.mapper.SalesCustHistoryPoMapper;
import com.huisou.mapper.SalesManPoMapper;
import com.huisou.po.CustomerPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.PicRecordPo;
import com.huisou.po.RegionPo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserPo;
import com.huisou.service.CustomerService;
import com.huisou.vo.CustomerInfoVo;
import com.huisou.vo.CustomerVO;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.PageTemp;

@SuppressWarnings("all")
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerPoMapper customerPoMapper;

	@Value(value = "${image.url}")
	private String imageUrl;
	@Autowired
	private ItemsPoMapper itemsPoMapper;

	@Autowired
	private PicRecordPoMapper picRecordPoMapper;

	@Autowired
	private SalesManPoMapper salesManPoMapper;

	@Autowired
	private SalesCustHistoryPoMapper salesCustHistoryPoMapper;

	@Autowired
	private RegionPoMapper regionPoMapper;
	
	
	public PageInfo<UniversalPo> findCustomer(Integer pageNum,
			Integer pageSize, boolean leader, SalesManPo salesManPo,
			String tradetype) throws Exception {
		List<UniversalPo> list = null;
		// 是否有经理权限
		if (!leader) {
			try {
				PageHelper.startPage(pageNum, pageSize);
				list = customerPoMapper.findAllInfo(tradetype);
			} finally {
				PageHelper.clearPage();
			}
		} else {
			// String username = (String) subject.getPrincipal();
			try {
				PageHelper.startPage(pageNum, pageSize);
				list = customerPoMapper.findInfoUsername(
						salesManPo.getSaleid(), tradetype);
			} finally {
				PageHelper.clearPage();
			}
		}
		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	}

	/**
	 * 搜索表的话 1表示搜索crm_customer 2.表示//crm_items
	 */
	@SuppressWarnings("unused")
	@Override
	public PageInfo<UniversalPo> searchByCondition(String searchType,
			String value, String beginDate, String endDate, String custstatus,
			String tradetype, PageTemp pageTemp, boolean leader,
			SalesManPo salesManPo, String isrenew, Integer date,String phonetype,String salename,String salenull,String overdue,String itemType,String contractStatus,String sortCondition,String pendingflag) {
		String searchTable = null;
		List<UniversalPo> list = null;
		if (StringUtils.isNotBlank(searchType)) {
			// 根据搜索类型判断值
			if (StringUtils.isBlank(tradetype)) {
				if (searchType.equals(ContextConstant.SEARCH_PHONE)) {
					searchTable = "a";
				} else if (searchType.equals(ContextConstant.SEARCH_CUSTNAME)) {
					searchTable = "a";
				} else if (searchType.equals(ContextConstant.SEARCH_ITEMTYPE)) {
					searchTable = "b";
				} else if (searchType.equals(ContextConstant.SEARCH_TIEMNAME)) {
					searchTable = "b";
				}
			}
		}
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		// 判断是否是个人还是经理
		if (!leader) {
			list = customerPoMapper.searchByConditionAndUsername(searchType,
					value, beginDate, endDate, custstatus, searchTable, null,
					tradetype, isrenew, date,phonetype,salename,salenull,overdue,itemType,contractStatus,sortCondition,pendingflag);
		} else {
			list = customerPoMapper.searchByConditionAndUsername(searchType,
					value, beginDate, endDate, custstatus, searchTable,
					salesManPo.getSaleid(), tradetype, isrenew, date,phonetype,null,salenull,overdue,itemType,contractStatus,sortCondition,pendingflag);
		}
		if (list != null && list.size() > 0) {
			for (UniversalPo po : list) {
				String province = getRegionInfo((String) po.get("province"));
				String city = getRegionInfo((String) po.get("city"));
				String area = getRegionInfo((String) po.get("area"));
				po.put("addressInfo",
						province + city + area + po.get("address"));
			}
		}
		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	}

	private String getRegionInfo(String province) {
		if (StringUtils.isNotBlank(province)) {
			RegionPo regionPo = new RegionPo();
			if (StringUtils.isNotBlank(province)&&StringUtils.isNumeric(province)) {
				regionPo.setId(Integer.valueOf(province));
				List<RegionPo> result = regionPoMapper.select(regionPo);
				if (result != null && result.size() > 0) {
					return result.get(0).getName();
				} else {
					return null;
				}
			} else {
				return "";
			}
		}
		return "";

	}

	@Override
	public void batchChangeCustomerStatus(ArrayList<String> custids,
			String custStatus) {
		customerPoMapper.batchChangeCustomerStatus(custids, custStatus);

	}

	// 保存客户和项目的信息
	@Override
	@Transactional
	public Integer saveInfo(CustomerPo customerPo, ItemsPo itemsPo,
			List<String> iDImageUrl, List<String> contractImageUrl,
			List<String> licenseImageUrl, List<String> invoiceImageUrl,
			List<String> itemdocUrl, SalesManPo salesManPo) {
		
			//根据联系人和手机号查询企业信息，如果存在，说明用户重复
			CustomerPo record = new CustomerPo();
			record.setContact(customerPo.getContact());
			record.setPhone(customerPo.getPhone());
			List<CustomerPo> list = customerPoMapper.select(record);
			if(null!=list&&list.size()>0){
				customerPo.setCustid(list.get(0).getCustid());
			}
			customerPo.setSaleid(itemsPo.getSaleid());
			
			// customerPo.setPicid(sdb.toString());
			if (null != customerPo.getCustid() && customerPo.getCustid() > 0) {
				customerPoMapper.updateByPrimaryKeySelective(customerPo);
			} else {
				customerPo.setCreatedate(new Date());
				// 保存
				customerPo.setCreateby(salesManPo.getSaleid());
				customerPoMapper.insertCustomerReturnId(customerPo);
			}
			itemsPo.setCustid(customerPo.getCustid());
			itemsPo.setCreateby(salesManPo.getSaleid());
			itemsPo.setCreatedate(new Date());
			itemsPo.setItemstatus(2);
			itemsPo.setStandby3(NumUtils.getNum());
			itemsPoMapper.insertItemsReturnId(itemsPo);
			Integer itemid = itemsPo.getItemid();
			
			// 保存图片
			List<PicRecordPo> picList = new ArrayList<PicRecordPo>();
			if (iDImageUrl != null && iDImageUrl.size() > 0.5) {
				savePicutre(picList, iDImageUrl, ContextConstant.PIC_IDCARD,
						salesManPo.getSaleid(), itemsPo.getItemid());
			}
			if (contractImageUrl != null && contractImageUrl.size() > 0.5) {
				savePicutre(picList, contractImageUrl,
						ContextConstant.PIC_CONTRACT, salesManPo.getSaleid(),
						itemsPo.getItemid());
			}
			if (licenseImageUrl != null && licenseImageUrl.size() > 0.5) {
				savePicutre(picList, licenseImageUrl,
						ContextConstant.PIC_LICENSE, salesManPo.getSaleid(),
						itemsPo.getItemid());
			}
			if (invoiceImageUrl != null && invoiceImageUrl.size() > 0.5) {
				savePicutre(picList, invoiceImageUrl, ContextConstant.PIC_BILL,
						salesManPo.getSaleid(), itemsPo.getItemid());
			}
			if (itemdocUrl != null && itemdocUrl.size() > 0.5) {
				savePicutre(picList, itemdocUrl, ContextConstant.doc,
						salesManPo.getSaleid(), itemsPo.getItemid());
			}

			if (picList != null && picList.size() > 0.8)
				picRecordPoMapper.insertList(picList);
			// 用来单独存放用户的身份证的图片的地址的，未启用
			/*
			 * StringBuilder sdb = new StringBuilder(); for ( int t = 0; t <
			 * iDcardUrls.size(); t++ ){
			 * if(StringUtils.isNotBlank(iDcardUrls.get(t))){ if ( sdb.length()
			 * > 0){ sdb.append( "," ).append( iDcardUrls.get(t) ); }else{
			 * sdb.append( iDcardUrls.get(t) ); } } }
			 * 
			 * customerPo.setPicid(sdb.toString());
			 */
			salesManPo = null!=salesManPo.getSaleid()?salesManPo:salesManPoMapper.findSaleById(customerPo.getSaleid());
			
			CustomerPo srcCustVo = customerPoMapper.selectByPrimaryKey(customerPo.getCustid());
			if(null!=srcCustVo){
				customerPo.setRelatecustid(srcCustVo.getRelatecustid());
			}
//			Map<String,Integer> resMap = JdbcSystemUtils.insertHsCust(customerPo, salesManPo, itemsPo);
			CustomerPo upCustVo = new CustomerPo();
			upCustVo.setCustid(customerPo.getCustid());
			upCustVo.setRelatecustid(0);
			customerPoMapper.updateByPrimaryKeySelective(upCustVo);
			ItemsPo upItemVo = new ItemsPo();
			upItemVo.setItemid(itemsPo.getItemid());
			upItemVo.setRelateitemid(0);
			itemsPoMapper.updateByPrimaryKeySelective(upItemVo);
			
			return itemid;
		
	}

	private String savePicutre(List<PicRecordPo> picList, List<String> list,
			Integer type, Integer Saleid, Integer itemid) {
		for (String url : list) {
			// 对图片进行处理
			PicRecordPo picRecordPo = new PicRecordPo();
			picRecordPo.setCreatedate(new Date());
			picRecordPo.setCreateby(Saleid);
			picRecordPo.setItemid(itemid);
			picRecordPo.setPicurl(url);
			picRecordPo.setPictype(type);
			picRecordPo.setCreatedate(new Date());
			picRecordPo.setPicstatus(ContextConstant.PIC_STATUS_EXIST);
			picList.add(picRecordPo);
		}
		return null;

	}

	@Override
	public void changCustomerStatus(Integer custid, Integer status) {
		customerPoMapper.changCustomerStatus(custid, status);
	}

	@Override
	public UniversalPo findDetailedInfo(Integer itemid) {
		UniversalPo mixPo = customerPoMapper.findDetailedInfoByitemid(itemid);
		return mixPo;
	}

	@Override
	public PageInfo<UniversalPo> findRenewCustomer(boolean leader,
			SalesManPo sale, PageTemp pageTemp, Integer datecount,String beginDate,String endDate) {
		// 判断是否是经理，经理展示所有的
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<UniversalPo> list = null;
		// 可以选择在mybaits中进行判断，但是为了便于维护，选择到service中。
		if (!leader) {
			list = customerPoMapper.findRenewCustomerAll(datecount,beginDate,endDate);
		} else {
			list = customerPoMapper.findRenewCustomerBysaid(sale.getSaleid(),
					datecount,beginDate,endDate);
		}
		if (list != null && list.size() > 0) {
			for (UniversalPo po : list) {
				String province = getRegionInfo((String) po.get("province"));
				String city = getRegionInfo((String) po.get("city"));
				String area = getRegionInfo((String) po.get("area"));
				po.put("addressInfo",
						province + city + area + po.get("address"));
			}
		}

		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	}

	@Override
	public List<UniversalPo> findInfos(ArrayList<String> itemids) {
		List<UniversalPo> list = customerPoMapper.findInfosByList(itemids);
		if (list != null && list.size() > 0) {
			for (UniversalPo po : list) {
				String province = getRegionInfo((String) po.get("province"));
				String city = getRegionInfo((String) po.get("city"));
				String area = getRegionInfo((String) po.get("area"));
				po.put("addressInfo",
						province + city + area + po.get("address"));
			}
		}
		return list;
	}

	// 根据条件进行搜索
	@Override
	public PageInfo<UniversalPo> searchCustomerByCondition(boolean leader,
			String condition, String content, String custstauts,
			SalesManPo sale, PageTemp pageTemp) throws Exception {
		// 先判断是否是管理员
		List<UniversalPo> list = null;
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		if (!leader) {
			list = customerPoMapper.searchCustomerViaCondition(condition,
					custstauts, content, null);
		} else {
			list = customerPoMapper.searchCustomerViaCondition(condition,
					custstauts, content, sale.getSaleid());
		}
		if (list != null && list.size() > 0) {
			for (UniversalPo po : list) {
				String province = getRegionInfo((String) po.get("province"));
				String city = getRegionInfo((String) po.get("city"));
				String area = getRegionInfo((String) po.get("area"));
				po.put("addressInfo",
						province + city + area + po.get("address"));
			}
		}
		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	}

	@Transactional
	@Override
	public String customerChangeSalemanBySalePhoneAndCustids(
			List<Integer> custids, Integer saleid, UserPo userPo) {
		try {
			SalesManPo salePo = salesManPoMapper.selectByPrimaryKey(saleid);
			for (Integer custid : custids) {
				if (custid != null) {
					CustomerPo customerPo = new CustomerPo();
					BeanUtils.copyProperties(customerPo,
							customerPoMapper.selectByPrimaryKey(custid));
					if (customerPo != null) {

						// 记录customer的变更记录
						SalesCustHistoryPo custHistoryPo = new SalesCustHistoryPo();
						custHistoryPo.setEndsaleid(saleid);
						custHistoryPo.setBeforesaleid(customerPo.getSaleid());
						custHistoryPo.setCustid(custid);
						custHistoryPo.setCreateby(userPo.getUserid());
						salesCustHistoryPoMapper.insertSelective(custHistoryPo);

						// 更新customer的saleid
						CustomerPo cupo = new CustomerPo();
						cupo.setCustid(custid);
						cupo.setSaleid(saleid);
						customerPoMapper.updateByPrimaryKeySelective(cupo);

						// 更具对应的custids，查询对应的项目，然后变更所有的项目的saleid
						ItemsPo itemsPo = new ItemsPo();
						itemsPo.setCustid(custid);
						List<ItemsPo> list = itemsPoMapper.select(itemsPo);
						list = ConvertUtil.convertDtoAndVo(list, ItemsPo.class);
						for (ItemsPo item : list) {
							// 先保存变更 的记录
							SalesCustHistoryPo history = new SalesCustHistoryPo();
							history.setBeforesaleid(item.getSaleid());
							history.setEndsaleid(saleid);
							history.setCustid(custid);
							history.setChangecause("变更客户时，同时变更对应的项目");
							history.setItemid(item.getItemid());
							history.setCreateby(userPo.getUserid());
							salesCustHistoryPoMapper.insertSelective(history);

							// 更新对应的item的id
							ItemsPo po = new ItemsPo();
							po.setItemid(item.getItemid());
							po.setSaleid(saleid);
							itemsPoMapper.updateByPrimaryKeySelective(po);
						}
						
						SalesManPo srcSalePo = salesManPoMapper.selectByPrimaryKey(customerPo.getSaleid());
//						JdbcSystemUtils.changeHsCustApp(srcSalePo, salePo, customerPo);

					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		// 保存变更记录
		return ResUtils.okRes();

	}

	@Override
	public ItemCustSaleVo findVoByitemid(Integer itemid) {
		UniversalPo mixPo = this.findDetailedInfo(itemid);
		if (null == mixPo) {
			return null;
		}
		ItemCustSaleVo vo = null;
		try {
			vo = (ItemCustSaleVo) ConvertUtil.convertDtoAndVo(mixPo,
					ItemCustSaleVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	@Transactional
	public String updateInfo(CustomerPo customerPo, ItemsPo itemsPo,
			List<String> iDImageUrl, List<String> contractImageUrl,
			List<String> licenseImageUrl, List<String> invoiceImageUrl,
			List<String> itemdocUrl, SalesManPo salesManPo, List<Integer> picids) {

		customerPo.setUpdateby(salesManPo.getSaleid());
		customerPoMapper.updateByPrimaryKeySelective(customerPo);
		itemsPo.setUpdateby(salesManPo.getSaleid());
		itemsPo.setUpdatedate(new Date());
		itemsPoMapper.updateByPrimaryKeySelective(itemsPo);
		if (picids != null && picids.size() > 0.5) {
			// 删除所有的对应的图片
			for (Integer pid : picids) {
				PicRecordPo po = new PicRecordPo();
				po.setPicid(pid);
				po.setPicstatus(ContextConstant.PIC_STATUS_DELETE);
				picRecordPoMapper.updateByPrimaryKeySelective(po);
			}
		}

		// 保存图片
		List<PicRecordPo> picList = new ArrayList<PicRecordPo>();
		String error = null;
		if (iDImageUrl != null && iDImageUrl.size() > 0.5) {
			savePicutre(picList, iDImageUrl, ContextConstant.PIC_IDCARD,
					salesManPo.getSaleid(), itemsPo.getItemid());
		}

		if (contractImageUrl != null && contractImageUrl.size() > 0.5) {
			savePicutre(picList, contractImageUrl,
					ContextConstant.PIC_CONTRACT, salesManPo.getSaleid(),
					itemsPo.getItemid());
		}
		if (licenseImageUrl != null && licenseImageUrl.size() > 0.5) {
			savePicutre(picList, licenseImageUrl, ContextConstant.PIC_LICENSE,
					salesManPo.getSaleid(), itemsPo.getItemid());
		}

		if (invoiceImageUrl != null && invoiceImageUrl.size() > 0.5) {
			error = savePicutre(picList, invoiceImageUrl,
					ContextConstant.PIC_BILL, salesManPo.getSaleid(),
					itemsPo.getItemid());
		}
		
		if (itemdocUrl != null && itemdocUrl.size() > 0.5) {
			savePicutre(picList, itemdocUrl, ContextConstant.doc,
					salesManPo.getSaleid(), itemsPo.getItemid());
		}

		if (picList != null && picList.size() > 0.9) {
			picRecordPoMapper.insertList(picList);
		}

		salesManPo = null!=salesManPo.getSaleid()?salesManPo:salesManPoMapper.findSaleById(customerPo.getSaleid());
		CustomerPo srcCustPo = customerPoMapper.selectByPrimaryKey(customerPo.getCustid());
		ItemsPo srcItemPo = itemsPoMapper.selectByPrimaryKey(itemsPo.getItemid());
//		JdbcSystemUtils.updateHsCust(srcCustPo, customerPo, salesManPo, srcItemPo, itemsPo);
		return null;
	}

	@Override
	public Integer findCountBySaleid(boolean leader,SalesManPo sale, Integer timeSpan,String beginDate,String endDate) {
		Integer count = 0;
		//如果是业务员，只查询自己；其余则查询所有
		if(leader){
			count = customerPoMapper.findCountBySaleid(sale.getSaleid(),
					timeSpan,beginDate,endDate);
		}else{
			count = customerPoMapper.findCountBySaleid(null,
					timeSpan,beginDate,endDate);
		}
		return count;
	}

	@Override
	public void updateCustomerOfSaleId(CustomerPo customerPo) {
		customerPoMapper.updateByPrimaryKeySelective(customerPo);

	}

	@Override
	public Integer countByItemTradetype(String tradetype, SalesManPo sale,
			boolean leader,String beginDate,String endDate,String pendingflag) {
		Integer count=null;
		if(!leader){
			//查询所有的
			
			count=customerPoMapper.selectcountByItemTradetype(null,tradetype,beginDate,endDate,pendingflag);
		}else{
			//更具id查询
			count=customerPoMapper.selectcountByItemTradetype(sale.getSaleid(),tradetype,beginDate,endDate,pendingflag);
		}
		return count;
	}

	@Override
	public boolean checkCustByContAndPhone(String contact, String phone) {
		// TODO Auto-generated method stub
		CustomerPo po = new CustomerPo();
		po.setContact(contact);
		po.setPhone(phone);
		List<CustomerPo> list = customerPoMapper.select(po);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}


	@Override
	public boolean checkItemByNameAndType(String itemname, Integer itemtype,Integer itemtypedetail) {
		// TODO Auto-generated method stub
		ItemsPo po = new ItemsPo();
		po.setItemname(itemname);
		po.setItemtype(itemtype);
		po.setItemtypedetail(itemtypedetail);
		List list = itemsPoMapper.select(po);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<UniversalPo> findRenewCustomerRenew(String searchValue, String itemType, Integer userid, boolean leader, SalesManPo sale, PageTemp pageTemp,
			Integer datecount, String beginDate, String endDate) {
		// TODO Auto-generated method stub

		// 判断是否是经理，经理展示所有的
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<UniversalPo> list = null;
		// 可以选择在mybaits中进行判断，但是为了便于维护，选择到service中。
		if (!leader) {
			list = customerPoMapper.findRenewCustomerAll2(searchValue,itemType,datecount,beginDate,endDate);
		} else {
			list = customerPoMapper.findRenewCustomerByuserid(searchValue,itemType,userid,
					datecount,beginDate,endDate);
		}
		if (list != null && list.size() > 0) {
			for (UniversalPo po : list) {
				String province = getRegionInfo((String) po.get("province"));
				String city = getRegionInfo((String) po.get("city"));
				String area = getRegionInfo((String) po.get("area"));
				po.put("addressInfo",
						province + city + area + po.get("address"));
			}
		}

		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	
	}

	@Override
	public CustomerPo findCustomerPoByCustid(Integer custid) {
		// TODO Auto-generated method stub
		CustomerPo customerPo = new CustomerPo();
		customerPo.setCustid(custid);
		return customerPoMapper.selectOne(customerPo);
	}

}
