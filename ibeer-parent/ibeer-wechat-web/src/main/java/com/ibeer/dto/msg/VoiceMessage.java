package com.ibeer.dto.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
   * 语音消息
 * @author wwang
 *
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
@XStreamAlias("Voice")
private Voice voice;

public Voice getVoice() {
	return voice;
}

public void setVoice(Voice voice) {
	this.voice = voice;
}
}
