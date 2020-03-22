package com.ibeer.dto.menu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright 2020 bejson.com 
 */

/**
* Auto-generated: 2020-03-20 12:2:56
*
* @author bejson.com (i@bejson.com)
* @website http://www.bejson.com/java2pojo/
*/
public class Button {

private List<AbstractButton> button = new ArrayList<AbstractButton>();

public Button() {
	super();
}

public Button(List<AbstractButton> button) {
	super();
	this.button = button;
}

public List<AbstractButton> getButton() {
	return button;
}

public void setButton(List<AbstractButton> button) {
	this.button = button;
}
public static void main(String[] args) {
	Button btn = new Button();
	btn.getButton().add(new ClickButton("key", "name"));
	btn.getButton().add(new ClickButton("key1", "name1"));
	String jsonString = JSONObject.toJSONString(btn);
	System.out.println(jsonString);
}

}