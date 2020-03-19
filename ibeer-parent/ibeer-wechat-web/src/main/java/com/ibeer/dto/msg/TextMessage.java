package com.ibeer.dto.msg;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 文本消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class TextMessage  extends BaseMessage{
/**
 * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
 */
@XStreamAlias("Content")
private String cotent;

public TextMessage() {
	super();
}


public TextMessage(Map map,String cotent) {
	super(map);	
	this.setMsgType("text");
	this.cotent = cotent;
	
	
}
public String getCotent() {
	return cotent;
}

public void setCotent(String cotent) {
	this.cotent = cotent;
}



}
