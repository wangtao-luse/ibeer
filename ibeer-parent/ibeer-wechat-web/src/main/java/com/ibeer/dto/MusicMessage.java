package com.ibeer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 音乐消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class MusicMessage {
@XStreamAlias("Music")
private Music music;

public Music getMusic() {
	return music;
}

public void setMusic(Music music) {
	this.music = music;
}
}
