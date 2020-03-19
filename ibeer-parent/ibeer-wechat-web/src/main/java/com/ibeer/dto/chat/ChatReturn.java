package com.ibeer.dto.chat;

public class ChatReturn {
private String	ret;//	返回码； 0表示成功，非0表示出错
private String	msg;//		返回信息；ret非0时表示出错时错误原因
private String	data;//	返回数据；ret为0时有意义
private String	session;//UTF-8编码，非空且长度上限32字节
private String	answer;//UTF-8编码，非空
public String getRet() {
	return ret;
}
public void setRet(String ret) {
	this.ret = ret;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getSession() {
	return session;
}
public void setSession(String session) {
	this.session = session;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
}
