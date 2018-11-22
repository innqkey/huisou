package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.RolePo;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface RolePoMapper extends MyMapper<RolePo> {

    int delRole(int roleId);

    int insertAndBackId(RolePo rolePo);

    int updateRole(RolePo rolePo);

}