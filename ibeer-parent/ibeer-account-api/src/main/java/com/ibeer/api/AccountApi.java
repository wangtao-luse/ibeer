package com.ibeer.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
@FeignClient(name = "ibeer-account-service")
public interface AccountApi {
	@RequestMapping(value = "/testFegin",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage testFegin(RequestMessage requestMessage,HttpServletRequest request);

}
