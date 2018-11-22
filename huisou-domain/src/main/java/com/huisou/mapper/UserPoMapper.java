package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.UserPo;
import com.huisou.vo.MenuSourcesVo;
import com.huisou.vo.UserRoleAuthVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserPoMapper extends MyMapper<UserPo> {

    int insertAndBackId(UserPo userPo);
    
    UserPo findOne(@Param(value = "userid")Integer userid);
    
    List<UserRoleAuthVo> selectAllUserInfo(Map map);

    List<UserPo> selectUserPos(Map map);
    
    List<UserPo> selectAllUserByOrgid(@Param(value = "orgids") List<Integer> orgids);

    List<UserPo> selectUserByPostId(@Param(value = "postids") List<Integer> postids);

    List<UserPo> selectTopUserPos(Map map);

    int delUser(Integer userid);

    int update(UserPo userPo);
    
    List<UserPo> selectUserByRole(Map map);

	void deletePidByPid(Integer postid);

	void deleteOidAndPidByOid(Integer orgid);

	UserPo findUserByEmpid(Integer empid);

	void updateOrgAndPostid(@Param(value = "empid")Integer empid, @Param(value = "orgid")Integer orgid, @Param(value = "postid")Integer postid);

}
