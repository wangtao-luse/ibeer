package com.ibeer.dto;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 
 * @author ddd
 * @since 2017/12/26
 * 自定义requestSn
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 5059690903469850505L;
	private HttpServletRequest request;
	private String loginType;//登陆类型 1:普通的登陆 0：模拟登陆
	private String token; //1：外网，0：内网
	public MyUsernamePasswordToken(String username,char[] password,HttpServletRequest request,String token) {
		super(username, password, false, null);
		this.request = request;
		this.loginType="1";
		this.token = token;
	}
	public MyUsernamePasswordToken(String username,char[] password,HttpServletRequest request) {
		super(username, password, false, null);
		this.request = request;
		this.loginType="1";
	}
	public MyUsernamePasswordToken(String username,char[] password,HttpServletRequest request,String loginType,String token) {
		super(username, password, false, null);
		this.request = request;
		this.loginType=loginType;
		this.token = token;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
