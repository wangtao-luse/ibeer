package com.ibeer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibeer.connector.WechatConnector;
import com.ibeer.dto.chat.ChatRequest;
import com.ibeer.dto.msg.TextMessage;
@Controller
public class WechatOfficialController {
	//需要和测试的token一致
	private static final String TOKEN="test";	
	@Autowired
	private WechatConnector wechatConnector;
	/**
	 * 1.开发接口接入
	 * 接入微信公众平台开发，开发者需要按照如下步骤完成：
		1、填写服务器配置
		2、验证服务器地址的有效性
		3、依据接口文档实现业务逻辑
		下面详细介绍这3个步骤。
			第一步：填写服务器配置
			第二步：验证消息的确来自微信服务器
			第三步：依据接口文档实现业务逻辑
	 * 
	 */
	@RequestMapping(value="/start",method = RequestMethod.GET)
	public String start(HttpServletRequest request,HttpServletResponse response) throws IOException {	
	    String signature = request.getParameter("signature");
	    String timestamp = request.getParameter("timestamp");
	    String nonce = request.getParameter("nonce");
	    String echostr = request.getParameter("echostr");
	    System.out.println(signature+"\n"+timestamp+"\n"+nonce+"\n"+echostr);   
	    if(wechatConnector.check(timestamp,nonce,signature,TOKEN)) {
	    	PrintWriter writer = response.getWriter();
	    	writer.write(echostr);
	    	writer.flush();
	    	writer.close();
	    	System.out.println("接入成功");
	    	
	    	
	    }else {
	    	 System.out.println("接入失败");
	    }
	    return "";
		
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
		//接受用户发的信息
		Map<String,String>  map = wechatConnector.parseRequest(request.getInputStream());

		//处理用户发的信息
		String xml = wechatConnector.getResponse(map);
		//将处理的消息返回用户的消息
		PrintWriter pw = response.getWriter();
		pw.write(xml);
		pw.flush();
		pw.close();

		
	}
	
    	

}
