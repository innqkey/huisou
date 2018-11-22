package com.huisou.service;

import com.huisou.po.OrganizePo;
import com.huisou.vo.OrgPostVo;

import java.util.List;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午5:08
 */
public interface OrganizeService {

    List<OrganizePo> findAllOrgId(Integer orgid);

    OrganizePo findFatherByOrgId(Integer orgid);

    int findRight(Integer orgid);

    int updateLftRgt(Integer right);

    List<OrganizePo> findAllFatherByOrgId();

    int insert(Map map);

    OrgPostVo findInfoByUserId(Integer userid);

    List<OrgPostVo> findMoreInfoByUserId(List<Integer> userids);
    
    List<OrganizePo> findOrgChildrenByOrgid(Integer orgid);

	OrganizePo findMax(Integer orgid);

	void edit(Integer orgid, String orgname);
	
	//查询该部门下面的所有子部门id（不包含自身）
	List<Integer> findChildOrgidsByorgid(Integer orgid);

	void deleteByOid(Integer oid);

	OrganizePo findOrgByOrgid(Integer orgid);

	void reduceLftRgt(Integer right, Integer deleteNum);

	List findAllChildrenByorgid(Integer orgid);
	
	//根据当前的orgid获取所有的直系父级领导
	List<Integer> findFatherOrgidByOrgid(Integer orgid);

}
