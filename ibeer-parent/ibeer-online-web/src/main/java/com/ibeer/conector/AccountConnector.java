package com.ibeer.conector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.api.AccountApi;
import com.ibeer.common.HttpDateUtil;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;

@Service
public class AccountConnector {
	@Autowired
 AccountApi accountApi;
public ResponseMessage regSub(JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDateUtil.postData(content, request);
	ResponseMessage regSub = accountApi.regSub(requestMessage);
	return regSub;
}
public ResponseMessage login(@RequestBody JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDateUtil.postData(content, request);
	ResponseMessage login = accountApi.login(requestMessage);
	return login;
}
}
