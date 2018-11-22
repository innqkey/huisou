package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SalesManPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VisitRecrdVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月20日 上午10:20:46 
* 类说明 
*/
public interface VisitRecordService {

	PageInfo findItemsAndVisit(PageTemp pageTemp, SalesManPo salesManPo, boolean leader);

	PageInfo findVisitByItemid(PageTemp pageTemp, String itemid);

	PageInfo findItemsAndVisitByParam(String salename, String salephone, String phone, String contact,
			String itemname, String startTime, String endTime, PageTemp pageTemp, SalesManPo salesManPo, boolean leader, String replystatus, int userId);

	void updateVisit(VisitRecordPo visitRecordPo);

	void add(VisitRecordPo visitRecordPo);

	PageInfo findVisitByCustid(PageTemp pageTemp, String custid);

	void addVisitList(List<VisitRecordPo> list);

	VisitRecrdVo findVisitByVisitid(String visitid);



}
