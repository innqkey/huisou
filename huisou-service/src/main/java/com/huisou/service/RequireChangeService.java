package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RequireChangeVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年10月23日 上午9:38:08 
* 类说明 
*/
public interface RequireChangeService {

	void add(Integer itemid, Integer changetype, Integer dealstatus, Integer createby,Integer requireid);

	PageInfo<RequireChangeVo> serarch(String customerPhone, String contact, String itemname, String salename, String saleid,
			PageTemp pageTemp, String begindate, String endDate, String itemtype);

	void dispose(List<Integer> requirechangeids);

}
