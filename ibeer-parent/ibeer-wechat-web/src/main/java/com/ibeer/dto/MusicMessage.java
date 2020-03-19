package com.ibeer.dto;


import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 音乐消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{
@XStreamAlias("Music")
private Music music;

public MusicMessage() {
	super();
}

public MusicMessage(Map<String,String> requestMap,Music music) {
	super(requestMap);	
	this.setMsgType("music");
	this.music=music;
}

public Music getMusic() {
	return music;
}

public void setMusic(Music music) {
	this.music = music;
}
}