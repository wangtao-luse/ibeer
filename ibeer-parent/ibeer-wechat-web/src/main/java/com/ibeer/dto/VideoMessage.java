package com.ibeer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 视频消息
 * @author wwang
 *
 */
public class VideoMessage extends BaseMessage {
@XStreamAlias("Video")
private Video video;

public Video getVideo() {
	return video;
}

public void setVideo(Video video) {
	this.video = video;
}
}
