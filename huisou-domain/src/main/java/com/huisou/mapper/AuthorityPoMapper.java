package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.AuthorityPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityPoMapper extends MyMapper<AuthorityPo> {

    int insertMoreAndBackId(List<AuthorityPo> authorityPos);

    int delAuthByRoleId(Integer roleid);
}