package com.ibeer.dto.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("Item")
public class Articles {
/**
 * 图文消息标题
 */
@XStreamAlias("Title")
private String title;
/**
 * 图文消息描述
 */
@XStreamAlias("Description")
private String description;
/**
 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
 */
@XStreamAlias("Picurl")
private String picurl;
/**
 * 点击图文消息跳转链接
 */
@XStreamAlias("Url")
private String url;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPicurl() {
	return picurl;
}
public void setPicurl(String picurl) {
	this.picurl = picurl;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}


}
