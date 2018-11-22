package com.huisou.controller;

import com.common.*;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.service.OrganizeService;
import com.huisou.service.UserService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.UserRoleAuthVo;
import com.huisou.vo.UserVo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * author： xueyuan
 * date  ： 2017-07-17 上午9:51
 */

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    
    @Autowired
    OrganizeService organizeService;

    /**
     * 查看所有用户基本信息、权限信息
     *
     * @param roleid
     * @param userid
     * @param rolename
     * @param begintime
     * @param endtime
     * @param status
     * @param pageTemp
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Integer roleid, Integer userid, String rolename, String begintime, String endtime, Integer status, PageTemp pageTemp) throws IllegalAccessException, InstantiationException, ParseException {
        // 无参数的请求，返回所有用户的信息
        Map<String, Object> map = new HashMap<>();
        map.put("roleid", roleid);
        map.put("rolename", rolename);
        map.put("status", status);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        //添加分页操作信息 为了得到相应的role列表
        map.put("pageNum", pageTemp.getPageNum());
        map.put("pageSize", pageTemp.getPageSize());

        PageInfo pageInfo;
        //如果有相关的开始、结束信息，那么表示只查询业务员相关信息，与管理员以及财务没有关系
        if (endtime != null && !("").equals(endtime) && begintime != null && !("").equals(begintime)) {
            pageInfo = new PageInfo(userService.findAll(map));
        } else {
            //如果没有开始、结束信息，那么说明查询的是所有用户的列表
            pageInfo = new PageInfo(userService.findAll2(map));
        }

        List<Integer> ids = ReflectionUtil.convertElementPropertyToList(pageInfo.getList(), "userid");

        //判断roleid是查看详情，还是全部请求
        if (userid == null && ids.size() > 0) {
            map.put("userid", ids);
        } else if (userid == null && ids.size() < 1) {
            map.put("userid", null);
        } else {
            ids.clear();
            ids.add(userid);
            map.put("userid", ids);
        }
        //移除分页信息 会干扰查找 对应用户的全部信息
        map.remove("pageNum");
        map.remove("pageSize");
        long begin = System.currentTimeMillis();
        List<UserRoleAuthVo> list = userService.findAllUserInfo(map);

        //验证id 如果请求的结果 status=0 的时候，没有参数的时候，他会返回一串无效值，这边直接不解析直接去掉。
        List<Integer> valIds = ReflectionUtil.convertElementPropertyToList(pageInfo.getList(), "userid");
        if (valIds.size() < 1) {
            pageInfo.setList(null);
            return ResUtils.okRes(pageInfo);
        }

        //开始构造 便于json转换形式类
        //转成json的数据对象
        List<UserRoleAuthVo> convertList = new ArrayList<>();
        // 一级菜单
        List<UserRoleAuthVo.MenuList> menuLists = new ArrayList<>();
        //二级菜单
        List<UserRoleAuthVo.ChildList> childLists = new ArrayList<>();
        //资源列表
//        List<UserRoleAuthVo.SourcesList> sourcesLists = new ArrayList<>();
        Map maps = new HashMap();

//        List<Map> maps = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            UserRoleAuthVo o = new UserRoleAuthVo();
            o.setUserid(list.get(i).getUserid());
            o.setMenuid(list.get(i).getMenuid());
            o.setSourcesfatherid(list.get(i).getSourcesfatherid());

//            UserRoleAuthVo.SourcesList sourcesList = new UserRoleAuthVo.SourcesList();
//            sourcesList.setSourcesid(list.get(i).getSourcesid());
//            sourcesList.setSourcesname(list.get(i).getSourcesname());
//            sourcesList.setSourcesurl(list.get(i).getSourcesurl());
//            sourcesList.setSourcesstatus(list.get(i).getStatus());
//            sourcesList.setSourcestarget(list.get(i).getSourcestarget());
//            Map tmpMaps = new HashMap();
            maps.put(list.get(i).getSourcestarget(), list.get(i).getStatus());
//            sourcesList.setMap(tmpMaps);
//            sourcesLists.add(sourcesList);
//            maps.add(tmpMaps);

            //构造资源列表
            if ((i + 1 < list.size() && !String.valueOf(o.getSourcesfatherid()).equals(String.valueOf(list.get(i + 1).getSourcesfatherid())))
                    || i + 1 == list.size()) {
                UserRoleAuthVo.ChildList childList = new UserRoleAuthVo.ChildList();
                childList.setChildid(list.get(i).getSourcesfatherid());
                childList.setChildname(list.get(i).getChildname());
                childList.setChildurl(list.get(i).getChildurl());
                childList.setChildtarget(list.get(i).getChildtarget());

//                List<Integer> statusid = ReflectionUtil.convertElementPropertyToList(sourcesLists, "sourcesstatus");
//                if (statusid.size() > 0 && statusid.contains(1)) {
//                    childList.setChildstatus(1);
//                } else {
//                    childList.setChildstatus(0);
//                }
                Collection values = maps.values();
                if (values.size() > 0 && values.contains(1)) {
                    childList.setChildstatus(1);
                } else {
                    childList.setChildstatus(0);
                }
//                List<UserRoleAuthVo.SourcesList> s = new ArrayList<>();
                Map s = new HashMap<>();
//                s.addAll(sourcesLists);
                s.putAll(maps);
//                childList.setSourcesLists(s);
                childList.setMap(s);
                childLists.add(childList);
//                sourcesLists.clear();
                maps.clear();
            }
            //构造子菜单
            if ((i + 1 < list.size() && !String.valueOf(o.getMenuid()).equals(String.valueOf(list.get(i + 1).getMenuid())))
                    || i + 1 == list.size()) {
                UserRoleAuthVo.MenuList menuList = new UserRoleAuthVo.MenuList();
                menuList.setMenuid(list.get(i).getMenuid());
                menuList.setMenuname(list.get(i).getMenuname());
                menuList.setMenuurl(list.get(i).getMenuurl());
                menuList.setMenutarget(list.get(i).getMenutarget());
                List<Integer> statusid = ReflectionUtil.convertElementPropertyToList(childLists, "childstatus");
                if (statusid.size() > 0 && statusid.contains(1)) {
                    menuList.setMenustatus(1);
                } else {
                    menuList.setMenustatus(0);
                }
                List<UserRoleAuthVo.ChildList> c = new ArrayList<>();
                c.addAll(childLists);
                menuList.setChildList(c);
                menuLists.add(menuList);
                childLists.clear();
            }
            // 提前判断下一个 是否跟当前属于同一个用户，如果不属于同一个用户，那么开始构造 当前用户的数据
            if ((i + 1 < list.size() && !String.valueOf(o.getUserid()).equals(String.valueOf(list.get(i + 1).getUserid())))
                    || i + 1 == list.size()) {
                //正式填充 用户信息
                o.setUsername(list.get(i - 1).getUsername());
                o.setPassword(list.get(i - 1).getPassword());
                if (list.get(i - 1).getPetname() == null) {
                    o.setPetname(list.get(i - 1).getSalename());
                } else {
                    o.setPetname(list.get(i - 1).getPetname());
                }
//                o.setMenutarget(list.get(i - 1).getMenutarget());
                o.setRoleid(list.get(i - 1).getRoleid());
                o.setRolename(list.get(i - 1).getRolename());
                o.setUserstatus(list.get(i - 1).getUserstatus());
                o.setSalename(list.get(i - 1).getSalename());
                o.setSalephone(list.get(i - 1).getSalephone());
                o.setSaleemail(list.get(i - 1).getSaleemail());
                o.setSaleweixin(list.get(i - 1).getSaleweixin());
                o.setSalestatus(list.get(i - 1).getSalestatus());
                o.setCreatedate(list.get(i - 1).getCreatedate() != null ? DateUtil.formatDate(list.get(i - 1).getCreatedate(),
                        "yyyy-MM-dd HH:mm:ss") : null);
                o.setUpdatedate(list.get(i - 1).getUpdatedate() != null ? DateUtil.formatDate(list.get(i - 1).getUpdatedate(),
                        "yyyy-MM-dd HH:mm:ss") : null);
                o.setMenuid(null);
                o.setSourcesfatherid(null);
                List<UserRoleAuthVo.MenuList> m = new ArrayList<>();
                m.addAll(menuLists);
                o.setMenuLists(m);
                convertList.add(o);
                menuLists.clear();
            }
        }
        pageInfo.setList(convertList);
        return ResUtils.okRes(pageInfo);
    }

    /**
     * 存储用户
     *
     * @param userPo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(UserPo userPo, Integer roleid) {
        if (roleid == null || roleid != null && roleid < 1 ||
                userPo == null || userPo != null && userPo.getUsername() == null) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        List<UserPo> list = userService.findAll3();
        List<String> usernames = ReflectionUtil.convertElementPropertyToList(list, "username");
        if (usernames.contains(userPo.getUsername())) {
            return ResUtils.errRes(ContextConstant.EXIST_NAME, "当前用户名已存在");
        }
        int flag = userService.insertUser(userPo, roleid);
        if (flag > 0 && flag != 3) {
            return ResUtils.okRes();
        } else if (flag > 0 && flag == 3) {
            return ResUtils.errRes(ResUtils.exceCode, "业务员经理的账号已存在！");
        } else {
            return ResUtils.execRes();
        }
    }

    /**
     * 禁用用户
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String save(Integer userid) {
        if (userService.delUser(userid) > 0) {
            return ResUtils.okRes();
        } else {
            return ResUtils.execRes();
        }

    }

    /**
     * 根据id获取user
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/getone", method = RequestMethod.POST)
    public String getone(Integer userid) throws InstantiationException, IllegalAccessException {
        if (userid != null && userid > 0) {
            UserPo userPo = userService.findOneById(userid);
            UserVo userVo = (UserVo) ConvertUtil.convertDtoAndVo(userPo, UserVo.class);
            return ResUtils.okRes(userVo);
        }
        return ResUtils.execRes();
    }

    /**
     * 更新用户
     *
     * @param userPo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(UserPo userPo, String enable) {
        if (userPo == null || userPo != null && userPo.getUserid() == null) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        if (userPo != null && !"".equals(userPo.getPassword()) && enable == null) {
            userPo.setPassword(MD5Util.md5Encode(MD5Util.md5Encode(userPo.getPassword())));
        }
        if (userService.update(userPo) > 0) {
            return ResUtils.okRes();
        } else {
            return ResUtils.execRes();
        }
    }


//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    public String page(PageTemp pageTemp, Integer roleid, Integer userid, String rolename, String begintime, String endtime, Integer salestatus) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("roleid", roleid);
//        map.put("userid", userid);
//        map.put("rolename", rolename);
//        map.put("salestatus", salestatus);
//        map.put("begintime", begintime);
//        map.put("endtime", endtime);
//        map.put("pageTemp", pageTemp);
//        return ResUtils.okRes(new PageInfo(userService.findAll(map)));
//    }
    /**
	 * 事故记录责任人/负责人 下拉选择输入框
	 */
	@RequestMapping(value="/findResponsible")
	public String findResponsible(HttpServletRequest request){
		try {
			UserPo userPo = new UserPo();
			String leader = request.getParameter("leader");
//			if(StringUtils.isNotBlank(request.getParameter("resorgid"))){
//				int orgid = Integer.parseInt(request.getParameter("resorgid"));
//				userPo.setOrgid(orgid);
//			}
			if(StringUtils.isNotBlank(leader)){
				userPo.setLeader(Integer.parseInt(leader));
			}
			
			List<UserPo> list = userService.findListByparas(userPo);
			List<Map<String,Object>> valMap = new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				for(UserPo po:list){
					if(po.getOrgid() == null){
						continue;
					}
					if(organizeService.findOrgByOrgid(po.getOrgid()) == null){
						continue;
					}
					String organizer = organizeService.findOrgByOrgid(po.getOrgid()).getDepartname();
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("resorgid", po.getOrgid());
					if(StringUtils.isBlank(leader)){
						map.put("value", po.getPetname()+"("+organizer+")");
						map.put("responsibleid", po.getUserid());
					} else if (Integer.parseInt(leader)== 1){
						map.put("value", po.getPetname()+"("+organizer+")");
						map.put("groupresid", po.getUserid());
					}
					valMap.add(map);
				}
			}
			return ResUtils.okRes(valMap);
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.execRes();
		}
	}

    //获取客服用户列表
    @RequestMapping(value = "/customerList")
    public String customerList(HttpServletRequest request,UserPo userPo){
    	String roleid = request.getParameter("roleid");
//    	if(StringUtils.isBlank(roleid)){
//    		return ResUtils.errRes("102", "请求参数错误");
//    	}
    	Map reMap = new HashMap();
    	reMap.put("roleid", roleid);
    	List<UserPo> list = userService.selectUserByRole(reMap);
    	List<Map<String,Object>> valMap = new ArrayList<Map<String,Object>>();
    	if(list!=null&&list.size()>0){
			for(UserPo po:list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userid", po.getUserid());
				map.put("value", po.getPetname()+"（"+po.getUsername()+"）");
				map.put("petname", po.getPetname());
				map.put("username", po.getUsername());
				valMap.add(map);
			}
		}
		return ResUtils.okRes(valMap);
    }
}
