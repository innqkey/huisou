package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.ClickUrlRecordPo;
import com.huisou.vo.ClickUrlRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午6:01:46 
* 类说明 
*/
public interface ClickUrlRecordPoMapper extends MyMapper<ClickUrlRecordPo>{

	List<ClickUrlRecordVo> findAll(@Param("beginDate")String beginDate, @Param("endDate")String endDate, 
			@Param("custuserid")Integer custuserid);

}
