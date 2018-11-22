package com.huisou.service;

import java.util.List;

import com.huisou.po.PicRecordPo;

public interface PicRecordService {

    void deleteImage(Integer picid, Integer pictype);


    List<PicRecordPo> findDocByItemId(String itemid);

    int insertList(List<PicRecordPo> list);

    List<String> findInfoByTypeAndId(String stem, Integer id, Integer typeid);
    List<String> findInfoByTypeAndId1(String stem, Integer id, Integer typeid);

    void selectByFromId(String deleteUrl, Integer fromid);


//	List<PicRecordPo> findDocsByItemId(String itemid);
}
