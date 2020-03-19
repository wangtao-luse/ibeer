package com.ibeer.dto.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Image {
@XStreamAlias("MediaId")
private String	mediaId;

public Image(String mediaId) {
	super();
	this.mediaId = mediaId;
}

public Image() {
	super();
}

public String getMediaId() {
	return mediaId;
}

public void setMediaId(String mediaId) {
	this.mediaId = mediaId;
}

}
