package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.CustomerPoMapper;
import com.huisou.mapper.ItemsPoMapper;
import com.huisou.mapper.SalesCustHistoryPoMapper;
import com.huisou.mapper.SalesManPoMapper;
import com.huisou.po.CustomerPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.po.SalesManPo;
import com.huisou.service.SalesCustHistoryService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SaleCustHistUserVo;
import com.huisou.vo.SaleCustHistoryVo;
@Service
public class SalesCustHistoryServiceImpl implements SalesCustHistoryService {
	
	@Autowired
	private SalesCustHistoryPoMapper salesCustHistoryPoMapper;
	
	@Autowired
	private SalesManPoMapper salesManPoMapper;
	
	@Autowired
	private ItemsPoMapper itemsPoMapper;
	
	@Override
	public void save(SalesCustHistoryPo salesCustHistoryPo) {
		salesCustHistoryPoMapper.insertSelective(salesCustHistoryPo);
	}
	@Override
	public List<SalesCustHistoryPo>  findHistoryBypara(SalesCustHistoryPo salesCustHistoryPo) {
		List<SalesCustHistoryPo> list = salesCustHistoryPoMapper.select(salesCustHistoryPo);
		return list;
	}
	
	@Override
	public List<SaleCustHistoryVo> findCustomerHistoryBycustid(Integer custid) throws Exception {
		List<SalesCustHistoryPo> result=salesCustHistoryPoMapper.findCustomerHistoryBycustid(custid);
		if(result==null||result.size()<1){
			return null;
		}
		List<SaleCustHistoryVo> listVos = new ArrayList<SaleCustHistoryVo>();
		//查出所有的该客户的变更记录
		for (SalesCustHistoryPo custHistoryPo : result) {
			Integer beforesaleid = custHistoryPo.getBeforesaleid();
			if(beforesaleid.equals(custHistoryPo.getEndsaleid())){
				break;
			}
			//查询之前是否有改业务员变更的历史
			List<SalesCustHistoryPo> hisList=salesCustHistoryPoMapper.selectHistoryByEndSaleidAndCustid(beforesaleid,custHistoryPo.getCustid());
			//查询出该业务员的信息
			SalesManPo manPo = salesManPoMapper.findSaleById(beforesaleid);
			SaleCustHistoryVo custHistoryVo = new SaleCustHistoryVo();
			custHistoryVo.setSalename(manPo.getSalename());
			custHistoryVo.setEndDate(DateUtils.format(custHistoryPo.getCreatedate(), "yyyy-MM-dd"));
			if(hisList.size()<1||hisList==null){
				//说明之前没有对应的变更记录，第一次变更,那么该业务员的服务的开始时间就是对应的项目的开始时间
				List<ItemsPo> itemlist = itemsPoMapper.selectItemByCustidOrderByBeginDate(custid);
				custHistoryVo.setBeginDate(DateUtils.format(itemlist.get(0).getItembegindate(), "yyyy-MM-dd"));
			}else { 
				//如果有的话
				custHistoryVo.setBeginDate(DateUtils.format(hisList.get(0).getCreatedate(), "yyyy-MM-dd"));
			}
			listVos.add(custHistoryVo);
		}
		
		
		return listVos;
	}
	@Override
	public PageInfo<SaleCustHistUserVo> findCustomerHistUserList(
			 PageTemp pageT, String searchValue, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageT.getPageNum(), pageT.getPageSize());
		List<SaleCustHistUserVo> list = salesCustHistoryPoMapper.findCustomerHistUserList(searchValue, beginDate, endDate);
		Page page = (Page) list;
        return new PageInfo(list);
	}

}
