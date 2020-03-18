package com.ibeer.dto;

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

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}
}
