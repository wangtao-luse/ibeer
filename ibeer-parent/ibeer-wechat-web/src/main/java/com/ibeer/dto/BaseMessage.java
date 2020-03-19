package com.ibeer.dto;

import java.util.Map;

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
 * 消息类型，文本为text,图片为image,语音为voice,视频为video,小视频消息为shortvideo,地理位置消息为location,链接消息为link;
 */
@XStreamAlias("MsgType")
private String msgType;
@XStreamAlias("CreateTime")
private String createTime;
public BaseMessage() {
	super();
}
public BaseMessage(Map<String,String> requestMap) {
	super();
	this.toUserName=requestMap.get("ToUserName");
	this.fromUserName=requestMap.get("FromUserName");
	this.createTime=System.currentTimeMillis()/1000+"";
}
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
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}


}
