package com.huisou.service;

import java.util.List;
import com.huisou.po.NoticePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年11月6日 下午2:42:23 
* 类说明 
*/
public interface NoticeService {

	List<NoticePo> findAllNotices(Integer noticeacceptype);

	List<NoticePo> findAllByList(List<Integer> ids);

	void insert(NoticePo noticePo);
}
