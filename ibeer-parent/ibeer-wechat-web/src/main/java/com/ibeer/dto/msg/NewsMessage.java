package com.ibeer.dto.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *   图文消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
/**
 * 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
 */
@XStreamAlias("ArticleCount")	
private String articleCount;
@XStreamAlias("Articles")
private List<Articles> articles = new ArrayList<Articles>();

public NewsMessage() {
	super();
}

public NewsMessage(Map<String,String> map , List<Articles> articles) {
	super(map);
	this.articleCount = articles.size()+"";
	this.articles = articles;
	this.setMsgType("news");
}

public List<Articles> getArticles() {
	return articles;
}
public void setArticles(List<Articles> articles) {
	this.articles = articles;
}
public String getArticleCount() {
	return articleCount;
}
public void setArticleCount(String articleCount) {
	this.articleCount = articleCount;
}


}
