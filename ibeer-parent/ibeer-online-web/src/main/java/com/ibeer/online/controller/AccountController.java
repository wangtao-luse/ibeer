package com.ibeer.online.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.conector.AccountConnector;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountConnector accountConnector;
	/**
	 * 注册页面
	 * @return
	 */
   @RequestMapping("/regPage")
	public String registerPage(String source) {	
	   if("buser".equals(source)) {
		   return "/account/reg/company-register";
	   }else {
		   return "/account/reg/register-person"; 
	   }
		
	}
  //注册信息提交
   @RequestMapping("/regSub")
   @ResponseBody
   public ResponseMessage regSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	   ResponseMessage regSub = accountConnector.regSub(jsonObject, request);
	  return regSub; 
   }
   /**
    * 登录页面
    * @return
    */
   @RequestMapping("/loginPage")
   public String loginPage() {
	   return "/account/login/login";
   }
   @RequestMapping("/login")
   public ResponseMessage login(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
	   try {
		   String uname = jsonObject.getString("uname");
		   String pwd = jsonObject.getString("pwd");
		   // 获取当前的Subject
	    Subject currenAccount = SecurityUtils.getSubject();
	    UsernamePasswordToken  token = new UsernamePasswordToken(uname,pwd.toCharArray());
		currenAccount.login(token);
		
	} catch (UnknownAccountException  e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed("账号不存在！");
	}catch (IncorrectCredentialsException  e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed("密码不正确！");
	}
	
	   return ResponseMessage.getSucess();
   }
   @RequestMapping("/surveyPage")
   public String surveyPage() {
	   return "/account/login/survey";
   }
  
   
}
