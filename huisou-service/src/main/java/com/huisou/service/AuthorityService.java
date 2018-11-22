package com.huisou.service;

import com.huisou.po.AuthorityPo;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-20 下午2:29
 */
public interface AuthorityService {

    int insertAuthoritys(List<AuthorityPo> authorityPos);

    int delAuthByRoleId(Integer roleid);
}
