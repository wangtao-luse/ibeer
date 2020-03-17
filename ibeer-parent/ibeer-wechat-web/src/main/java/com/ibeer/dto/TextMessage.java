package com.ibeer.dto;

import java.util.HashMap;
import java.util.Map;

public class TextMessage  extends BaseMessage{
private String cotent;
private Map map = new HashMap<String, String>();

public TextMessage() {
	super();
}

public TextMessage(Map map,String cotent) {
	super();
	this.cotent = cotent;
	this.map=map;
}

public String getCotent() {
	return cotent;
}

public void setCotent(String cotent) {
	this.cotent = cotent;
}

public Map getMap() {
	return map;
}

public void setMap(Map map) {
	this.map = map;
}

}
