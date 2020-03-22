package com.ibeer.common.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibeer.dto.testjson.Hobby;
import com.ibeer.dto.testjson.Person;

public class JsonToJavaObject {
public static void main(String[] args) {
	Person p = new Person("001", "mike");
	 Hobby h = new Hobby();
	 	   h.setId("007");
	       h.setHobby("看电影");
	       p.getHobby().add(h);
	String jsonString = JSONObject.toJSONString(p);
	System.out.println(jsonString);
	String path="D:\\temp\\test\\";
	String className="Button";
	String packageName="com.ibeer.model";
	jsonString ="{\r\n" + 
			"     \"button\":[\r\n" + 
			"     {	\r\n" + 
			"          \"type\":\"click\",\r\n" + 
			"          \"name\":\"今日歌曲\",\r\n" + 
			"          \"key\":\"V1001_TODAY_MUSIC\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"           \"name\":\"菜单\",\r\n" + 
			"           \"sub_button\":[\r\n" + 
			"           {	\r\n" + 
			"               \"type\":\"view\",\r\n" + 
			"               \"name\":\"搜索\",\r\n" + 
			"               \"url\":\"http://www.soso.com/\"\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"                 \"type\":\"miniprogram\",\r\n" + 
			"                 \"name\":\"wxa\",\r\n" + 
			"                 \"url\":\"http://mp.weixin.qq.com\",\r\n" + 
			"                 \"appid\":\"wx286b93c14bbf93aa\",\r\n" + 
			"                 \"pagepath\":\"pages/lunar/index\"\r\n" + 
			"             },\r\n" + 
			"            {\r\n" + 
			"               \"type\":\"click\",\r\n" + 
			"               \"name\":\"赞一下我们\",\r\n" + 
			"               \"key\":\"V1001_GOOD\"\r\n" + 
			"            }]\r\n" + 
			"       }]\r\n" + 
			" }";
	getJavaBean(path,className,packageName,jsonString);
}

/**
 * 根据JSON生成对应的JavaBean
 * @param path 生成的目录
 * @param className 生成的根文件的名字
 * @param packageName 包名
 * @param json json字符串
 */
public static void printJavaBean(String path,String className,String str)  {
	//默认存放目录
	if(StringUtils.isEmpty(path)) {
		path ="D:\\temp\\";
		
	}
	  
	try {
		//如果目录不存在创建目录
		boolean exists = Files.exists(Paths.get(path));
		if(!exists) {
			 Files.createDirectories(Paths.get(path));
		}
		//写文件
		File file = new File(path+className+".java");
		PrintStream ps1 = new PrintStream(new FileOutputStream(file));		
		 //生成java文件		 
		 ps1.print(str);
		 ps1.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		   
	
}


private static String getImports() {
	// TODO Auto-generated method stub
	StringBuffer sb = new StringBuffer();
	sb.append("import");
	return null;
}

/**
 * 生成javaBean中的属性和get,set 方法;
 * @param json
 * @return 字符串
 */
private static void getJavaBean(String path,String className,String packageName,String json) {
	String captureName = captureName(className);
	JSONObject jsonObject = JSONObject.parseObject(json);
	Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
	Iterator<Entry<String, Object>> iterator = entrySet.iterator();
	//最终的javaBean字符串
	StringBuffer result = new StringBuffer();	
	//属性
	StringBuffer attr = new StringBuffer();
	//get set方法
	StringBuffer data = new StringBuffer();
	boolean isList=false;
	Map<String,String> map = new HashMap<String, String>();
	
	while(iterator.hasNext()){
		Entry<String, Object> next = iterator.next();
		
		 Object value = next.getValue();
		if(value instanceof String) {
			//拼接属性
			getAttribute(attr,next.getKey(),"String");
			//拼接get,set方法
			setData(data, next.getKey(),"String");
			getData(data,next.getKey(),"String");
			
		}else if(value instanceof List) {
			//拼接属性
			getAttribute(attr,next.getKey(),"List");
			//拼接get,set方法
			setData(data, next.getKey(),"List");
			getData(data,next.getKey(),"List");
			//是否需要导入List导包
			isList=true;
			if(null!=next.getValue()) {
				JSONArray  arr1 = (JSONArray)next.getValue();
			     for (Object object2 : arr1) {
			    	 String jsonString = JSONObject.toJSONString(object2);
						map.put(next.getKey(), jsonString);
				}
				
			}
			
			
			
			
			
		}
	}
	
	 String imports = getImports(isList);
	
	   //包名
		result.append("package "+packageName+";\r\n");
		//导入包
		result.append(imports);
		//类定义
		result.append("public class "+captureName+" {\r\n");
		//属性和get,set方法
	    result.append(attr);
	    result.append(data);
	   //结尾的括号
	   result.append("}");
	   printJavaBean(path, className, result.toString());
	   Iterator<Entry<String, String>> iterator2 = map.entrySet().iterator();
	   while(iterator2.hasNext()) {
		 Entry<String, String> next = iterator2.next();
		   getJavaBean( path,next.getKey(),packageName,next.getValue());
	   }
	  
}
private static String getImports(boolean isList) {
	// TODO Auto-generated method stub
	if(isList) {
		StringBuffer sb = new StringBuffer();
		sb.append("import java.util.ArrayList;\r\n");
		sb.append("import java.util.List;\r\n");
		return sb.toString();
	}
	return "";
}

/**
   * 生成属性字符串
 * @param sb
 * @param key
 * @param type
 */
private static void getAttribute(StringBuffer sb, String key,String type) {
	// TODO Auto-generated method stub
	String captureName = captureName(key);
	sb.append("private ");
	if("String".equals(type)) {
		sb.append(type+" ");
		sb.append(key+";");
	}else if("List".equals(type)) {
		sb.append(type+"<"+captureName+"> "+key+" = new ArrayList<"+captureName+">();");
	}
	sb.append("\r\n");
	
}

/**
 *生成get方法字符串
 * @param sb
 * @param key
 */
public static  void getData(StringBuffer sb,String key,String type) {
	String captureName = captureName(key);
	//访问修饰符
	sb.append("public ");	
	if("String".equals(type)) {
		//数据类型
		sb.append(type);
		//方法名
		sb.append(" get"+captureName+"()");
		
	}else if("List".equals(type)) {
		// List<Hobby> getHobby
		//数据类型
		sb.append(type+"<"+captureName+">");
		//方法名
		sb.append(" get"+captureName+"()");
	}
	//方法体
	sb.append("{\r\n   return "+key+";\r\n}\r\n");
			
}
/**
 * 生成set字符串
 * @param sb
 * @param key
 */
public static  void setData(StringBuffer sb,String key,String type) {
	String captureName = captureName(key);
	//访问修饰符 返回类型
	sb.append("public void");
	//方法名
	//setName
	sb.append(" set"+captureName);
	if("String".equals(type)) {
		//参数列表(String name)
		sb.append("(");
		sb.append(type+" "+key);
		sb.append(")");
		
	}else if("List".equals(type)) {
		//参数列表(List<Hobby> hobby)
		sb.append("(");
		sb.append(type+"<"+captureName+">"+" "+key);
		sb.append(")");
		
		
	}
	//方法体
	sb.append("{\r\n" );
	sb.append("   this."+key+"="+key+";");
	sb.append("\r\n}\r\n");
}
/**
 * 首字母大写
 * @param name
 * @return
 */
public static String captureName(String name) {
	//https://blog.csdn.net/zhurhyme/article/details/27951099
	//https://my.oschina.net/simpleton/blog/1607204
	        char[] cs=name.toCharArray();
	        cs[0]-=32;
	        return String.valueOf(cs);
	        
	    }
}
