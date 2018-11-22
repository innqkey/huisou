package com.huisou.service;

import com.huisou.po.AuthorityPo;
import com.huisou.po.MenuPo;
import com.huisou.vo.ChildMenuVo;
import com.huisou.vo.MenuSourcesVo;
import com.huisou.vo.MenuVo;
import com.huisou.vo.PageTemp;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 上午11:06
 */
public interface MenuService {

    MenuPo findOneMenu(Integer menuid);

    int inserMenu(MenuPo menuPo);

    int findMainMenuIdByChildId(Integer menuid);

    List<MenuPo> findAllMainMenu();

    List<MenuPo> findAllMainMenu2(PageTemp pageTemp);

    List<ChildMenuVo> findChildMenu2(int fatherId, PageTemp pageTemp);

    List<MenuPo> findChildMenu(int fatherId);

    List<MenuVo> findMenu() throws IllegalAccessException, InstantiationException;

    List<MenuSourcesVo> findMenus(List<Integer> fatherId, List<Integer> sourcesId);

    List<AuthorityPo> findMenusIdByRoleId(Integer roleid);

    int update(MenuPo menuPo);
}
