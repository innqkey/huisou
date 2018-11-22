package com.huisou.service;

import com.huisou.po.PostPo;
import com.huisou.vo.PageTemp;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-31 下午6:06
 */
public interface PostService {

    int insertPostPo(PostPo postPo);

    List<PostPo> findByOrgId(List<Integer> orgIds);

    int updatePostPo(PostPo postPo);

    List<PostPo> findAll(PageTemp pageTemp);

	void deleteByOid(Integer oid);

    PostPo findByFkId(Integer postid);

	void deleteByPid(Integer postid);

	List<PostPo> findPostByOrgid(Integer organizeid);


}
