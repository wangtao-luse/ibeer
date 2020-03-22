package com.ibeer.common.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.HttpURLConnectionUtil;
import com.ibeer.constant.Constant;
import com.ibeer.dto.Accesstoken;
@Service
public class AccesstokenUtil {
	@Autowired
	HttpURLConnectionUtil httpURLConnectionUtil = new HttpURLConnectionUtil();
	private   Accesstoken accesstoken=null;
	private   void  getToken() {
		
		String serverurl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		serverurl=serverurl.replace("APPID", Constant.APPID).replace("APPSECRET", Constant.APPKEY);	
		String visitGet = httpURLConnectionUtil.visitGet(serverurl, "");
		
		JSONObject jsonObject =JSONObject.parseObject(visitGet);
		String access_token = jsonObject.getString("access_token");
		String expires_in = jsonObject.getString("expires_in");
		//将token存入
		 accesstoken = new Accesstoken(access_token, expires_in);
	}
	public  String getAccesstoken() {
		if(accesstoken==null||accesstoken.isExpires()) {
			 getToken();
			 
			 return accesstoken.getAccess_token();
		}
		System.out.println("Access_token------>"+accesstoken.getAccess_token());
		return null;
	}
}

