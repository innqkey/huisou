package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.AuthorityPo;
import com.huisou.po.MenuPo;
import com.huisou.vo.ChildMenuVo;
import com.huisou.vo.MenuSourcesVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuPoMapper extends MyMapper<MenuPo> {
    List<MenuPo> selectMainMenuPo();

    List<MenuPo> selectMainMenuPo2();

    int selectMenuIdByChildId(Integer menuid);

    int update(MenuPo menuPo);

    List<AuthorityPo> selectMenuId(Integer roleid);

    List<MenuPo> selectChildMenuPo(int fatherId);

    List<ChildMenuVo> selectChildMenuPo2(int fatherId);

    List<MenuSourcesVo> selectMenuAndSources(@Param("fatherId") List<Integer> fatherId,
                                             @Param("sourcesId") List<Integer> sourcesId);
}