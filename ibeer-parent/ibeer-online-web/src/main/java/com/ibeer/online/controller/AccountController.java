package com.ibeer.online.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.resp.ResponseMessage;

@Controller
public class AccountController {
	@Autowired
    RestTemplate restTemplate;
	/**
	 * 注册页面
	 * @return
	 */
   @RequestMapping("/register")
	public String registerPage() {	   
		return "";
	}
  
   
}
