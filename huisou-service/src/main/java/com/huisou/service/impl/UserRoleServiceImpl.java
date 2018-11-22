package com.huisou.service.impl;

import com.huisou.mapper.UserRolePoMapper;
import com.huisou.po.UserRolePo;
import com.huisou.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author： xueyuan
 * date  ： 2017-08-22 上午11:21
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRolePoMapper userRolePoMapper;

    @Override
    public UserRolePo findOne(Integer userid) {
        UserRolePo userRolePo = new UserRolePo();
        userRolePo.setUserid(userid);
        return userRolePoMapper.selectOne(userRolePo);
    }

	@Override
	public void updateUserRole(UserRolePo userRole) {
		userRolePoMapper.updateByPrimaryKey(userRole);
	}
    
    
}
