package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.IntentionItemPo;
import com.huisou.vo.ItemCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午2:54:46 
* 类说明 
*/
public interface IntentionItemPoMapper extends MyMapper<IntentionItemPo>{

	//根据custid 和courseid 查找项目
	List<ItemCourseVo> selectByParams(@Param("intentioncustid")Integer intentioncustid, @Param("courseid")Integer courseid, @Param("createby")int createby);

	void updateItemCourseId(@Param("courseid")int courseid, @Param("intentioncustid")int intentioncustid, @Param("createby")int createby);

}
