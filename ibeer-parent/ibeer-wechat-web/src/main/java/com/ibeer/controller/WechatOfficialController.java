package com.ibeer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.http.HttpURLConnectionUtil;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.common.wechat.AccesstokenUtil;
import com.ibeer.common.wechat.WechatUtil;
import com.ibeer.connector.MessageConnector;
import com.ibeer.connector.WechatConnector;
import com.ibeer.dto.menu.Button;
import com.ibeer.dto.menu.ClickButton;
import com.ibeer.dto.menu.PhotoOrAlbum;
import com.ibeer.dto.menu.SubButton;
import com.ibeer.dto.menu.ViewButton;
@Controller
public class WechatOfficialController {
	@Autowired
	private WechatConnector wechatConnector;
	@Autowired
	private MessageConnector messageConnector;
	/**
	 * 接入微信公众平台开发
	 * 1.填写服务器配置;
	 *   a.开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上;
	 * 2.验证消息的确来自微信服务器
	 *   a.开发者通过检验signature对请求进行校验,确认此次GET请求来自微信服务器，
	   *              请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败;
	 * 3.第三步：依据接口文档实现业务逻辑;  
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/start",method = RequestMethod.GET)
	public void start(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		//验证消息的确来自微信服务器
		if(WechatUtil.check(request)) {	    	
	    	PrintWriter writer = response.getWriter();
	    	writer.write( request.getParameter("echostr"));
	    	writer.flush();
	    	writer.close();
	    	System.out.println("接入成功");
	    	
	    }else {
	    	 System.out.println("接入失败");
	    }
		
	}
	
    
	/**
	 *   2. 接受用户消息
	 *     1.当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上;
	 *     2.微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。假如服务器无法保证在五秒内处理并回复，
	 *                   可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试;
	 *     3.开发者在5秒内未回复任何内容,微信都会在公众号会话中，向用户下发系统提示“该公众号暂时无法提供服务，请稍后再试”
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException 
	 */
	@RequestMapping(value="/start",method = RequestMethod.POST)
	public void receiveMessage(HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException {
		//打印信息
		/*ServletInputStream inputStream = request.getInputStream();
		byte [] by = new byte[1024];
		int len;
		StringBuffer sb = new StringBuffer();
		while((len=inputStream.read(by))!=-1) {
			sb.append(new String(by,0,len));
		}
		System.out.println(sb.toString());*/
		//接受用户发的信息 (微信服务器推送的xml转为map)
		Map<String,String>  map = WechatUtil.parseRequest(request.getInputStream());
		//处理用户发的信息(根据不同的消息回复消息且生成对应的xml发给微信服务器)
		String xml = messageConnector.getResponse(map);
		//将处理的消息返回用户的消息(返回微信服务器)
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(xml);
		pw.flush();
		pw.close();

		
	}
	
	
	/**
	 * 自定义菜单
	 * @return
	 */
	@RequestMapping("/createMenu")
	public ResponseMessage createMenu() {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
			String accesstoken = AccesstokenUtil.getAccesstoken();
			url = url.replace("ACCESS_TOKEN", accesstoken);
			// 菜单对象
			Button btn = new Button();
			// 第一个一级菜单
			btn.getButton().add(new ClickButton("一级点击菜单", "1-1"));
			// 第二个一级菜单
			btn.getButton().add(new ViewButton("一级跳转菜单", "http://www.baidu.com"));
			// 第三个一级菜单
			SubButton sub = new SubButton("菜单");
			sub.getSub_button().add(new ClickButton("二级点击菜单", "3-1"));
			sub.getSub_button().add(new ViewButton("二级跳转菜单", "http://www.csesteel.com"));
			sub.getSub_button().add(new PhotoOrAlbum("传图片", "3-3"));
			// 加入第三个一级菜单
			btn.getButton().add(sub);

			String jsonString = JSONObject.toJSONString(btn);
			System.out.println(jsonString);
			String visitPost = HttpURLConnectionUtil.visitPost(url, "", jsonString);
			responseMessage.setReturnResult(visitPost);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return responseMessage.getFailed();
		}

		return responseMessage;
	}  	
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping(value ="/deleteMenu")
	public ResponseMessage deleteMenu() {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
			String accesstoken = AccesstokenUtil.getAccesstoken();
			url = url.replace("ACCESS_TOKEN", accesstoken);
			String visitGet = HttpURLConnectionUtil.visitGet(url, "");
			responseMessage.setReturnResult(visitGet);
			System.out.println("delete返回信息：" + visitGet);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return responseMessage.getSucess();
		}

		return responseMessage;
	}
	/**
	 * 查看自定义菜单
	 * @return
	 */
	@RequestMapping(value="/queryMenu",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage queryMenu() {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			String url ="https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
			 String accesstoken = AccesstokenUtil.getAccesstoken();
			 url=url.replace("ACCESS_TOKEN", accesstoken);
			 String visitGet = HttpURLConnectionUtil.visitGet(url, "");
			 responseMessage.setReturnResult(visitGet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return responseMessage.getFailed();
		}
		
		return responseMessage;
	}
	

}
