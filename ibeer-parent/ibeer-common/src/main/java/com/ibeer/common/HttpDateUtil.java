package com.ibeer.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.req.RequestBody;
import com.ibeer.common.req.RequestHeader;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.dto.UserV;
import com.ibeer.util.SessionUtil;


public class HttpDateUtil {

public static RequestMessage postData(JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = new RequestMessage();
	RequestHeader header = new RequestHeader();
	header.setRemoteAddr(getRemoteHost(request));
	requestMessage.setRequestHeader(header);	
	RequestBody body = new RequestBody();
	UserV sessionUser = SessionUtil.getSessionUser();
	if(null != sessionUser) {
		body.setUId(sessionUser.getUId());
		body.setNickname(sessionUser.getNickname());
		body.setAvatar(sessionUser.getAvatar());
		body.setOauthType(sessionUser.getOauthType());	
	}
	body.setContent(content);
	requestMessage.setBody(body);
	return requestMessage;
}
/**
 * 获取IP
 * @param request
 * @param response
 * @return
 */
public static String getRemoteHost(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("WL-Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getRemoteAddr();
    }
    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
}
}
