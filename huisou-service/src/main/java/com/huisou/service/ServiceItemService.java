package com.huisou.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SalesManPo;
import com.huisou.po.ServiceItemPo;
import com.huisou.po.UniversalPo;
import com.huisou.vo.PageTemp;

public interface ServiceItemService {

	PageInfo<UniversalPo> searchCustomerByCondition(PageTemp pageTemp, Map reMap) ;

	void saveServiceItem(ServiceItemPo po);

	List<ServiceItemPo> findByUserId(int createby);

	boolean findOne(int itemid, int userid);
}
