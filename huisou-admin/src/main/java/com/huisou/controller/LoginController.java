package com.huisou.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.FileUtil;
import com.common.MD5Util;
import com.common.ResUtils;
import com.common.VerifyCodeUtils;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.CustomerUserLoginRecordPoMapper;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.RolePo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.po.UserRolePo;
import com.huisou.service.CustomerLoginRecordSerivce;
import com.huisou.service.CustomerUserService;
import com.huisou.service.RoleService;
import com.huisou.service.SaleRemSetService;
import com.huisou.service.SalesmanService;
import com.huisou.service.UserRoleService;
import com.huisou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author： xueyuan
 * date  ： 2017-07-14 下午2:48
 */

@RestController
public class LoginController {


    @Autowired
    UserService userService;

    @Autowired
    SalesmanService salesmanService;

    @Autowired
    SaleRemSetService remSetSer;

    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    RoleService roleService;
    @Autowired
    CustomerUserService customerUserService;
    @Autowired
    CustomerLoginRecordSerivce custLoginRecordSerivce;

//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @RequestMapping(value = "/index")
//    public String index(Model model) {
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        Subject subject = SecurityUtils.getSubject();
//        model.addAttribute("username", userName);
//        return "index";
//    }
//
//    @RequiresRoles("admin")
//    @ResponseBody
//    @RequestMapping(value = "/index/add")
//    public String add() {
//        return "/index/add";
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String defaultIndex(Model model) {
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        model.addAttribute("username", userName);
//        return "login";
//    }

    /**
     * 用户登陆
     *
     * @param userPo
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(UserPo userPo, HttpServletRequest request) {
        if (!StringUtils.isEmpty(userPo.getPassword()) && !StringUtils.isEmpty(userPo.getUsername())) {
            userPo.setPassword(MD5Util.md5Encode(MD5Util.md5Encode(userPo.getPassword())));
            UserPo valUser = userService.findOne(userPo);
            if (valUser != null && valUser.getStatus() == 0) {
                return ResUtils.errRes(ResUtils.exceCode, ResUtils.lockUser);
            }
            if (valUser != null && valUser.getUserid() > 0) {
                String token = "";
                //token是否开启，1开启、2关闭
                if ("1".equals(FileUtil.getApplicationPro("http.token"))) {
                    token = MD5Util.md5Encode(UUID.randomUUID().toString());
                }
                //先清空session
                clearSession(request);
                HttpSession session = request.getSession();
                //session过期时间24小时
                session.setMaxInactiveInterval(24*60*60);
                if (null != valUser.getKeyid() && valUser.getKeyid() > 0) {
                    SalesManPo salePo = salesmanService.findSaleById(valUser.getKeyid());
                    session.setAttribute(ContextConstant.SALE_SESSION + token, salePo);
                }
                session.setAttribute(ContextConstant.USER_SESSION + token, valUser);

                //续签提醒，查询天数放在session中
                session.setAttribute(ContextConstant.SALE_TIMESPAN + token, remSetSer.getTimeSpan(valUser.getKeyid(), 1));

                Object obj = session.getAttribute(ContextConstant.SALE_SESSION + token);

                Map map = new HashMap();
                map.put("username", valUser.getUsername());
                map.put("keyid", valUser.getKeyid());
                map.put("userid", valUser.getUserid());
                map.put("petname", valUser.getPetname());
                map.put("leader", valUser.getLeader());

                //得到对应权限id
                UserRolePo userRolePo =  userRoleService.findOne(valUser.getUserid());
                map.put("roleid", userRolePo.getRoleid());
                RolePo rolePo = roleService.findOne(userRolePo.getRoleid());
                map.put("begindate", null!=rolePo.getStandby1()?rolePo.getStandby1():"");
                map.put("enddate", null!=rolePo.getStandby2()?rolePo.getStandby2():"");

                map.put(ContextConstant.SESSION_TOKEN, token);
                return ResUtils.okRes(map);
            }
        }
        return ResUtils.errRes(ResUtils.exceCode, ResUtils.errUser);
    }
    /**
     * 客户登陆
     *
     * @param custUserPo
     * @return
     */
    @RequestMapping(value = "/custLogin", method = RequestMethod.GET)
    public String custLogin(CustomerUserPo custUserPo, HttpServletRequest request) {
    	if (!StringUtils.isEmpty(custUserPo.getPassword()) && !StringUtils.isEmpty(custUserPo.getPhone())) {
    		custUserPo.setPassword(MD5Util.md5Encode(MD5Util.md5Encode(custUserPo.getPassword())));
    		CustomerUserPo valUser = customerUserService.findOne(custUserPo);
    		if (valUser != null && valUser.getStatus() == 0) {
    			return ResUtils.errRes(ResUtils.exceCode, ResUtils.lockUser);
    		}
    		if (valUser != null && valUser.getCustuserid() > 0) {
    			String token = "";
    			//token是否开启，1开启、2关闭
    			if ("1".equals(FileUtil.getApplicationPro("http.token"))) {
    				token = MD5Util.md5Encode(UUID.randomUUID().toString());
    			}
    			//先清空session
    			clearSession(request);
    			HttpSession session = request.getSession();
    			//session过期时间24小时
    			session.setMaxInactiveInterval(24*60*60);
//    			if (null != valUser.getKeyid() && valUser.getKeyid() > 0) {
//    				SalesManPo salePo = salesmanService.findSaleById(valUser.getKeyid());
//    				session.setAttribute(ContextConstant.SALE_SESSION + token, salePo);
//    			}
    			session.setAttribute(ContextConstant.CUST_SESSION + token, valUser);
    			
    			//续签提醒，查询天数放在session中
//    			session.setAttribute(ContextConstant.SALE_TIMESPAN + token, remSetSer.getTimeSpan(valUser.getKeyid(), 1));
    			
//    			Object obj = session.getAttribute(ContextConstant.SALE_SESSION + token);
    			
    			Map map = new HashMap();
    			map.put("username", valUser.getPhone());
    			map.put("keyid", null);
    			map.put("userid", valUser.getCustuserid());
    			map.put("petname", valUser.getContact());
    			map.put("leader", null);
    			
    			//得到对应权限id
//    			UserRolePo userRolePo =  userRoleService.findOne(valUser.getCustuserid());
//    			map.put("roleid", userRolePo.getRoleid());
//    			RolePo rolePo = roleService.findOne(userRolePo.getRoleid());
//    			map.put("begindate", null!=rolePo.getStandby1()?rolePo.getStandby1():"");
//    			map.put("enddate", null!=rolePo.getStandby2()?rolePo.getStandby2():"");
    			
    			map.put(ContextConstant.SESSION_TOKEN, token);
    			custLoginRecordSerivce.addLoginNumber(custUserPo);
    			return ResUtils.okRes(map);
    		}
    	}
    	return ResUtils.errRes(ResUtils.exceCode, ResUtils.errUser);
    }

    @RequestMapping(value = "/imagecode")
    //登陆验证码
    public void imageCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        //返回验证码
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 36;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    //对已验证码
    @RequestMapping(value = "/veritycode")
    @ResponseBody
    public boolean verityCode(HttpServletRequest request, HttpServletResponse response, HttpSession session, String veritycode) {

        if (org.apache.commons.lang.StringUtils.isBlank(veritycode) && veritycode == null) {
            return false;
        }
        String vercode = (String) session.getAttribute("verCode");
        if (org.apache.commons.lang.StringUtils.isBlank(vercode)) {
            return false;
        }
        if (vercode.equalsIgnoreCase(veritycode)) {
            return true;
        } else {
            return false;
        }
    }
    //登录先清空session
    private void clearSession(HttpServletRequest request){
		Enumeration em = request.getSession().getAttributeNames();
		while(em.hasMoreElements()){
			request.getSession().removeAttribute(em.nextElement().toString());
		}
	}

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@Valid UserPo user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//
//        String username = user.getUsername();
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//        //获取当前的Subject
//        Subject currentUser = SecurityUtils.getSubject();
//        try {
//            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
//            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
//            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
//            logger.info("对用户[" + username + "]进行登录验证..验证开始");
//            currentUser.login(token);
//            logger.info("对用户[" + username + "]进行登录验证..验证通过");
//        } catch (UnknownAccountException uae) {
//            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
//            redirectAttributes.addFlashAttribute("message", "未知账户");
//        } catch (IncorrectCredentialsException ice) {
//            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
//            redirectAttributes.addFlashAttribute("message", "密码不正确");
//        } catch (LockedAccountException lae) {
//            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
//            redirectAttributes.addFlashAttribute("message", "账户已锁定");
//        } catch (ExcessiveAttemptsException eae) {
//            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
//            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
//        } catch (AuthenticationException ae) {
//            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
//            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
//            ae.printStackTrace();
//            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
//        }
//        //验证是否登录成功
//        logger.info("对用户[" + username + "]进行登录验证..验证结果" + currentUser.isAuthenticated() + "");
//        if (currentUser.isAuthenticated()) {
//            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//            return "redirect:/index";
//        } else {
//            token.clear();
//            return "redirect:/login";
//        }
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(RedirectAttributes redirectAttributes) {
//        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
//        SecurityUtils.getSubject().logout();
//        redirectAttributes.addFlashAttribute("message", "您已安全退出");
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/pages/403")
//    public String unauthorizedRole() {
//        logger.info("------没有权限-------");
//        return "pages/403";
//    }
}
