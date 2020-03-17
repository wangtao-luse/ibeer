package com.ibeer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BaseMessage {
@XStreamAlias("ToUserName")
private String toUserName;
@XStreamAlias("FromUserName")
private String fromUserName;
@XStreamAlias("MsgType")
private String msgType;
public String getToUserName() {
	return toUserName;
}
public void setToUserName(String toUserName) {
	this.toUserName = toUserName;
}
public String getFromUserName() {
	return fromUserName;
}
public void setFromUserName(String fromUserName) {
	this.fromUserName = fromUserName;
}
public String getMsgType() {
	return msgType;
}
public void setMsgType(String msgType) {
	this.msgType = msgType;
}
@Override
public String toString() {
	return "BaseMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", msgType=" + msgType + "]";
}
}
