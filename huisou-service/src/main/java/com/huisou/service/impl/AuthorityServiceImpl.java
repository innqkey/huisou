package com.huisou.service.impl;

import com.huisou.mapper.AuthorityPoMapper;
import com.huisou.po.AuthorityPo;
import com.huisou.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-20 下午2:30
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityPoMapper authorityPoMapper;

    @Override
    public int insertAuthoritys(List<AuthorityPo> authorityPos) {
        return authorityPoMapper.insertMoreAndBackId(authorityPos);
    }

    @Override
    public int delAuthByRoleId(Integer roleid) {
        return authorityPoMapper.delAuthByRoleId(roleid);
    }
}
