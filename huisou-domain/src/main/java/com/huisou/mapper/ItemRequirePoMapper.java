package com.huisou.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.MyMapper;
import com.huisou.po.ItemRequirePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:42:26 
* 类说明 
*/
@Repository
public interface ItemRequirePoMapper extends MyMapper<ItemRequirePo>{

	List findAllByItemid(Integer itemid);

	ItemRequirePo findByrequireid(Integer requireid);
	
}
