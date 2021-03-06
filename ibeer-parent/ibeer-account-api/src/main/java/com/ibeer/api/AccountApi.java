package com.ibeer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
@FeignClient(name = "ibeer-account-service")
public interface AccountApi  {
	@RequestMapping(value = "account/regSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage regSub(RequestMessage requestMessage);
	@RequestMapping(value = "account/login",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage login(RequestMessage requestMessage);
}
