package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.PicRecordPo;

public interface PicRecordPoMapper extends MyMapper<PicRecordPo> {

    List<PicRecordPo> findPicByItemidAndNoDelete(Integer itemid);

    List<PicRecordPo> findPicByItemidAndNoDeleteAndDoc(@Param("itemid") Integer itemid,
                                                       @Param("doc") Integer doc);

    List<String> findInfoByTypeAndId(Map map);
    List<String> findInfoByTypeAndId1(Map map);
}