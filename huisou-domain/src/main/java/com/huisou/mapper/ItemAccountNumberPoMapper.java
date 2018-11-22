package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.ItemAccountNumberPo;
import com.huisou.vo.ItemAccountNumberVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月15日 下午5:56:29 
* 类说明 
*/
public interface ItemAccountNumberPoMapper extends MyMapper<ItemAccountNumberPo>{

	void add(@Param("custuserid")Integer custuserid, @Param("itemid")Integer itemid, @Param("urlid")Integer urlid, @Param("url")String url, @Param("username")String username, @Param("password")String password,
			@Param("comments")String comments, @Param("createdate")Date createdate,@Param("contact")String contact, @Param("phone")String phone, @Param("itemname")String itemname, @Param("itemtype")Integer itemtype);

	List<ItemAccountNumberPo> findByItemid(@Param("itemid")Integer itemid);

	void update(@Param("accountid")Integer accountid, @Param("username")String username, @Param("password")String password, @Param("url")String url, @Param("comments")String comments);

	List<ItemAccountNumberVo> search(@Param("contact")String contact, @Param("phone")String phone, @Param("itemname")String itemname);

	List<Integer> findItemListByCustuserid(@Param("custuserid")Integer custuserid);

	List<ItemAccountNumberVo> findItemAccountNumberPoList(@Param("itemid")Integer itemid);

	List<ItemAccountNumberVo> findAccountListByCusterid(@Param("custuserid")Integer custuserid);

	ItemAccountNumberPo findByAccountid(@Param("accountid")Integer accountid);

	List<ItemAccountNumberVo> searchBySaleid(@Param("contact")String contact, @Param("phone")String phone, @Param("itemname")String itemname, @Param("saleid")Integer saleid);

	void addAccount(@Param("custuserid")Integer custuserid, @Param("itemid")Integer itemid, @Param("urlid")Integer urlid, @Param("url")String url, @Param("username")String username, @Param("password")String password,
			@Param("comments")String comments, @Param("createdate")Date createdate,@Param("contact")String contact, @Param("phone")String phone, @Param("itemname")String itemname, @Param("itemtype")Integer itemtype,
			@Param("saleid")Integer saleid);

	List<ItemAccountNumberPo> findByItemIdAndUrlId(@Param("itemid")Integer itemid, @Param("urlid")Integer urlid);

}
