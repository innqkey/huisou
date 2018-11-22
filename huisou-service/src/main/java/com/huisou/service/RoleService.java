package com.huisou.service;

import com.huisou.po.RolePo;

import javax.management.relation.Role;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 上午10:41
 */
public interface RoleService {
    int insertRole(RolePo rolePo);

    int delRole(int roleId);

    List<RolePo> findAll();
    
    RolePo findOne(int roleId);

    int update(RolePo rolePo);
}
