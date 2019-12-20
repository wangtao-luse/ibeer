package com.ibeer.online.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.api.TestApi;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;

@Controller
public class TestController {
	@Autowired
    RestTemplate restTemplate;
	@Autowired
	TestApi testApi;

@RequestMapping("test/hello")
@ResponseBody
public String hello() {
	return "hello";
}
@ResponseBody
@RequestMapping(value = "test/getTest",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getResponseMessage(@RequestBody(required = false) JSONObject jsonObject,HttpServletRequest request) {
	   ResponseMessage responseMessage = ResponseMessage.getSucess();
	   //fegin rest
	   String url="http://ibeer-account-service/account/test";	   
	    responseMessage = restTemplate.getForObject(url, JSONObject.class).toJavaObject(ResponseMessage.class);
	   
	  
	   return responseMessage.add("wangtao");
}


@RequestMapping(value = "test/getMsg",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage testFegin() {
	ResponseMessage resp = ResponseMessage.getSucess();
	resp.setReturnResult("333");
	ResponseMessage testFegin = testApi.testFegin();
	return testFegin;
}
}
