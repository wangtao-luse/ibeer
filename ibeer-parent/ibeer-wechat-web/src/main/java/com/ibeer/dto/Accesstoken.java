package com.ibeer.dto;

public class Accesstoken {
private String access_token;
private long expires_time;
public Accesstoken() {
	super();
}
public Accesstoken(String access_token, String expires_in) {
	super();
	this.access_token = access_token;
	this.expires_time=System.currentTimeMillis()+Integer.valueOf(expires_in)*1000;
}
public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}
public long getExpires_time() {
	return expires_time;
}
public void setExpires_time(long expires_time) {
	this.expires_time = expires_time;
}
/**
 * 判断token是否过期;
 * @return
 */
public boolean isExpires() {
	return System.currentTimeMillis()>expires_time;
}

}
