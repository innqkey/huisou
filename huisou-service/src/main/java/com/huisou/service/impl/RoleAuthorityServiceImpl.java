package com.huisou.service.impl;

import com.huisou.mapper.RoleAuthorityPoMapper;
import com.huisou.po.RoleAuthorityPo;
import com.huisou.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-20 下午3:34
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    RoleAuthorityPoMapper roleAuthorityPoMapper;

    @Override
    public int insertRoleAuthoritys(List<RoleAuthorityPo> roleAuthorityPos) {
        return roleAuthorityPoMapper.insertList(roleAuthorityPos);
    }

    @Override
    public int delRoleAuthByRoleId(Integer roleid) {
        return roleAuthorityPoMapper.delRoleAuthByRoleId(roleid);
    }
}
