package com.huisou.mapper;

import com.common.MyMapper;
import com.huisou.po.PostPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostPoMapper extends MyMapper<PostPo> {

    List<PostPo> findByOrgId(@Param(value = "orgIds") List<Integer> orgIds);

    List<PostPo> findAll();

	void deleteByOid(@Param(value = "orgid")Integer oid);

	void deleteByPid(Integer postid);

	List<PostPo> findPostByOrgid(Integer organizeid);

}