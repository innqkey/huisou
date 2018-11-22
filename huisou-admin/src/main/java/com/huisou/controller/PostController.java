package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.PostPo;
import com.huisou.service.OrganizeService;
import com.huisou.service.PostService;
import com.huisou.service.UserService;
import com.huisou.vo.OrgPostVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * author： xueyuan
 * date  ： 2017-09-01 上午9:24
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    OrganizeService organizeService;
    
    @Autowired
    UserService userService;

    /**
     * 得到所有员工岗位信息
     *
     * @param pageTemp
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(PageTemp pageTemp) throws IllegalAccessException, InstantiationException {
        List<PostPo> list = postService.findAll(pageTemp);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(ConvertUtil.convertDtoAndVo(list, PostVo.class));
        return ResUtils.okRes(pageInfo);
    }


    /**
     * 保存 岗位信息
     *
     * @param postVo
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(PostVo postVo) throws InstantiationException, IllegalAccessException {
        if (postVo != null) {
            PostPo postPo = (PostPo) ConvertUtil.convertDtoAndVo(postVo, PostPo.class);
            if (postService.insertPostPo(postPo) > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }


    /**
     * 查看自己的岗位信息
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/mypost", method = RequestMethod.POST)
    public String myPost(Integer userid) {

        List<Integer> userids = new ArrayList<>();
        userids.add(userid);
        List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);

        return ResUtils.okRes(orgPostVos.get(0));

    }

    /**
     * 更新 岗位信息
     *
     * @param postVo
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(PostVo postVo) throws InstantiationException, IllegalAccessException {
        if (postVo != null) {
            PostPo postPo = (PostPo) ConvertUtil.convertDtoAndVo(postVo, PostPo.class);
            if (postService.updatePostPo(postPo) > 0) {
                return ResUtils.okRes();
            }
        }
        return ResUtils.execRes();
    }
    
    /**
     * 删除岗位的同时会删除在移除该岗位员工的岗位信息
     * @param request
     * @param postid
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,String postid){
    	try {
    		if(StringUtils.isEmpty(postid)){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
				userService.deletePidByPid(Integer.parseInt(postid));
				postService.deleteByPid(Integer.parseInt(postid));
				return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
    
    /**
     * 根据部门查看岗位
     * @param request
     * @param orgid
     * @return
     */
    @RequestMapping(value = "/findPostByOrgid")
    public String findPostByOrgid(HttpServletRequest request,String orgid){
    	try {
			if(StringUtils.isEmpty(orgid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<PostPo> list = postService.findPostByOrgid(Integer.parseInt(orgid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
    }
    

    @RequestMapping(value = "/findPost")
    public String findPost(HttpServletRequest request,Integer postid){
    	if (postid > 0){
    		PostPo po = postService.findByFkId(postid);
    		return ResUtils.okRes(po);
    	} else{
    		return ResUtils.execRes();
    	}
    }
}
