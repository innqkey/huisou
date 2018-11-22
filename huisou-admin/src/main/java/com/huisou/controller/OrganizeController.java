package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ReflectionUtil;
import com.common.ResUtils;
import com.huisou.constant.ContextConstant;
import com.huisou.po.OrganizePo;
import com.huisou.po.PostPo;
import com.huisou.po.UserPo;
import com.huisou.service.OrganizeService;
import com.huisou.service.PostService;
import com.huisou.service.UserService;
import com.huisou.vo.EluiSelectVo;
import com.huisou.vo.OrgPostVo;
import com.huisou.vo.OrganizeVo;
import org.apache.ibatis.exceptions.IbatisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * author： xueyuan
 * date  ： 2017-08-31 上午10:57
 */
@RestController
@RequestMapping(value = "/organize")
public class OrganizeController extends BaseController {

    @Autowired
    OrganizeService organizeService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Integer orgid, String dep) {
        if (orgid == null || orgid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        Map map = new HashMap();
//        map.put("curid", curid);
        map.put("dep", dep);
        try {
            Integer right = organizeService.findRight(orgid);
            organizeService.updateLftRgt(right);
            map.put("right", right);
            map.put("standby1", orgid);
            organizeService.insert(map);
            return ResUtils.okRes();
        } catch (IbatisException e) {
            return ResUtils.execRes();
        }
    }

    /**
     * 得到当前用户直接领导
     *
     * @param userid
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/leader", method = RequestMethod.POST)
    public String leader(Integer userid) throws InstantiationException, IllegalAccessException {
        if (userid == null || userid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        OrgPostVo orgPostVo = organizeService.findInfoByUserId(userid);
        if (orgPostVo != null) {
            OrganizePo organizePo = organizeService.findFatherByOrgId(orgPostVo.getOrgid());
            List orgIds = new ArrayList();
            orgIds.add(organizePo.getOrgid());
            List<PostPo> postPos = postService.findByOrgId(orgIds);
            List<Integer> userids = new ArrayList<>();
            if(postPos.size()>0){
            	List<Integer> postids = new ArrayList<>();
            	for (PostPo postPo : postPos) {
            		postids.add(postPo.getPostid());
    			}
            	List<UserPo> userPos = userService.findAllByPostId(postids);
            	userids = ReflectionUtil.convertElementPropertyToList(userPos,"userid");
            }
            UserPo po = userService.findOneById(userid);
            //判断是不是部门的普通成员，如果是，上级领导要加上自己部门的负责人
            if(po.getPostid()==0  || po.getPostid()==null){
            	 Integer selfOrgid = po.getOrgid();
            	 //获取和自己同一个部门的领导岗位
            	 List<PostPo> selfPosts = postService.findPostByOrgid(selfOrgid);
            	 //获取岗位id
            	 List<Integer> selfPostids = ReflectionUtil.convertElementPropertyToList(selfPosts,"postid");
            	 //根据这些岗位id获取领导集合
            	 List<UserPo> selfPostUserPos = userService.findAllByPostId(selfPostids);
            	 //获取自己部门的领导的userid的集合
            	 List<Integer> selfPostUserids = ReflectionUtil.convertElementPropertyToList(selfPostUserPos,"userid");
            	 userids.addAll(selfPostUserids);
            }
           
            //得到自己部门的领导
            //得到所有直系领导
            List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);
            return ResUtils.okRes(orgPostVos);
        }
        return ResUtils.execRes();
    }
    

    /**
     * 获取下级子节点
     * @param request
     * @param orgid
     * @return
     */
    @RequestMapping(value = "/findOrgChildrenByOrgid")
    public String findOrgChildrenByOrgid(HttpServletRequest request,String orgid){
    	if(StringUtils.isEmpty(orgid)){
    		return ResUtils.errRes("102", "请求参数错误");
    	}
    	List<Object> result = new ArrayList<>();
    	List<OrganizePo> orgList = organizeService.findOrgChildrenByOrgid(Integer.parseInt(orgid));
    	result.addAll(orgList);
    	List<Integer> oid = new ArrayList<>();
    	oid.add(Integer.parseInt(orgid));
    	List<PostPo>   poList = postService.findByOrgId(oid);
    	if(poList.size()>0 ){
    		result.addAll(poList);
    	}
    	
    	System.out.println(result);
    	return ResUtils.okRes(result);
    }

    /**
     * ========== 未完成  ===========
     * 得到所有领导 （主要用于请假，有可能会需要通知其他部门领导审批）
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/leaders", method = RequestMethod.POST)
    public String leaders() throws IllegalAccessException, InstantiationException {
        List<OrganizeVo> organizeVo = ConvertUtil.convertDtoAndVo(organizeService.findAllFatherByOrgId(),
                OrganizeVo.class);
        List<Integer> orgIds = ReflectionUtil.convertElementPropertyToList(organizeVo, "orgid");
        List<PostPo> postPos = postService.findByOrgId(orgIds);
        List<Integer> userids = ReflectionUtil.convertElementPropertyToList(postPos, "userid");

        //得到所有领导

        return ResUtils.okRes(organizeVo);
    }
    
    /**
     * 获取顶级部门
     * @return
     */
    @RequestMapping(value = "/getMaxFather")
    public String getMaxFather(HttpServletRequest request,String orgid){
    	try {
    		if(StringUtils.isEmpty(orgid)){
        		return ResUtils.errRes("102", "请求参数错误");
        	}else{
        		OrganizePo organizePo = organizeService.findMax(Integer.parseInt(orgid));
        		return ResUtils.okRes(organizePo);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
    	
    }
    
    /**
     * 编辑部门
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request,String orgid,String orgname){
    	try {
    		if(StringUtils.isEmpty(orgid) || StringUtils.isEmpty(orgname)){
        		return ResUtils.errRes("102", "请求参数错误");
        	}else{
        		organizeService.edit(Integer.parseInt(orgid),orgname);
        		return ResUtils.okRes();
        	}
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
    }
    
    /**
     * 删除部门，删除部门的时候会删除下面的子节点和对应的岗位
     * @param request
     * @param orgid
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,String orgid){
    	try {
    		if(StringUtils.isEmpty(orgid)){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
    		List<Integer> orgidList = organizeService.findChildOrgidsByorgid(Integer.parseInt(orgid));
    		int deleteNum = 1;
    		if(null != orgidList){
    			deleteNum = deleteNum+orgidList.size(); 
    			for (Integer oid : orgidList) {
    				postService.deleteByOid(oid);
    				organizeService.deleteByOid(oid);
    				userService.deleteOidAndPidByOid(oid);
    			}
    			
    		}
    		OrganizePo organizePo = organizeService.findOrgByOrgid(Integer.parseInt(orgid));
    		organizeService.reduceLftRgt(organizePo.getRight(),deleteNum);
    		postService.deleteByOid(Integer.parseInt(orgid));
			organizeService.deleteByOid(Integer.parseInt(orgid));
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
			return ResUtils.execRes();
		}
    }
    
    /**
     * 根据部门id获取下面所有的子部门的值，并按层级分类
     * @param request
     * @param orgid
     * @return
     */
    @RequestMapping(value = "/findAllChildrenByorgid")
    public String findAllChildrenByorgid(HttpServletRequest request , String orgid){
    	try {
			if(org.apache.commons.lang.StringUtils.isBlank(orgid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List list = organizeService.findAllChildrenByorgid(Integer.parseInt(orgid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
   
    /**
     * 根据empid获取该员工所有的上级部门id
     * @param request
     * @param empid
     * @return
     */
    @RequestMapping(value = "/findAllOrgByempid")
    public String  findAllOrgByempid(HttpServletRequest request,String empid){
    	try {
    		if(org.apache.commons.lang.StringUtils.isBlank(empid)){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
			UserPo userPo = userService.findUserByEmpid(Integer.parseInt(empid));
			Integer orgid = userPo.getOrgid();
			List<Integer> orgList = organizeService.findFatherOrgidByOrgid(orgid);
			return ResUtils.okRes(orgList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
    
    /**
     * 根据orgid获取所有的上级部门id
     * @param request
     * @param orgid
     * @return
     */
    @RequestMapping(value = "/findAllOrg")
    public String  findAllOrg(HttpServletRequest request,String orgid){
    	try {
    		if(org.apache.commons.lang.StringUtils.isBlank(orgid) && Integer.parseInt(orgid) < 0){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
			Integer orgId = Integer.parseInt(orgid);
			List<Integer> orgList = organizeService.findFatherOrgidByOrgid(orgId);
			return ResUtils.okRes(orgList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
    
    /**
     * 根据userid获取包含自己部门在内的所有子部门
     * @param request
     * @param userid
     * @return
     */
    @RequestMapping(value = "/findChildOrgByUserid")
    public String findChildOrgByUserid(HttpServletRequest request ,String userid){
    	try {
			if(org.apache.commons.lang.StringUtils.isBlank(userid) ){
				return ResUtils.errRes("102", "请求参数错误");
			}
			UserPo userPo = userService.findOneById(Integer.parseInt(userid));
			//获取所有的子级部门id
			List<Integer> orgids = organizeService.findChildOrgidsByorgid(userPo.getOrgid());
			//将自己部门id插入进去
			orgids.add(userPo.getOrgid());
			
			List<EluiSelectVo> orgList = new ArrayList<>();
			for (Integer orgid : orgids) {
				OrganizePo organizePo = organizeService.findOrgByOrgid(orgid);
				EluiSelectVo eluiSelectVo = new EluiSelectVo();
				eluiSelectVo.setSearchkey(organizePo.getOrgid());
				eluiSelectVo.setValue(organizePo.getDepartname());
				orgList.add(eluiSelectVo);
			}
			return ResUtils.okRes(orgList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
    /*
	 * 返回所有部门信息
	 */
	@RequestMapping(value = "/listAllOrgs")
	public String listAllOrgs(HttpServletRequest request,String orgid){
		try {
			if(org.apache.commons.lang.StringUtils.isBlank(orgid) && Integer.parseInt(orgid) < 0){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
			Integer orgId = Integer.parseInt(orgid);
			List<OrganizePo> findAllOrgId = organizeService.findAllOrgId(orgId);
			return ResUtils.okRes(findAllOrgId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
