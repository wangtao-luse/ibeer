package com.ibeer.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class PhotoOrAlbum extends AbstractButton {
private String type="pic_photo_or_album";
private String key;
private List<SubButton> sub_button = new ArrayList<SubButton>();
public PhotoOrAlbum() {
	super();
}
public PhotoOrAlbum(String name,String key) {
	super(name);
	this.key = key;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public List<SubButton> getSub_button() {
	return sub_button;
}
public void setSub_button(List<SubButton> sub_button) {
	this.sub_button = sub_button;
}

}
