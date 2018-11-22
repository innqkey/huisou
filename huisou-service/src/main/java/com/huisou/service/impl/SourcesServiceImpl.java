package com.huisou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huisou.mapper.SourcesPoMapper;
import com.huisou.po.SourcesPo;
import com.huisou.service.SourcesService;
import com.huisou.vo.PageTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 下午3:06
 */
@Service
public class SourcesServiceImpl implements SourcesService {

    @Autowired
    SourcesPoMapper sourcesPoMapper;

    @Override
    public List<SourcesPo> findAll(PageTemp pageTemp) {
        PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
        return sourcesPoMapper.findAll();
    }

    @Override
    public int insertSources(SourcesPo sourcesPo) {
        return sourcesPoMapper.insert(sourcesPo);
    }

    @Override
    public List<SourcesPo> findAllByMenuId(int menuid) {
        SourcesPo sourcesPo = new SourcesPo();
        sourcesPo.setMenuid(menuid);
        return sourcesPoMapper.select(sourcesPo);
    }

    @Override
    public List<SourcesPo> findAllByMenuId2(PageTemp pageTemp, int menuid) {
        PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
        SourcesPo sourcesPo = new SourcesPo();
        sourcesPo.setMenuid(menuid);
        return sourcesPoMapper.select(sourcesPo);
    }

    @Override
    public SourcesPo findOneSources(Integer sourcesid) {
        return sourcesPoMapper.selectByPrimaryKey(sourcesid);
    }

    @Override
    public int update(SourcesPo sourcesPo) {
        return sourcesPoMapper.update(sourcesPo);
    }
}
