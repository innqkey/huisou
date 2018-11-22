package com.huisou.service;

import com.huisou.po.UserPo;
import com.huisou.vo.UserRoleAuthVo;

import java.util.List;
import java.util.Map;

/**
 * author:  xueyuan
 * date  :  2017-07-04 16:39.
 */
public interface UserService {

    int insertUser(UserPo userPo, Integer roleid);

    List<UserPo> findAll(Map map);

    List<UserPo> findAll2(Map map);

    UserPo findOne(UserPo userPo);

    List<UserRoleAuthVo> findAllUserInfo(Map map);

    List<UserPo> findAll3();

    int delUser(Integer userid);

    UserPo findOneById(Integer userid);

    int update(UserPo userPo);


    List<UserPo> selectUserByRole(Map map);

    List<UserPo> findAllByOgrId(List<Integer> orgids);

    List<UserPo> findAllByPostId(List<Integer> postids);
    
    //根据岗位清除员工的岗位信息
	void deletePidByPid(Integer postid);
	//根据部门清除员工的部门和岗位信息
	void deleteOidAndPidByOid(Integer orgid);

	UserPo findUserByEmpid(Integer empId);

	void updateOrgAndPostid(Integer empid, Integer orgid, Integer postid);

	//查询所有lead 为0/1 的用户
	List<UserPo> findListByparas(UserPo userPo);


}
