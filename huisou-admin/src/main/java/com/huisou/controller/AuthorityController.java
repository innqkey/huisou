package com.huisou.controller;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.huisou.mapper.RolePoMapper;
import com.huisou.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * author： xueyuan
 * date  ： 2017-07-17 下午7:33
 */

@RestController
@RequestMapping(value = "/authority")
public class AuthorityController extends BaseController{
    @Autowired
    RolePoMapper rolePoMapper;

    /**
     * 权限列表信息   （暂时用不到）
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list() throws IllegalAccessException, InstantiationException {
        List<RoleVo> roleVos = ConvertUtil.convertDtoAndVo(rolePoMapper.selectAll(), RoleVo.class);

        return ResUtils.okRes(roleVos);
    }
}
