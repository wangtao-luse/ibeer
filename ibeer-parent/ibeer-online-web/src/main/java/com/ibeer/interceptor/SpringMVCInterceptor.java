package com.ibeer.interceptor;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author ddd
 * @since 2017/11/16
 * 拦截所有的controller 统计时间
 *
 */
public class SpringMVCInterceptor implements HandlerInterceptor{
	

	private Logger logger=LoggerFactory.getLogger(SpringMVCInterceptor.class);
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	/**
	 * 获取IP
	 * @param request
	 * @param response
	 * @return
	 */
	private String getRemoteHost(HttpServletRequest request, HttpServletResponse response){

	    String ip = request.getHeader("x-forwarded-for");

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getHeader("Proxy-Client-IP");

	    }

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getHeader("WL-Proxy-Client-IP");

	    }

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getRemoteAddr();

	    }

	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
}
