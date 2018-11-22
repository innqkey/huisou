package com.huisou.mapper;

import java.util.List;

import com.common.MyMapper;
import com.huisou.po.BigVisitRecordPo;

public interface BigVisitRecordPoMapper extends MyMapper<BigVisitRecordPo> {

	List<BigVisitRecordPo> findBigVisit(Integer bigid);

	void updatebigVisit(BigVisitRecordPo bigVisitRecordPo);

	void insertBigVisit(BigVisitRecordPo bigVisitRecordPo);
	
	
}