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


public ImageMessage(Map<String,String> requestMap,Image image) {
	super(requestMap);
	this.image = image;
}

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}
}
