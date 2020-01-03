package com.ibeer.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.account.presist.ContractMapper;
import com.ibeer.api.TestApi;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;
@RestController
public class TestServiceApi implements TestApi {
	@Autowired
	ContractMapper contractMapper;
	@RequestMapping(value = "/account/test")
	public ResponseMessage test(RequestMessage requestMessage){
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		//JSONObject jsonObject = requestMessage.getBody().getContent();
		//Contract contract = jsonObject.getJSONObject("vo").toJavaObject(Contract.class);
		//List<Contract> list = jsonObject.getJSONArray("arr").toJavaList(Contract.class);
		Contract t = new Contract();
		 t.setCreateRq(new Date());
		 t.setHymc("10000");
		 t.setHydm("10000");
		 t.setHthm("Beer00001");
		 t.setHzmc("Ibeer");
		 t.setId(1L);
		 Map<String,Object> map= new HashMap<String, Object>();
		 map.put("result", t);
		responseMessage.setReturnResult(map);
		return responseMessage;
	}
	@RequestMapping(value = "/testFegin",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage testFegin() {
		// TODO Auto-generated method stub
		return ResponseMessage.getSucess().add("wangtao");
	}
	
@RequestMapping("/getContract")
 public Contract  hello() {
	 Contract queryById = contractMapper.queryById(new Long("179")); 
	 return queryById;
 }

@RequestMapping("/test1")
public ResponseMessage test1(RequestMessage requestMessage){
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONObject jsonObject = requestMessage.getBody().getContent();
	Contract contract = jsonObject.getJSONObject("vo").toJavaObject(Contract.class);
	List<Contract> list = jsonObject.getJSONArray("arr").toJavaList(Contract.class);
	
	return responseMessage;
}


}
