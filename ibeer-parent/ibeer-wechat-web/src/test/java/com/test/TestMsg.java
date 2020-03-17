package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ibeer.dto.TextMessage;
import com.thoughtworks.xstream.XStream;

public class TestMsg {
	@Test
    public boolean estMsg() {
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("ToUserName", "to");
    	map.put("FromUserName", "from");
    	map.put("MsgType", "type");
    	TextMessage textMessage = new TextMessage(map, "你好");
    	XStream xstream = new XStream();
    	String xml = xstream.toXML(map);
    	System.out.println(xml);
    	return true;
    }
}
