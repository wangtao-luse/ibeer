package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ibeer.dto.msg.Articles;
import com.ibeer.dto.msg.Image;
import com.ibeer.dto.msg.ImageMessage;
import com.ibeer.dto.msg.NewsMessage;
import com.ibeer.dto.msg.TextMessage;
import com.thoughtworks.xstream.XStream;

public class TestMsg {
	public static void main(String[] args) {
		testMsg();
	}
    public static boolean testMsg() {
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("ToUserName", "to");
    	map.put("FromUserName", "from");
    	map.put("MsgType", "type");
    	List<Articles> list= new ArrayList<Articles>();
    	Articles item =new Articles();
    	item.setTitle("title");
    	item.setDescription("description");
    	item.setPicurl("picurl");
    	item.setUrl("url");
    	
    	list.add(item);
    	NewsMessage textMessage = new NewsMessage(map, list); 
    	ImageMessage imageMessage = new ImageMessage(map, new Image("001"));
    	XStream xstream = new XStream();
    	xstream.processAnnotations(NewsMessage.class);
    	xstream.processAnnotations(ImageMessage.class);
    	String xml = xstream.toXML(imageMessage);
    	System.out.println(xml);
    	return true;
    }
}
