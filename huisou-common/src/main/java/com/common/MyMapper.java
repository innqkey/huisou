package com.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * author:  xueyuan
 * date  :  2017-07-05 15:25.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}