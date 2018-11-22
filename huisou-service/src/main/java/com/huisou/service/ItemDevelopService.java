package com.huisou.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.ItemDevelopPo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月20日
*/

public interface ItemDevelopService {
	// 保存一条项目记录
	void saveItemDev(ItemDevelopPo itemDevelopPo);
	// 查找一条项目记录
	ItemDevelopPo selectOnePo(int i);
	// 更新一条项目记录
	void updateDevelop(ItemDevelopPo selectedPo);
	// 查询/显示项目记录
	PageInfo<ItemDevelopPo> queryByMultiParas(Map<String, String> map, PageTemp pageTemp);

}
