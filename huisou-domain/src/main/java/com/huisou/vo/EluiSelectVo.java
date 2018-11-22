package com.huisou.vo;
/** 为了elui的select选择器返回值创建的对象
* @author 作者 :yuhao 
* @version 创建时间：2017年9月20日 下午7:47:55 
* 类说明 
*/
public class EluiSelectVo {
	private Integer searchkey;
	private String value;  //elui input远程数据必须有的参数
	public Integer getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(Integer searchkey) {
		this.searchkey = searchkey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
