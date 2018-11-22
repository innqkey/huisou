package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.SourcesPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourcesPoMapper extends MyMapper<SourcesPo> {

    int update(SourcesPo sourcesPo);

    List<SourcesPo> findAll();
}