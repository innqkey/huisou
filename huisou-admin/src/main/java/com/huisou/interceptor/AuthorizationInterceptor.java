package com.huisou.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.FileUtil;
import com.common.MD5Util;
import com.common.ResUtils;
import com.huisou.constant.ContextConstant;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.UserPo;


@Configuration
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private  static  final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor. class);
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		logger.info("====权限拦截器===="+handler);
		//token是否开启，1开启、2关闭
		if("2".equals(FileUtil.getApplicationPro("http.token"))){
    		return true;
    	}
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getName().equals("login") || method.getName().equals("custLogin")){
        	return true;
        }
        boolean isChecked = checkToken(request);
        if(!isChecked){
        	writeContent(ResUtils.tokenErrRes());
        }
		return isChecked;
	}
	
	public boolean checkToken(HttpServletRequest request){
		String token = "";
		//token存放header里
		if("1".equals(FileUtil.getApplicationPro("http.token"))){
			token = request.getHeader(ContextConstant.SESSION_TOKEN);
			if(StringUtils.isBlank(token)){
				token = request.getParameter(ContextConstant.SESSION_TOKEN);
			}
    	}
		logger.info("====token===="+token);
		String loginSign = request.getParameter("loginSign");
		if(StringUtils.isNotBlank(loginSign)&&ContextConstant.CUST_SESSION.equals(loginSign)){
			return null!=(CustomerUserPo)request.getSession().getAttribute(ContextConstant.CUST_SESSION+token);
		}else{
			return null!=(UserPo)request.getSession().getAttribute(ContextConstant.USER_SESSION+token);
		}
		
	}
	
	private void writeContent(String content) {
    	HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    	response.reset();
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Type", "text/plain;charset=UTF-8");
    	response.setHeader("icop-content-type", "exception");
    	PrintWriter writer = null;
    	try {
    		writer = response.getWriter();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	writer.print(content);
    	writer.flush();
    	writer.close();
     }
}
