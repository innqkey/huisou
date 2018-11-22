package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.AccidentRecordPo;
import com.huisou.po.NoticePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月6日 下午2:45:06 
* 类说明 
*/
public interface NoticePoMapper extends MyMapper<NoticePo>{
	List<NoticePo> queryAllNotices(@Param("noticeacceptype")Integer noticeacceptype);

	List<NoticePo> selectAllByList(List<Integer> ids);
}
