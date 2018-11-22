package com.huisou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UniversalPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SalesmanVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年7月17日 下午5:28:16 
* 类说明 
*/
public interface SalesmanService {

	
	PageInfo findAll(PageTemp pageTemp);
	
//	/**
//	 * 根据业务员名字查询业务员
//	 * @param salename
//	 * @return
//	 */
//	PageInfo findSaleBysalename(String salename);
//	
//	/**
//	 * 根据业务员电话查询业务员
//	 * @param salephone
//	 * @return
//	 */
//	PageInfo findSaleBysalephone(String salephone);
//	
//	/**
//	 * 根据业务员姓名电话和创建时间查询业务员
//	 * @param salename
//	 * @param salephone
//	 * @return
//	 */
	PageInfo findSale(String salename, String salephone, Date startTime, Date endTime,PageTemp pageTemp);
	
	/**
	 * 修改业务员
	 * @param saleid
	 * @param status
	 */
	void update(SalesManPo salesManPo);
	
	/**
	 * 根据业务员手机号获取id和姓名
	 * @param Salephone
	 * @return
	 */
	public SalesManPo findSaleBySalephone(String Salephone);

	/**
	 * 批量修改业务员状态
	 * @param saleids
	 * @param status
	 */
	void updateList(ArrayList<Integer> saleids, Integer status , Integer userid);
	
	/**
	 * 添加业务员
	 * @param salesManPo
	 */
	Integer insertSalesMan(SalesManPo salesManPo);

	boolean iphoneIsExist(String salephone);

	SalesManPo findSaleById(Integer beforesaleid);
	
	List<SalesManPo> findListByparas(SalesManPo salesManPo);
	/**
	 * 修改业务员的手机号校验,修改的手机号数据库已存在返回true（不包括自己原本的）
	 * @param salesManPo
	 * @return
	 */
	boolean checkUpdatePhone(SalesManPo salesManPo);
	
}
