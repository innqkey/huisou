package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ReflectionUtil;
import com.common.ResUtils;
import com.huisou.constant.ContextConstant;
import com.huisou.po.AuthorityPo;
import com.huisou.po.RoleAuthorityPo;
import com.huisou.po.RolePo;
import com.huisou.service.AuthorityService;
import com.huisou.service.MenuService;
import com.huisou.service.RoleAuthorityService;
import com.huisou.service.RoleService;
import com.huisou.vo.MenuSourcesVo;
import com.huisou.vo.RoleVo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-14 下午3:42
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    RoleAuthorityService roleAuthorityService;

    /**
     * 角色信息列表
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list() throws IllegalAccessException, InstantiationException {
        List<RolePo> list = roleService.findAll();
        List<RoleVo> roleVos = ConvertUtil.convertDtoAndVo(list, RoleVo.class);
        return ResUtils.okRes(roleVos);

    }

    /**
     * 添加 角色信息 同时与权限信息进行相关联
     *
     * @param roleParam
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RoleVo.RoleParam roleParam) throws InstantiationException, IllegalAccessException {
        List<RolePo> list = roleService.findAll();
        List<String> rolenames = ReflectionUtil.convertElementPropertyToList(list, "rolename");

        //角色名字
        if (roleParam.getRoleid() == null && roleParam.getRolename() == null) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        if (roleParam.getAuths().get("childId").equals("") || roleParam.getAuths().get("sourcesId").equals("")) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
//        如果为新建角色信息 那么判断是否重名
        if (null == roleParam.getRoleid() && rolenames.contains(roleParam.getRolename())) {
            return ResUtils.errRes(ContextConstant.EXIST_NAME, "当前角色名已存在");
        }
//        // --测试
        long begin = System.currentTimeMillis();

        RolePo rolePo = new RolePo();
        rolePo.setRolename(roleParam.getRolename());
        rolePo.setRolestatus(1);

        //当前角色的可查询的日期 （如果有的话）  添加 2017年08月28日10:39:47 by xy
        if (StringUtils.isNotBlank(roleParam.getBegintime())) {
            rolePo.setStandby1(roleParam.getBegintime());
        }
        if (StringUtils.isNotBlank(roleParam.getEndtime())) {
            rolePo.setStandby2(roleParam.getEndtime());
        }
//        Map map = new HashMap();
//        //前台接收到的数组 菜单主键以及资源主键
//        map.put("childd", "10,11");
//        map.put("sourcesId", "1,2,3,5,6");
//        roleParam.setAuths(map);

        String[] fatherArr = roleParam.getAuths().get("childId").toString().split(",");
        String[] sourcesArr = roleParam.getAuths().get("sourcesId").toString().split(",");
        List<Integer> fatherId = new ArrayList<>();
        for (String s : fatherArr) {
            fatherId.add(Integer.parseInt(s));
        }
        List<Integer> sourcesId = new ArrayList<>();
        for (String s : sourcesArr) {
            sourcesId.add(Integer.parseInt(s));
        }
        int updateOrAdd;
        //如果从页面带了roleid 那说明是更改已知权限 -- 先删除 原本的记录，在插入新的记录
        if (roleParam.getRoleid() != null && roleParam.getRoleid() > 0) {
            // 先删除 authority 表
            authorityService.delAuthByRoleId(roleParam.getRoleid());
            //  再删除 authority 关联的 role-authority 表
            roleAuthorityService.delRoleAuthByRoleId(roleParam.getRoleid());
            rolePo.setRoleid(roleParam.getRoleid());

            roleService.update(rolePo);
            updateOrAdd = 1;
        } else {
            roleService.insertRole(rolePo);
            updateOrAdd = 1;
        }
        // 创建新权限
        if (updateOrAdd > 0) {
            //得到所有 菜单信息
            List<MenuSourcesVo> allMenu = menuService.findMenus(null, null);
            //得到当前被选中的菜单信息
            List<MenuSourcesVo> menuSourcesVos = menuService.findMenus(fatherId, sourcesId);
            // 角色权限映射表数据
            List<RoleAuthorityPo> roleAuthorityPos = new ArrayList<>();
            // 权限表数据
            List<AuthorityPo> authorityPos = new ArrayList<>();

            int flag = 0;
            for (MenuSourcesVo o : allMenu) {
                for (int i = 0; i < menuSourcesVos.size(); i++) {
                    if (o.getSourcesid() == menuSourcesVos.get(i).getSourcesid()
                            && o.getSourcesfatherid() == menuSourcesVos.get(i).getSourcesfatherid()) {
                        AuthorityPo authorityPo = new AuthorityPo();
                        authorityPo.setMenuid(o.getSourcesfatherid());
                        authorityPo.setSourcesid(o.getSourcesid());
                        authorityPo.setStatus(1);
                        authorityPos.add(authorityPo);
                        menuSourcesVos.remove(menuSourcesVos.get(i));
                        flag = 1;
                        break;
                    }
                }
                if (flag != 1) {
                    AuthorityPo authorityPo = new AuthorityPo();
                    // 判断是否为一级菜单 是的话去除  --- 已优化sql 去除
//                    if (o.getSourcesid() != null && o.getSourcesfatherid() != null) {
                    authorityPo.setMenuid(o.getSourcesfatherid());
                    authorityPo.setSourcesid(o.getSourcesid());
                    authorityPo.setStatus(0);
                    authorityPos.add(authorityPo);
//                    }
                }
                flag = 0;
            }
            authorityService.insertAuthoritys(authorityPos);
            for (AuthorityPo auth : authorityPos) {

                RoleAuthorityPo rap = new RoleAuthorityPo();
                rap.setRoleid(rolePo.getRoleid());
                rap.setAuthorityid(auth.getAuthorityid());
                roleAuthorityPos.add(rap);
            }

            if (roleAuthorityPos.size() > 0) {
                roleAuthorityService.insertRoleAuthoritys(roleAuthorityPos);
            }

            return ResUtils.okRes();
        }
        return ResUtils.execRes();
    }

    /**
     * 删除角色信息  1为可用 0 为禁用 (暂时用不到)
     *
     * @param rolePo
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(RolePo rolePo) {
        if (rolePo != null && rolePo.getRoleid() > 0) {
            int flag = roleService.delRole(rolePo.getRoleid());
            if (flag > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }


}
