package com.ibeer.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.req.RequestBody;
import com.ibeer.common.req.RequestHeader;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.util.SessionUtil;


public class HttpDateUtil {

public static RequestMessage postData(JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = new RequestMessage();
	RequestHeader header = new RequestHeader();
	header.setRemoteAddr(request.getRemoteAddr());
	SessionUtil.getSessionUser();

	requestMessage.setRequestHeader(header);
	
	RequestBody body = new RequestBody();
	body.setContent(content);
	requestMessage.setBody(body);
	return requestMessage;
}
}
