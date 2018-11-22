package com.huisou.vo;

import java.util.List;

import com.huisou.constant.DictConConstant;
import com.huisou.po.ItemAccountNumberPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月17日 上午8:50:17 
* 类说明 
*/
public class ItemAccountNumberListVo {
	 
	/**
	 * 根据项目id获取ItemAccountNumberPo集合
	 */
	private List<ItemAccountNumberVo> itemAccountNumberList;

   /**
    * 项目名称
    */
   private String itemname;
   
   /**
    * 项目id
    */
   private Integer itemid;
   
   /**
    * 项目l类型
    */
   private Integer itemtype;
   private Integer itemtypedetail;
   private String itemFullName;
   
   
	public Integer getItemtypedetail() {
	return itemtypedetail;
}

public void setItemtypedetail(Integer itemtypedetail) {
	this.itemtypedetail = itemtypedetail;
}

public String getItemFullName() {
	StringBuilder stringBuilder = new StringBuilder(DictConConstant.getDicName("itemtype", itemtype));
	stringBuilder.append("--");
	if (itemtype == 7){
		stringBuilder.append(DictConConstant.getDicName("Itemdetailtype", itemtypedetail))
			.append("--");
	} 
	return stringBuilder.append(itemname).toString();
}

public void setItemFullName(String itemFullName) {
	this.itemFullName = itemFullName;
}

	public Integer getItemtype() {
	return itemtype;
}

public void setItemtype(Integer itemtype) {
	this.itemtype = itemtype;
}

	public String getItemname() {
		return itemname;
	}
	
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	public Integer getItemid() {
		return itemid;
	}
	
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public List<ItemAccountNumberVo> getItemAccountNumberList() {
		return itemAccountNumberList;
	}

	public void setItemAccountNumberList(List<ItemAccountNumberVo> itemAccountNumberList) {
		this.itemAccountNumberList = itemAccountNumberList;
	} 
	 
}
