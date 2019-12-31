package com.ibeer.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.req.RequestBody;
import com.ibeer.common.req.RequestHeader;
import com.ibeer.common.req.RequestMessage;


public class HttpDateUtil {

public static RequestMessage postData(String functionMethod,JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = new RequestMessage();
	RequestHeader header = new RequestHeader();
	header.setFunctionMethod(functionMethod);
	requestMessage.setRequestHeader(header);
	
	RequestBody body = new RequestBody();
	body.setContent(content);
	requestMessage.setBody(body);
	return requestMessage;
}
}
