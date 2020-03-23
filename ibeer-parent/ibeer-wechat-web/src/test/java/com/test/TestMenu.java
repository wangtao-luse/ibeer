package com.test;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.http.HttpURLConnectionUtil;
import com.ibeer.common.wechat.AccesstokenUtil;
import com.ibeer.dto.menu.Button;
import com.ibeer.dto.menu.ClickButton;
import com.ibeer.dto.menu.PhotoOrAlbum;
import com.ibeer.dto.menu.SubButton;
import com.ibeer.dto.menu.ViewButton;

public class TestMenu {
 public static void main(String[] args) {
	// TestMenu t = new TestMenu();
	// t.deleteMenu();
	 
	 AccesstokenUtil accesstoken = new AccesstokenUtil();
	 //String accesstoken2 = accesstoken.getAccesstoken();
	 String accesstoken2 = "31_Ishe3W6ah5sGIPKStABJZ4HfArEOkXO5SGJrI_dl6ILQLfcCQ4AeBs-qJrsJkbOlu-0y2XDmMg9GUXTsxiXzqGrlcewrGbCycgF_1XitMLjOD1iqyFVQL0Kd3jExUVr4O5_1dqXGBRLjn6k4TMPfACAKDG";
	 String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	  url=url.replace("ACCESS_TOKEN", accesstoken2);
	 //菜单对象
	Button btn = new Button();
	//第一个一级菜单
	btn.getButton().add(new ClickButton("资讯","1-1"));
	//第二个一级菜单
	btn.getButton().add(new ViewButton("商城","http://mp.weixin.qq.com"));
	//第三个一级菜单
	SubButton sub = new SubButton("创业从业");
	sub.getSub_button().add(new ClickButton("点击","31"));
	sub.getSub_button().add(new ViewButton("百度","http://www.baidu.com"));
	sub.getSub_button().add(new PhotoOrAlbum("传图","3-3"));
	//加入第三个一级菜单加入子菜后不能创建成功待解决
	btn.getButton().add(sub);
	String jsonString = JSONObject.toJSONString(btn);
	System.out.println(jsonString);
	String visitPost = HttpURLConnectionUtil.visitPost(url, "", jsonString);
	System.out.println("返回信息："+visitPost);
}

}
