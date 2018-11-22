package com.huisou.po;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * 可以封装混合参数
 * @author Administrator
 *
 */
public class UniversalPo extends TreeMap<String, Object> implements Serializable{


	private static final long serialVersionUID = 815442499280312483L;
		
	public Map<String,Object> setProperty(String key,Object value){
		this.put(key, value);
		return this;
	}
}
