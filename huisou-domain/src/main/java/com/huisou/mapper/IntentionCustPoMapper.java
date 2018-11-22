package com.huisou.mapper;


import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.IntentionCustPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:24:16 
* 类说明 
*/
public interface IntentionCustPoMapper extends MyMapper<IntentionCustPo>{

	IntentionCustPo selectOneByPhoneAndName(@Param("phone")String phone, @Param("name")String name);

	void insertOne(@Param("intentionCustPo")IntentionCustPo intentionCustPo);

	List<IntentionCustPo> findAll(@Param("name")String name);

	void update(@Param("intentioncustid")Integer intentioncustid, @Param("name")String name, @Param("phone")String phone, @Param("companyname")String companyname, @Param("address")String address,@Param("updateby")Integer updateby);

}
