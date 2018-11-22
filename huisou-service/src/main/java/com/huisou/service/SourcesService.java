package com.huisou.service;

import com.huisou.po.SourcesPo;
import com.huisou.vo.PageTemp;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-07-19 下午3:06
 */
public interface SourcesService {

    List<SourcesPo> findAll(PageTemp pageTemp);

    int insertSources(SourcesPo sourcesPo);

    List<SourcesPo> findAllByMenuId(int menuid);

    List<SourcesPo> findAllByMenuId2(PageTemp pageTemp,int menuid);

    SourcesPo findOneSources(Integer sourcesid);

    int update(SourcesPo sourcesPo);
}
