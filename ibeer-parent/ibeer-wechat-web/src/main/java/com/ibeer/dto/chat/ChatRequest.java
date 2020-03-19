package com.ibeer.dto.chat;

import java.util.UUID;

import com.ibeer.constant.Constant;

public class ChatRequest {
private String 	app_id;//应用标识（AppId）
private String	time_stamp;//请求时间戳（秒级）
private String	nonce_str;//随机字符串
private String	sign;//	签名信息，详见接口鉴权
private String	session;//	会话标识（应用内唯一）
private String	question;//用户输入的聊天内容

public ChatRequest( String nonce_str, String sign, String session, String question) {
	super();
	this.app_id = Constant.APPID;
	this.time_stamp = System.currentTimeMillis()/1000+"";
	this.nonce_str = getUUid();
	this.sign = sign;
	this.session = session;
	this.question = question;
}
public ChatRequest() {
	super();
}
public String getApp_id() {
	return app_id;
}
public void setApp_id(String app_id) {
	this.app_id = app_id;
}
public String getTime_stamp() {
	return time_stamp;
}
public void setTime_stamp(String time_stamp) {
	this.time_stamp = time_stamp;
}
public String getNonce_str() {
	return nonce_str;
}
public void setNonce_str(String nonce_str) {
	this.nonce_str = nonce_str;
}
public String getSign() {
	return sign;
}
public void setSign(String sign) {
	this.sign = sign;
}
public String getSession() {
	return session;
}
public void setSession(String session) {
	this.session = session;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public  String  getUUid() {
	UUID randomUUID = UUID.randomUUID();
	String uuid = randomUUID.toString();
	return uuid.replaceAll("-", "");
}

public String getReqSign() {
	//签名算法
	//1. 计算步骤
	//用于计算签名的参数在不同接口之间会有差异，但算法过程固定如下4个步骤。
	//1.将<key, value>请求参数对按key进行字典升序排序，得到有序的参数对列表N
	//2.将列表N中的参数对按URL键值对的格式拼接成字符串，得到字符串T（如：key1=value1&key2=value2），URL键值拼接过程value部分需要URL编码，URL编码算法用大写字母，例如%E8，而不是小写%e8
	//3.将应用密钥以app_key为键名，组成URL键值拼接到字符串T末尾，得到字符串S（如：key1=value1&key2=value2&app_key=密钥)
	//4.对字符串S进行MD5运算，将得到的MD5值所有字符转换成大写，得到接口请求签名
 return null;	
}
public static void main(String[] args) {
	UUID randomUUID = UUID.randomUUID();
	System.out.println("原始的："+randomUUID);
	String uuid =randomUUID.toString().replaceAll("-", "");
	System.out.println("处理过的："+uuid);
	System.out.println(uuid.length());	
}


}
