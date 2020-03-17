package com.ibeer.online.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibeer.common.resp.ResponseMessage;
import com.sun.xml.fastinfoset.sax.SAXDocumentParser;
@Controller
public class WechatOfficialController {
	//需要和测试的token一致
	private static final String TOKEN="test";	
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
	    if(check(timestamp,nonce,signature,TOKEN)) {
	    	PrintWriter writer = response.getWriter();
	    	writer.write(echostr);
	    	writer.flush();
	    	writer.close();
	    	System.out.println("接入成功");
	    	
	    	
	    }else {
	    	 System.out.println("接入失败");
	    }
	    return "index";
		
	}
	/**
	 *  验证消息的确来自微信服务器
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param token
	 * @return
	 */
	private boolean check(String timestamp, String nonce, String signature,String token) {
		//1.将token、timestamp、nonce三个参数进行字典序排序   
		  String [] str = {token,timestamp,nonce};
		  Arrays.sort(str);
		  //2）将三个参数字符串拼接成一个字符串进行sha1加密 
		  String strs = str[0]+str[1]+str[2];
		  String mysig= sha1(strs);
		  System.out.println("mysig:---->"+mysig);
		  //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		 return mysig.equals(signature);
	}
     /**
      * 加密处理
      * @param strs
      * @return
      */
	private String sha1(String strs) {
		// TODO Auto-generated method stub
		try {
			//获取加密对象
			MessageDigest md = MessageDigest.getInstance("sha1");
			//加密处理
		    byte[] digest = md.digest(strs.getBytes());
		    //处理加密结果
		    char [] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		    StringBuilder sb = new StringBuilder();
		    for (byte b : digest) {
			 sb.append((chars)[(b>>4)&15]);
			 sb.append(chars[b&15]);
			}
		    return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public ResponseMessage receiveMessage(HttpServletRequest request) throws IOException, DocumentException {
		//打印信息
		ServletInputStream inputStream = request.getInputStream();
		byte [] by = new byte[1024];
		int len;
		StringBuffer sb = new StringBuffer();
		while((len=inputStream.read(by))!=-1) {
			sb.append(new String(by,0,len));
		}
		System.out.println(sb.toString());
		//接受用户信息
		Map<String,String>  map=parseRequest(request.getInputStream());
		return null;
	}
	/**
	 * 解析xml数据包
	 * @param input
	 * @return
	 * @throws DocumentException 
	 */
	private Map<String, String> parseRequest(InputStream input) throws DocumentException {
		// TODO Auto-generated method stub
		Map map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		//读取输入流，获取文本对象
		Document read = saxReader.read(input);
		//根据文档对象获取根节点
		Element rootElement = read.getRootElement();
		//获取根节点下的所有节点
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			map.put(element.getName(), element.getStringValue());
		}
		return map;
	}
	
}
