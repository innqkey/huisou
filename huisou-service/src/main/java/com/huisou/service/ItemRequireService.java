package com.huisou.service;

import java.util.List;

import com.huisou.po.ItemRequirePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:37:10 
* 类说明 
*/
public interface ItemRequireService {

	Integer add(Integer itemid, String requirecontent, int createby);

	List findAllByItemid(Integer itemid);

	ItemRequirePo findByrequireid(Integer requireid);

}
