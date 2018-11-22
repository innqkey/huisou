package com.huisou.service;

import com.huisou.po.RoleAuthorityPo;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-20 下午3:34
 */
public interface RoleAuthorityService {

    int insertRoleAuthoritys(List<RoleAuthorityPo> roleAuthorityPos);

    int delRoleAuthByRoleId(Integer roleid);
}
