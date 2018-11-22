package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.RoleAuthorityPo;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthorityPoMapper extends MyMapper<RoleAuthorityPo> {

    int delRoleAuthByRoleId(Integer roleid);
}