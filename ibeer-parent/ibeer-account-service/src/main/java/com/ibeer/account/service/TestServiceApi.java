package com.ibeer.account.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;
@RestController
public class TestServiceApi {
	@RequestMapping("/account/test")
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
		responseMessage.setReturnResult(t);
		return responseMessage;
	}

}
