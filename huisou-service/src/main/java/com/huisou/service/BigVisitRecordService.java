package com.huisou.service;

import java.util.List;

import com.huisou.po.BigVisitRecordPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月25日 下午4:28:00 
* 类说明 
*/
public interface BigVisitRecordService {

	List<BigVisitRecordPo> findBigVisit(Integer bigid);

	void update(BigVisitRecordPo bigVisitRecordPo);

	void add(BigVisitRecordPo bigVisitRecordPo);

	void addVisitList(List<BigVisitRecordPo> list);

}
