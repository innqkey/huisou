package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.UrlPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午6:09:41 
* 类说明 
*/
public interface UrlPoMapper extends MyMapper<UrlPo>{

	List<UrlPo> findAll();

	UrlPo findUrlPo(@Param("accountypename")String accountypename);

	UrlPo findUrlPoByid(@Param("urlid")Integer urlid);

}
