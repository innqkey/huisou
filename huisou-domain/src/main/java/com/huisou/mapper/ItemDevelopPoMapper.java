package com.huisou.mapper;

import java.util.List;
import java.util.Map;
import com.common.MyMapper;
import com.huisou.po.ItemDevelopPo;

public interface ItemDevelopPoMapper extends MyMapper<ItemDevelopPo> {

	List<ItemDevelopPo> queryByMultiParas(Map<String, String> map);
}