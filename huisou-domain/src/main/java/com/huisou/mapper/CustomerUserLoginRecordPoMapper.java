package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.CustomerUserLoginRecordPo;
import com.huisou.vo.CustomerUserVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午5:55:48 
* 类说明 
*/
public interface CustomerUserLoginRecordPoMapper extends MyMapper<CustomerUserLoginRecordPo>{

	//登陆总次数/最近登陆时间排序
	List<CustomerUserVo> selectByParas(@Param("orderFactor")String orderFactor, 
			@Param("custuserid")Integer custuserid);
	
	//最近7天登陆次数
	List<CustomerUserLoginRecordPo> findAll(@Param("beginDate")String beginDate, @Param("endDate")String endDate, 
			@Param("custuserid")Integer custuserid);
	
	List<CustomerUserLoginRecordPo> selectAllByParas(@Param("custuserid")Integer custuserid);

}
