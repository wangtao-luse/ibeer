package com.ibeer.dto.menu;

import java.util.ArrayList;
/**
 * Copyright 2020 bejson.com 
 */
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
* Auto-generated: 2020-03-20 12:2:56
*
* @author bejson.com (i@bejson.com)
* @website http://www.bejson.com/java2pojo/
*/
public class ButtonRootBean {

   private List<Button> button;
   public void setButton(List<Button> button) {
        this.button = button;
    }
    public List<Button> getButton() {
        return button;
    }
 public static void main(String[] args) {
	 List<Button> list = new ArrayList<Button>();
	 Button btn = new Button();
	 btn.setKey("key");
	 btn.setName("name");
	 btn.setType("type");
	 list.add(btn);
	 ButtonRootBean btnroot = new ButtonRootBean();
	 btnroot.setButton(list);
	 String jsonString = JSONObject.toJSONString(btnroot);
	 System.out.println(jsonString);
	 
	 
}
}