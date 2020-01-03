package com.ibeer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibeer.common.resp.ResponseMessage;
//@FeignClient(name = "ibeer-account-service")
public interface TestApi {
//	@RequestMapping(value = "/testFegin",produces = {"application/json;charset=UTF-8"})
	//public ResponseMessage testFegin();

}
