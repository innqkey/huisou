package com.huisou.service.impl;

import com.github.pagehelper.PageHelper;
import com.huisou.mapper.PostPoMapper;
import com.huisou.po.PostPo;
import com.huisou.service.PostService;
import com.huisou.vo.PageTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-31 下午6:06
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostPoMapper postPoMapper;

    @Override
    public int insertPostPo(PostPo postPo) {
        return postPoMapper.insert(postPo);
    }

    @Override
    public List<PostPo> findByOrgId(List<Integer> orgIds) {
        return postPoMapper.findByOrgId(orgIds);
    }

    @Override
    public int updatePostPo(PostPo postPo) {
        return postPoMapper.updateByPrimaryKeySelective(postPo);
    }

    @Override
    public List<PostPo> findAll(PageTemp pageTemp) {
        PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
        return postPoMapper.findAll();
    }

    @Override
    public PostPo findByFkId(Integer postid) {
        return postPoMapper.selectByPrimaryKey(postid);
    }

    @Override
    public void deleteByOid(Integer oid) {
        // TODO Auto-generated method stub
        postPoMapper.deleteByOid(oid);
    }

	@Override
	public void deleteByPid(Integer postid) {
		// TODO Auto-generated method stub
		postPoMapper.deleteByPid(postid);
	}

	@Override
	public List<PostPo> findPostByOrgid(Integer organizeid) {
		// TODO Auto-generated method stub
		List<PostPo> postList = postPoMapper.findPostByOrgid(organizeid);
		return postList;
	}


}
