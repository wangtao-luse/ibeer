package com.ibeer.online.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.conector.AccountConnector;
import com.ibeer.dto.MyUsernamePasswordToken;

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
	public String registerPage(String source,ModelMap model,HttpServletRequest request) {	
	   //生成UUID(相当于一个令牌)
	    String randomUUID = UUID.randomUUID().toString();
	    request.getSession().setAttribute("uuid", randomUUID);
	   model.addAttribute("uuid", randomUUID);
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
	   try {
		 //比较UUID
			  String uuid= jsonObject.getString("uuid");
			  String attr =(String) request.getSession().getAttribute("uuid");
			  if(!StringUtils.isEmpty(attr)&&uuid.equals(attr)) {//UUID相同		 
				  //校验用户名(昵称)
				  String nickname = jsonObject.getString("nickname");
				  if(StringUtils.isEmpty(nickname)) {
					  return ResponseMessage.getFailed("用户名不能为空！");
				  }
				  //校验信息密码
				  String pwdRepeat = jsonObject.getString("pwdRepeat");
				  String credential = jsonObject.getString("credential");
				  if(!credential.equals(pwdRepeat)) {
					  return ResponseMessage.getFailed("两次密码不一致");
				  }
				  String str = UUID.randomUUID().toString();
				  request.getSession().setAttribute("uuid", str);
				return accountConnector.regSub(jsonObject, request);  
			  }else {
				  return ResponseMessage.getFailed("请不要重复提交！");
			  }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(ConstantBase.FAILED_SYSTEM_ERROR);
	}
	 
   }
   /**
    * 登录页面
    * @return
    */
   @RequestMapping("/loginPage")
   public String loginPage() {
	   return "/account/login/login";
   }
   @RequestMapping("/logout")
   public String logout() {
	   return "/account/login/login";
   }
   /**
    * 登录提交
    * @param jsonObject
    * @param request
    * @return
    */
   @RequestMapping(value = "/login")
   @ResponseBody
	public ResponseMessage login(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
		try {
			String uname = jsonObject.getString("loginname");
			String pwd = jsonObject.getString("nloginpwd");
			// 获取当前的Subject
			Subject currenAccount = SecurityUtils.getSubject();
			char[] charArray = pwd.toCharArray();
			MyUsernamePasswordToken token = new MyUsernamePasswordToken(uname,charArray , request);
			currenAccount.login(token);

		} catch (UnknownAccountException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed("账户名与密码不匹配，请重新输入");
		} catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed("账户名与密码不匹配，请重新输入");
		}

		return ResponseMessage.getSucess();
	}
   @RequestMapping("/surveyPage")
   public String surveyPage() {
	   return "/account/login/survey";
   }
  
   
}
