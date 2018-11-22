package com.huisou.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.constant.ContextConstant;
import com.huisou.mapper.PicRecordPoMapper;
import com.huisou.po.PicRecordPo;
import com.huisou.service.PicRecordService;

@Service
public class PicRecordServiceImpl implements PicRecordService {
    @Autowired
    private PicRecordPoMapper picMapper;

    @Override
    public void deleteImage(Integer picid, Integer pictype) {
        PicRecordPo po = new PicRecordPo();
        po.setPicid(picid);
        po.setPicstatus(ContextConstant.PIC_STATUS_DELETE);
        picMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public List<PicRecordPo> findDocByItemId(String itemid) {
        List<PicRecordPo> list = null;
        if (StringUtils.isNotBlank(itemid)) {
            list = picMapper
                    .findPicByItemidAndNoDeleteAndDoc(Integer.valueOf(itemid), ContextConstant.doc);
        } else {
            list = null;
        }
        return list;
    }

    @Override
    public int insertList(List<PicRecordPo> list) {
        return picMapper.insertList(list);
    }

    @Override
    public List<String> findInfoByTypeAndId(String stem, Integer id, Integer typeid) {
        Map map = new HashMap();
        map.put("stem", stem);
        map.put("id", id);
        map.put("typeid", typeid);
        return picMapper.findInfoByTypeAndId(map);
    }
    
    @Override
    public List<String> findInfoByTypeAndId1(String stem, Integer id, Integer typeid) {
        Map map = new HashMap();
        map.put("stem", stem);
        map.put("id", id);
        map.put("typeid", typeid);
        return picMapper.findInfoByTypeAndId1(map);
    }
	@Override
	public void selectByFromId(String deleteUrl, Integer fromid) {
		PicRecordPo record = new PicRecordPo();
		record.setFromid(fromid);
		record.setPicurl(deleteUrl);
		
		List<PicRecordPo> selectPo = picMapper.select(record);
		for(int i = 0; i < selectPo.size(); ++i){
			PicRecordPo selectedPo = selectPo.get(i);
			selectedPo.setPicstatus(2);
			picMapper.updateByPrimaryKey(selectedPo);
		}
//		if (selectPo != null){
//			selectPo.setPicstatus(2);
//			picMapper.updateByPrimaryKey(selectPo);
//		}
	}


}
