package com.ibeer.dto;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 图片消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
@XStreamAlias("Image")
private Image image;

public ImageMessage() {
	super();
}

public ImageMessage(Map<String,String> map,Image image) {
	super();
	this.image = image;
	this.setCreateTime(Long.valueOf(map.get("CreateTime")));
	this.setFromUserName(map.get("FromUserName"));
	this.setToUserName(map.get("ToUserName"));
	this.setMsgType(map.get("MsgType"));
	this.setCreateTime(Long.valueOf(map.get("CreateTime")));
}

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}
}
