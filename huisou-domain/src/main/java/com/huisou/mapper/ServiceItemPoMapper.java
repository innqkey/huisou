package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.ServiceItemPo;
import com.huisou.po.UniversalPo;

public interface ServiceItemPoMapper extends MyMapper<ServiceItemPo>{
	List<UniversalPo> searchItemListByCon(Map reMap);

	List<ServiceItemPo> findItems(@Param("userid")int userid);
}