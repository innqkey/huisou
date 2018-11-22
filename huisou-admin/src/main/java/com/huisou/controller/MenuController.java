package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ReflectionUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.AuthorityPo;
import com.huisou.po.MenuPo;
import com.huisou.po.RolePo;
import com.huisou.service.MenuService;
import com.huisou.service.RoleService;
import com.huisou.service.SourcesService;
import com.huisou.vo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * author： xueyuan
 * date  ： 2017-07-17 上午9:51
 */

@RestController
@RequestMapping(value = "/menu")
public class MenuController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    MenuService menuService;
    @Autowired
    SourcesService sourcesService;
    @Autowired
    RoleService roleService;

    /**
     * 获取所有一级菜单信息（暂时用不到）
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/mainmenu", method = RequestMethod.POST)
    public String mainMenu(PageTemp pageTemp) throws IllegalAccessException, InstantiationException {
        List<MenuPo> list = menuService.findAllMainMenu2(pageTemp);
        PageInfo pageInfo = new PageInfo(list);
        List<MenuVo> voList = ConvertUtil.convertDtoAndVo(list, MenuVo.class);
        pageInfo.setList(voList);
        return ResUtils.okRes(pageInfo);
    }

    /**
     * 存储菜单信息 （暂时用不到）
     *
     * @param menuPo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(MenuPo menuPo) {
        if (menuPo.getMenuname() != null && menuPo.getMenucode() != null && menuPo.getUrl() != null) {
            if (menuService.inserMenu(menuPo) > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }

    /**
     * 根据主键查找
     *
     * @param menuid
     * @return
     */
    @RequestMapping(value = "/getone", method = RequestMethod.POST)
    public String getMenu(Integer menuid) {
        MenuPo menuPo = menuService.findOneMenu(menuid);
        if (menuPo != null) {
            return ResUtils.okRes(menuPo);
        } else {
            return ResUtils.execRes();
        }
    }

    /**
     * 获取子菜单信息 包含主菜单信息（暂时用不到）
     *
     * @param menuPo
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/childmenu", method = RequestMethod.POST)
    public String childMenu(MenuPo menuPo, PageTemp pageTemp) throws IllegalAccessException, InstantiationException {
        if (menuPo.getFatherid() != null && menuPo.getFatherid() > -1) {
            List<ChildMenuVo> childMenuVos = menuService.findChildMenu2(menuPo.getFatherid(), pageTemp);
            PageInfo pageInfo = new PageInfo(childMenuVos);
//            List<MenuPo> list = menuService.findChildMenu(menuPo.getFatherid());
//            List<MenuVo> voList = ConvertUtil.convertDtoAndVo(list, MenuVo.class);
            return ResUtils.okRes(pageInfo);
        }
        return ResUtils.execRes();
    }


    /**
     * 获取全部菜单信息
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list() throws IllegalAccessException, InstantiationException {
        long begin = System.currentTimeMillis();
        List<MenuPo> mainList = menuService.findAllMainMenu();
        List<MenuVo> voList = new ArrayList<>();
        if (mainList.size() > 0) {
            for (MenuPo menuPo : mainList) {
                MenuVo menuVo = new MenuVo();
                ConvertUtil.convertDtoAndVo(menuPo, menuVo);
                List<MenuVo.menu> menus = ConvertUtil.convertDtoAndVo(menuService.findChildMenu(menuPo.getMenuid()), MenuVo.menu.class);
                for (MenuVo.menu m : menus) {
                    List<SourcesVo> s = ConvertUtil.convertDtoAndVo(sourcesService.findAllByMenuId(m.getMenuid()), SourcesVo.class);
                    m.setSourcesVoList(s);
                }
                menuVo.setMenuList(menus);
                voList.add(menuVo);
            }
        }
        logger.info("用时:" + (System.currentTimeMillis() - begin));
        return ResUtils.okRes(voList);
    }

    /**
     * 根据 roleId,userId 获取所有菜单信息，包含sources 菜单的资源
     *
     * @return
     */
    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    public String list2() {
        long begin = System.currentTimeMillis();
        List<MenuSourcesVo> menuSourcesVos = menuService.findMenus(null, null);
        List<Integer> ids = ReflectionUtil.convertElementPropertyToList(menuSourcesVos, "sourcesfatherid");
        Set<Integer> setIds = new TreeSet<>();
        setIds.addAll(ids);
        List<MenuIdsVo> lists = new ArrayList<>();

        for (Integer id : setIds) {
            MenuIdsVo menuIdsVos = new MenuIdsVo();
            List<Integer> sids = new ArrayList<>();
            for (MenuSourcesVo o : menuSourcesVos) {
                if (o.sourcesfatherid == id) {
                    sids.add(o.sourcesid);
                }
            }
            menuIdsVos.setFatherId(id);
            menuIdsVos.setSourcrsIds(sids);
            lists.add(menuIdsVos);
        }

        logger.info("用时:" + (System.currentTimeMillis() - begin));
        return ResUtils.okRes(lists);
    }

    /**
     * 得到指定权限 所有 有效菜单与资源
     * @param roleid
     * @return
     */
    @RequestMapping(value = "/list3", method = RequestMethod.POST)
    public String list3(Integer roleid) {
        if (roleid == null) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        List<AuthorityPo> authorityPos = menuService.findMenusIdByRoleId(roleid);
        List<Integer> temp = ReflectionUtil.convertElementPropertyToList(authorityPos, "menuid");
        Set<Integer> fatherids = new TreeSet<>();
        fatherids.addAll(temp);
        List<Integer> sourcesids = ReflectionUtil.convertElementPropertyToList(authorityPos, "sourcesid");
        Map map = new HashMap();
        map.put("fatherids", fatherids);
        map.put("sourcesids", sourcesids);

        // 获取角色中的 起始日期  add 2017年08月28日11:29:03  by xy
        RolePo rolePo = roleService.findOne(roleid);
        map.put("begintime", rolePo.getStandby1());
        map.put("endtime", rolePo.getStandby2());
        return ResUtils.okRes(map);
    }

    /**
     * 更新菜单
     * @param menuPo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(MenuPo menuPo) {
        if (menuPo == null) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        if (menuService.update(menuPo) > 0) {
            return ResUtils.okRes();
        } else {
            return ResUtils.execRes();
        }
    }

}
