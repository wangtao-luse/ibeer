package com.ibeer.common.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

@Service
public class WechatUtil {
	
//需要和测试的token一致
private static final String TOKEN="test";
	
	
	/**
	 *  验证消息的确来自微信服务器
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param token
	 * @return
	 */
	public boolean check(HttpServletRequest request) {
		 //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		//时间戳
	    String timestamp = request.getParameter("timestamp");
	    //随机数
	    String nonce = request.getParameter("nonce");
	    //随机字符串
	    String echostr = request.getParameter("echostr");
	    System.out.println(signature+"\n"+timestamp+"\n"+nonce+"\n"+echostr);
		//1.将token、timestamp、nonce三个参数进行字典序排序   
		  String [] str = {TOKEN,timestamp,nonce};
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
	public String sha1(String strs) {
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
	 * 解析xml数据包
	 * @param input
	 * @return
	 * @throws DocumentException 
	 */
	public Map<String, String> parseRequest(InputStream input)  {
		Map map = new HashMap<String, String>();
		try {
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
			input.close();
			System.out.println(map);
		}  catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
}
