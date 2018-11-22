package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.OrganizePo;
import com.huisou.po.UniversalPo;
import com.huisou.vo.OrgPostVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrganizePoMapper extends MyMapper<OrganizePo> {

    List<OrganizePo> findAllOrgId(Integer orgid);

    List<OrganizePo> findAllFatherByOrgId();

    OrganizePo findFatherByOrgId(Integer orgid);

    int findRight(Integer orgid);

    int updateLftRgt(Integer right);

    int insertOrg(Map map);

    OrgPostVo findInfoByUserId(Integer userid);

    List<OrgPostVo> findMoreInfoByUserId(@Param(value = "userids") List<Integer> userids);

	OrganizePo selectOrganizePo(@Param(value = "orgid")Integer orgid, @Param(value = "departname")String departname, @Param(value = "left")Integer left, @Param(value = "right")Integer right, @Param(value = "standby1")String standby1,
			@Param(value = "standby2")String standby2);

	void edit(@Param(value = "orgid")Integer orgid, @Param(value = "orgname")String orgname);

	List findChildOrgidsByorgid(@Param(value = "orgid")Integer orgid);

	void deleteByOid(@Param(value = "orgid")Integer oid);

	OrganizePo findOrgByOrgid(Integer orgid);

	void reduceLftRgt(@Param(value = "right")Integer right, @Param(value = "deleteNum")Integer deleteNum);


	

}