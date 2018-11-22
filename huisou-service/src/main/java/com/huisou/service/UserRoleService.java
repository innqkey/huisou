package com.huisou.service;

import com.huisou.po.UserRolePo;

/**
 * author： xueyuan
 * date  ： 2017-08-22 上午11:20
 */
public interface UserRoleService {

    UserRolePo findOne(Integer userid);
   
    // 更新角色
	void updateUserRole(UserRolePo userRole);
}
