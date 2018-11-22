package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.common.MyMapper;
import com.huisou.po.RequireChangePo;
import com.huisou.vo.RequireChangeVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:41:35 
* 类说明 
*/
@Repository
public interface RequireChangePoMapper extends MyMapper<RequireChangePo>{

	RequireChangePo findRecentlyChangePo(Integer itemid);

	List<RequireChangeVo> serarch(@Param(value="phone")String phone, @Param(value="contact")String contact, @Param(value="itemname")String itemname, @Param(value="salename")String salename, @Param(value="saleid")int saleid,
			@Param(value="beginDate")String beginDate, @Param(value="endDate")String endDate, @Param(value="itemtype")int itemtype);

	void updateDealstatus(Integer requirechangeid);

}
