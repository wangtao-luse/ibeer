package com.ibeer.dto.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 视频消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
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
