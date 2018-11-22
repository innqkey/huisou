package com.huisou.service.impl;

import com.huisou.mapper.OrganizePoMapper;
import com.huisou.mapper.PostPoMapper;
import com.huisou.po.OrganizePo;
import com.huisou.po.UniversalPo;
import com.huisou.service.OrganizeService;
import com.huisou.vo.OrgPostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午5:08
 */
@Service
public class OrganizeServiceImpl implements OrganizeService {

    @Autowired
    OrganizePoMapper organizePoMapper;


    @Override
    public List<OrganizePo> findAllOrgId(Integer orgid) {
        return organizePoMapper.findAllOrgId(orgid);
    }

    @Override
    public List<OrganizePo> findAllFatherByOrgId() {
        return organizePoMapper.findAllFatherByOrgId();
    }

    @Override
    public OrganizePo findFatherByOrgId(Integer OrgId) {
        return organizePoMapper.findFatherByOrgId(OrgId);
    }

    @Override
    public int findRight(Integer OrgId) {
        return organizePoMapper.findRight(OrgId);
    }

    @Override
    public int updateLftRgt(Integer right) {
        return organizePoMapper.updateLftRgt(right);
    }

    @Override
    public int insert(Map map) {
        return organizePoMapper.insertOrg(map);
    }

    @Override
    public OrgPostVo findInfoByUserId(Integer userid) {
        return organizePoMapper.findInfoByUserId(userid);
    }

    @Override
    public List<OrgPostVo> findMoreInfoByUserId(List<Integer> userids) {
        return organizePoMapper.findMoreInfoByUserId(userids);
    }

	@Override
	public List<OrganizePo> findOrgChildrenByOrgid(Integer orgid) {
		List<OrganizePo> orgList = new ArrayList<>();
		OrganizePo org = new OrganizePo();
		org.setOrgid(orgid);
		OrganizePo organizePo = organizePoMapper.selectOrganizePo(org.getOrgid(),org.getDepartname(),org.getLeft(),org.getRight(),org.getStandby1(),org.getStandby2());
		if(null!=organizePo){
			int left = organizePo.getLeft();
			int right = organizePo.getRight();
			OrganizePo org1 = new OrganizePo();
			OrganizePo org2 = new OrganizePo();
				right--;
				left++;
				org2.setRight(right);
				OrganizePo org22 = organizePoMapper.selectOrganizePo(org2.getOrgid(),org2.getDepartname(),org2.getLeft(),org2.getRight(),org2.getStandby1(),org2.getStandby2());
				if(null!=org22){//判断是否有子节点
					orgList.add(org22);
					if(left!=(right-1)){//判断是否有多个子节点
						while (left != org22.getLeft()) {//循环判断并遍历子节点
							org1.setLeft(left);
							OrganizePo org11 = organizePoMapper.selectOrganizePo(org1.getOrgid(),org1.getDepartname(),org1.getLeft(),org1.getRight(),org1.getStandby1(),org1.getStandby2());
							if(null!=org11){
								orgList.add(org11);
								left = org11.getRight()+1;
							}
						}
					}
				}
		}
		return orgList;
	}

	@Override
	public OrganizePo findMax(Integer orgid) {
		// TODO Auto-generated method stub
		OrganizePo org = organizePoMapper.selectOrganizePo(orgid,null,null,null,null,null);
		return org;
	}

	@Override
	public void edit(Integer orgid, String orgname) {
		// TODO Auto-generated method stub
		 organizePoMapper.edit(orgid,orgname);
	}

	@Override
	public List<Integer> findChildOrgidsByorgid(Integer orgid) {
		// TODO Auto-generated method stub
		List<Object> list = organizePoMapper.findChildOrgidsByorgid(orgid);
		List<Integer> orgidList = new ArrayList<>();
		if(null == list){
			return null;
		}
		for (Object object : list) {
			String oid = object.toString().substring(object.toString().indexOf("=")+1,object.toString().length()-1);
			orgidList.add(Integer.parseInt(oid));
		}
		return orgidList;
	}

	@Override
	public void deleteByOid(Integer oid) {
		// TODO Auto-generated method stub
		organizePoMapper.deleteByOid(oid);
	}

	@Override
	public OrganizePo findOrgByOrgid(Integer orgid) {
		// TODO Auto-generated method stub
		OrganizePo organizePo = organizePoMapper.findOrgByOrgid(orgid);
		return organizePo;
	}

	@Override
	public void reduceLftRgt(Integer right, Integer deleteNum) {
		// TODO Auto-generated method stub
		organizePoMapper.reduceLftRgt(right,deleteNum*2);
	}

	@Override
	public List findAllChildrenByorgid(Integer orgid) {
		// TODO Auto-generated method stub
		List<Map<Object, Object>>  allChildrenList = new ArrayList<>();
		
		OrganizePo organizePo = organizePoMapper.findOrgByOrgid(orgid);
		Map<Object, Object> map = new HashMap<>();
		map.put("value", organizePo.getOrgid());
		map.put("label", organizePo.getDepartname());
		List<OrganizePo> list1 = this.findOrgChildrenByOrgid(orgid);
		if(list1.size()>0){
			List list = this.recursionChildrenByorgid(orgid);
			map.put("children", list);
		}
		allChildrenList.add(map);
		return allChildrenList;
	}

	
	public List recursionChildrenByorgid(Integer orgid) {
		// TODO Auto-generated method stub
		List<Map<Object, Object>>  allChildrenList = new ArrayList<>();
		
		OrganizePo organizePo = organizePoMapper.findOrgByOrgid(orgid);
		
		List<OrganizePo> list1 = this.findOrgChildrenByOrgid(orgid);
		if(list1.size()>0){
			for (int i = 0; i < list1.size(); i++) {
				OrganizePo org =list1.get(i);
				Map<Object, Object> map = new HashMap<>();
				map.put("value", org.getOrgid());
				map.put("label", org.getDepartname());
				List<OrganizePo> list2 = this.findOrgChildrenByOrgid(org.getOrgid());
				if(list2.size()>0){
					map.put("children", this.recursionChildrenByorgid(org.getOrgid()));
				}
				allChildrenList.add(map);
			}
		}
		return allChildrenList;
	}

	@Override
	public List<Integer> findFatherOrgidByOrgid(Integer orgid) {
		// TODO Auto-generated method stub
		List<Integer> list  = new ArrayList<>();
		List<Integer> list1  = new ArrayList<>();
		OrganizePo organizePo = organizePoMapper.findOrgByOrgid(orgid);
		list.add(organizePo.getOrgid());
		
		OrganizePo organizePo1 = new OrganizePo();
		organizePo1=organizePo;
		while (Integer.parseInt(organizePo1.getStandby1())!=0) {
			organizePo1 = organizePoMapper.findOrgByOrgid(Integer.parseInt(organizePo1.getStandby1()));
			list.add(organizePo1.getOrgid());
		}
		
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get((list.size()-1-i)));
		}
		return list1;
	}




}
