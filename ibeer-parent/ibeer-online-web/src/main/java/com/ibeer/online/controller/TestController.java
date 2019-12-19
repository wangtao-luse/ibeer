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
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;

@Controller
public class TestController {
	@Autowired
    RestTemplate restTemplate;

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
	   JSONObject json = restTemplate.getForObject(url, JSONObject.class);
	   //String jsonString = JSONObject.toJSONString(responseMessage);
	   Contract t = new Contract();
		 t.setCreateRq(new Date());
		 t.setHymc("10000");
		 t.setHydm("10000");
		 t.setHthm("Beer00001");
		 t.setHzmc("Ibeer");
		 t.setId(1L);
		// responseMessage.setReturnResult(t);
	   return responseMessage.add("wangtao");
}
}
