package com.huisou.service.impl;

import com.common.MD5Util;
import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.UserPoMapper;
import com.huisou.mapper.UserRolePoMapper;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.po.UserRolePo;
import com.huisou.service.SalesmanService;
import com.huisou.service.UserService;
import com.huisou.vo.UserRoleAuthVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author:  xueyuan
 * date  :  2017-07-04 16:39.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserPoMapper userPoMapper;
    @Autowired
    UserRolePoMapper userRolePoMapper;
    @Autowired
    SalesmanService salesmanService;

    /**
     * 新增用户
     * 这边要跟saleman功能有交互，saleman添加的时候，这边也应该新增
     * saleman 需要传递的参数为 username（用户账号） ，type（约定好的类型）
     */
    @Override
    public int insertUser(UserPo userPo, Integer roleid) {
        // 默认初始密码
        userPo.setPassword(MD5Util.md5Encode(MD5Util.md5Encode("666666")));
        // 默认状态
        userPo.setStatus(1);
        UserRolePo userRolePo = new UserRolePo();
        //如果插入的roleid888 则代表当前的角色为业务员经理，需要插入saleman字段
        if (roleid != null && roleid == 888) {
            boolean iphoneIsExist = salesmanService.iphoneIsExist(userPo.getUsername());
            // 判断输入的号码是否重复
            if (iphoneIsExist) {
                return 3;
            }
            SalesManPo salesManPo = new SalesManPo();
            Date createdate = new Date();
            salesManPo.setCreatedate(createdate);
            salesManPo.setSalephone(userPo.getUsername());
            salesManPo.setSalename(userPo.getPetname());
            salesManPo.setSalestatus(1);
            int keyid = salesmanService.insertSalesMan(salesManPo);
            userPo.setLeader(1);
            userPo.setKeyid(keyid);
            userRolePo.setRoleid(888);
        }
        //==============================================
        if (userPoMapper.insertAndBackId(userPo) > 0) {
            userRolePo.setUserid(userPo.getUserid());
            //判断  如果是业务员的话 ，为特殊情况，用约定好的id - 999
            if (userPo.getType() != null && userPo.getType() == 3) {
                    userRolePo.setRoleid(999);
                } else {
                    userRolePo.setRoleid(roleid);
            }
            if (userRolePoMapper.insert(userRolePo) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public List<UserPo> findAll(Map map) {
        PageHelper.startPage((int) map.get("pageNum"), (int) map.get("pageSize"));
        return userPoMapper.selectUserPos(map);
    }

    @Override
    public List<UserPo> findAll2(Map map) {
        PageHelper.startPage((int) map.get("pageNum"), (int) map.get("pageSize"));
        return userPoMapper.selectTopUserPos(map);
    }

    @Override
    public List<UserPo> findAll3() {
        return userPoMapper.selectAll();
    }

    @Override
    public List<UserRoleAuthVo> findAllUserInfo(Map map) {
        List<UserRoleAuthVo> list = userPoMapper.selectAllUserInfo(map);
        return list;
    }


    @Override
    public int delUser(Integer userid) {
        return userPoMapper.delUser(userid);
    }

    @Override
    public UserPo findOneById(Integer userid) {
        return userPoMapper.selectByPrimaryKey(userid);
    }

    @Override
    public int update(UserPo userPo) {
        return userPoMapper.update(userPo);
    }

    @Override
    public List<UserPo> findAllByOgrId(List<Integer> orgids) {
        return userPoMapper.selectAllUserByOrgid(orgids);
    }

    @Override
    public List<UserPo> findAllByPostId(List<Integer> postids){
        return userPoMapper.selectUserByPostId(postids);
    }

    @Override
    public UserPo findOne(UserPo userPo) {
        UserPo po = null;
        try {
            Object obj = userPoMapper.selectOne(userPo);
            if (null != obj) {
                po = new UserPo();
                BeanUtils.copyProperties(po, obj);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return po;
    }

	@Override
	public List<UserPo> selectUserByRole(Map map) {
		// TODO Auto-generated method stub
		return userPoMapper.selectUserByRole(map);
	}

	public void deletePidByPid(Integer postid) {
		// TODO Auto-generated method stub
		userPoMapper.deletePidByPid(postid);
	}

	@Override
	public void deleteOidAndPidByOid(Integer orgid) {
		// TODO Auto-generated method stub
		userPoMapper.deleteOidAndPidByOid(orgid);
	}

	@Override
	public UserPo findUserByEmpid(Integer empId) {
		// TODO Auto-generated method stub
		UserPo userPo = userPoMapper.findUserByEmpid(empId);
		return userPo;
	}

	@Override
	public void updateOrgAndPostid(Integer empid, Integer orgid, Integer postid) {
		// TODO Auto-generated method stub
		userPoMapper.updateOrgAndPostid(empid,orgid,postid);					
	}

	@Override
	public List<UserPo> findListByparas(UserPo userPo) {
		return userPoMapper.select(userPo);
	}


}
