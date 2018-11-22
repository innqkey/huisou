package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.MenuPo;
import com.huisou.po.SourcesPo;
import com.huisou.service.MenuService;
import com.huisou.service.SourcesService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SourcesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-17 上午9:51
 */
@RestController
@RequestMapping(value = "/sources")
public class SourcesController extends BaseController{

    @Autowired
    SourcesService sourcesService;
    @Autowired
    MenuService menuService;

    /**
     * 所有资源的信息 （暂时用不到）
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(PageTemp pageTemp) throws IllegalAccessException, InstantiationException {
        List<SourcesPo> list = sourcesService.findAll(pageTemp);
        PageInfo pageInfo = new PageInfo(list);
        List<SourcesVo> voList = ConvertUtil.convertDtoAndVo(list, SourcesVo.class);
        pageInfo.setList(voList);
        return ResUtils.okRes(pageInfo);
    }


    /**
     * 查找指定自子菜单id的 所有资源的信息 （暂时用不到）
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    public String list2(PageTemp pageTemp, Integer menuid) throws IllegalAccessException, InstantiationException {
        if (menuid == null || menuid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        List<SourcesPo> list = sourcesService.findAllByMenuId2(pageTemp, menuid);
        PageInfo pageInfo = new PageInfo(list);
        List<SourcesVo> voList = ConvertUtil.convertDtoAndVo(list, SourcesVo.class);
        pageInfo.setList(voList);
        return ResUtils.okRes(pageInfo);
    }

    /**
     * 保存资源信息  （暂时用不到，因为这些信息是要手动输入到数据库，通过页面无法更改）
     *
     * @param sourcesPo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SourcesPo sourcesPo) {
        if (sourcesPo.getUrl() != null && sourcesPo.getSourcesname() != null && sourcesPo.getMenuid() != null) {
            if (sourcesService.insertSources(sourcesPo) > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }

    /**
     * 更新
     *
     * @param sourcesPo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SourcesPo sourcesPo) {
        if (sourcesPo != null && sourcesPo.getSourcesid() > 0) {
            if (sourcesService.update(sourcesPo) > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }

    /**
     * 根据主键查找
     *
     * @param sourcesid
     * @return
     */
    @RequestMapping(value = "/getone", method = RequestMethod.POST)
    public String getMenu(Integer sourcesid) throws InstantiationException, IllegalAccessException {
        SourcesPo sourcesPo = sourcesService.findOneSources(sourcesid);
        if (sourcesPo != null) {
            int fatherid = menuService.findMainMenuIdByChildId(sourcesPo.getMenuid());
            SourcesVo sourcesVo = (SourcesVo) ConvertUtil.convertDtoAndVo(sourcesPo, SourcesVo.class);
            sourcesVo.setMainmenuid(fatherid);
            return ResUtils.okRes(sourcesVo);
        } else {
            return ResUtils.execRes();
        }
    }


}
