package com.ibeer.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.account.presist.ContractMapper;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;

@RestController
public class AccountServiceApi {
	@Autowired
	ContractMapper contractMapper;
@RequestMapping("/getContract")
 public Contract  hello() {
	 Contract queryById = contractMapper.queryById(new Long("179")); 
	 return queryById;
 }

@RequestMapping("/test")
public ResponseMessage test(RequestMessage requestMessage){
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONObject jsonObject = requestMessage.getBody().getContent();
	Contract contract = jsonObject.getJSONObject("vo").toJavaObject(Contract.class);
	List<Contract> list = jsonObject.getJSONArray("arr").toJavaList(Contract.class);
	
	return responseMessage;
}
}
