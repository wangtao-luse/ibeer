package com.ibeer.dto;

import java.util.Map;

/**
 * 音乐消息
 * @author wwang
 *
 */
public class MusicMessage extends BaseMessage{
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
