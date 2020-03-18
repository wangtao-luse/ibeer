package com.ibeer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BaseMessage {
/**
 * 接收方帐号（收到的OpenID）
 */
@XStreamAlias("ToUserName")
private String toUserName;
/**
 * 开发者微信号
 */
@XStreamAlias("FromUserName")
private String fromUserName;
/**
 * 消息类型，文本为text,图片为image,语音为voice
 */
@XStreamAlias("MsgType")
private String msgType;
@XStreamAlias("CreateTime")
private Long createTime;
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

public Long getCreateTime() {
	return createTime;
}
public void setCreateTime(Long createTime) {
	this.createTime = createTime;
}
@Override
public String toString() {
	return "BaseMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", msgType=" + msgType + "]";
}
}
