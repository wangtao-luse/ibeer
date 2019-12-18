package com.ibeer.online.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.resp.ResponseMessage;

@Controller
public class TestController {
	@Autowired
    RestTemplate restTemplate;

@RequestMapping("test/hello")
@ResponseBody
public String hello() {
	return "hello";
}

@RequestMapping(value = "test/getTest",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage getResponseMessage(@RequestBody(required = false) JSONObject jsonObject,HttpServletRequest request) {
	   ResponseMessage responseMessage = ResponseMessage.getSucess();
	   String url="http://ibeer-account-service/account/test";
	   responseMessage = (ResponseMessage)restTemplate.getForObject(url, ResponseMessage.class);
	   return responseMessage;
}
}
