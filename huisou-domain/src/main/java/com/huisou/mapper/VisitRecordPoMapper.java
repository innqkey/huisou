package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.VisitRecordPo;
import com.huisou.vo.VisitRecrdVo;

public interface VisitRecordPoMapper extends MyMapper<VisitRecordPo> {

	List findItemsAndVisit();

	List findVisitByItemid(Integer itemid);

	List findItemsAndVisitByParam(@Param(value="salename")String salename, @Param(value="salephone")String salephone, 
			@Param(value="phone")String phone, @Param(value="contact")String contact, @Param(value="itemname")String itemname,
			@Param(value="starttime")String starttime, @Param(value="endtime")String endtime,@Param("replystatus") String replystatus);
	List findItemsAndVisitByParam2(@Param(value="salename")String salename, @Param(value="salephone")String salephone, 
			@Param(value="phone")String phone, @Param(value="contact")String contact, @Param(value="itemname")String itemname,
			@Param(value="starttime")String starttime, @Param(value="endtime")String endtime,@Param("replystatus") String replystatus,@Param("userId") Integer userId);

	void updateVisit(VisitRecordPo visitRecordPo);

	List findVisitByCustid(Integer parseInt);

	void addList(List<VisitRecordPo> partList);

	List findItemsAndVisitBySale(Integer saleid);

	VisitRecrdVo findVisitByVisitid(Integer parseInt);
}