package com.huisou.service.impl;

import com.common.ConvertUtil;
import com.github.pagehelper.PageHelper;
import com.huisou.mapper.MenuPoMapper;
import com.huisou.mapper.SourcesPoMapper;
import com.huisou.po.AuthorityPo;
import com.huisou.po.MenuPo;
import com.huisou.po.SourcesPo;
import com.huisou.service.MenuService;
import com.huisou.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 上午11:08
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuPoMapper menuPoMapper;
    @Autowired
    SourcesPoMapper sourcesPoMapper;

    @Override
    public MenuPo findOneMenu(Integer menuid) {
        MenuPo menuPo = new MenuPo();
        if (menuid != null && menuid > 0) {
            menuPo.setMenuid(menuid);
            return menuPoMapper.selectByPrimaryKey(menuid);
        }
        return null;
    }

    @Override
    public int inserMenu(MenuPo menuPo) {
        if(menuPo.getMenuid()!=null && menuPo.getMenuid()>0){
            return menuPoMapper.updateByPrimaryKey(menuPo);
        }else {
            return menuPoMapper.insert(menuPo);
        }

    }

    @Override
    public int findMainMenuIdByChildId(Integer menuid) {
        return menuPoMapper.selectMenuIdByChildId(menuid);
    }

    /**
     * 查找 所有一级菜单
     *
     * @return
     */
    @Override
    public List<MenuPo> findAllMainMenu() {
        return menuPoMapper.selectMainMenuPo();
    }

    /**
     * 查找 所有一级菜单 带分页
     *
     * @return
     */
    @Override
    public List<MenuPo> findAllMainMenu2(PageTemp pageTemp) {
        PageHelper.startPage(pageTemp.getPageNum(), 20);
        return menuPoMapper.selectMainMenuPo2();
    }

    @Override
    public List<ChildMenuVo> findChildMenu2(int fatherId, PageTemp pageTemp) {
        PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
        return menuPoMapper.selectChildMenuPo2(fatherId);
    }

    /**
     * 查找对应一级菜单的二级菜单
     *
     * @param fatherId
     * @return
     */
    @Override
    public List<MenuPo> findChildMenu(int fatherId) {
        return menuPoMapper.selectChildMenuPo(fatherId);
    }

    @Override
    public List<MenuVo> findMenu() throws IllegalAccessException, InstantiationException {
        List<MenuVo> voList = new ArrayList<>();
        List<MenuPo> mainList = findAllMainMenu();
        if (mainList.size() > 0) {
            for (MenuPo menuPo : mainList) {
                MenuVo menuVo = new MenuVo();
                ConvertUtil.convertDtoAndVo(menuPo, menuVo);
                List<MenuVo.menu> menus = ConvertUtil.convertDtoAndVo(findChildMenu(menuPo.getMenuid()), MenuVo.menu.class);
                for (MenuVo.menu m : menus) {
                    SourcesPo sourcesPo = new SourcesPo();
                    sourcesPo.setMenuid(m.getMenuid());
                    List<SourcesVo> s = ConvertUtil.convertDtoAndVo(sourcesPoMapper.select(sourcesPo), SourcesVo.class);
                    m.setSourcesVoList(s);
                }
                menuVo.setMenuList(menus);
                voList.add(menuVo);
            }
        }
        return voList;
    }

    @Override
    public List<MenuSourcesVo> findMenus(List<Integer> fatherId, List<Integer> sourcesId) {
        return menuPoMapper.selectMenuAndSources(fatherId, sourcesId);
    }

    @Override
    public List<AuthorityPo> findMenusIdByRoleId(Integer roleid) {
        return menuPoMapper.selectMenuId(roleid);
    }

    @Override
    public int update(MenuPo menuPo) {
        return menuPoMapper.update(menuPo);
    }

}
