package com.huisou.service.impl;

import com.huisou.mapper.RolePoMapper;
import com.huisou.po.RolePo;
import com.huisou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 上午10:42
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolePoMapper rolePoMapper;

    /**
     * 插入返回主键
     *
     * @param rolePo
     * @return
     */
    @Override
    public int insertRole(RolePo rolePo) {

        return rolePoMapper.insertAndBackId(rolePo);
    }


    @Override
    public int delRole(int roleId) {
        return rolePoMapper.delRole(roleId);
    }

    @Override
    public List<RolePo> findAll() {
        RolePo rolePo = new RolePo();
        rolePo.setRolestatus(1);
        return rolePoMapper.select(rolePo);
    }


	@Override
	public RolePo findOne(int roleId) {
		return rolePoMapper.selectByPrimaryKey(roleId);
	}

    @Override
    public int update(RolePo rolePo) {
        return rolePoMapper.updateRole(rolePo);
    }
}
