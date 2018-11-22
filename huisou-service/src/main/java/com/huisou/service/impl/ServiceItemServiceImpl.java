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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.PicRecordPoMapper;
import com.huisou.mapper.ServiceItemPoMapper;
import com.huisou.po.ItemsPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.ServiceItemPo;
import com.huisou.po.UniversalPo;
import com.huisou.service.ServiceItemService;
import com.huisou.vo.CustomerInfoVo;
import com.huisou.vo.CustomerVO;
import com.huisou.vo.PageTemp;

@SuppressWarnings("all")
@Service
public class ServiceItemServiceImpl implements ServiceItemService {
	@Autowired
	private ServiceItemPoMapper serviceItem;

	@Override
	public PageInfo<UniversalPo> searchCustomerByCondition(PageTemp pageTemp, Map reMap) {
		// TODO Auto-generated method stub
		List<UniversalPo> list = null;
		
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		list = serviceItem.searchItemListByCon(reMap);
		PageInfo<UniversalPo> pageInfo = new PageInfo<UniversalPo>(list);
		return pageInfo;
	}

	@Override
	public void saveServiceItem(ServiceItemPo po) {
		// TODO Auto-generated method stub
		ServiceItemPo searchPo = new ServiceItemPo();
		searchPo.setItemid(po.getItemid());
		List<ServiceItemPo> list = serviceItem.select(searchPo);
		if(null!=list&&list.size()>0){
			for(ServiceItemPo vo : list){
				vo.setCreateby(po.getCreateby());
				vo.setUserid(po.getUserid());
				vo.setSerremark(po.getSerremark());
				serviceItem.updateByPrimaryKeySelective(vo);
			}
		}else{
			serviceItem.insertSelective(po);
		}
	}

	@Override
	public List<ServiceItemPo> findByUserId(int createby) {
		return serviceItem.findItems(createby);
	}

	@Override
	public boolean findOne(int itemid, int userid) {
		ServiceItemPo itemsPo = new ServiceItemPo();
		itemsPo.setItemid(itemid);
		itemsPo.setUserid(userid);
		List<ServiceItemPo> select = serviceItem.select(itemsPo);
		if (select.size() > 0){
			return true;
		} else {
			return false;
		}
	}

}
