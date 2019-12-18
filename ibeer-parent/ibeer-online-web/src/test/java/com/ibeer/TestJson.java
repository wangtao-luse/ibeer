package com.ibeer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Contract;


public class TestJson {
 public static void main(String[] args) { 
	 /**
	  * 1.将JavaBean转为JSONObject
	  *   a.将对象转为JSON字符串;
	  *   b.将JSON字符串转为JSONObject;	
	  *   String jsonString = JSONObject.toJSONString(contract);
	  *   JSONObject jsonObject = JSONObject.parseObject(jsonString); 
	  *   
	  * 2.JSONObject转为JavaBean
	  * Contract contract = JSONObject.toJavaObject(jsonObject, Contract.class)
	  * 关系：
	  * jsonString--->JSONObject--->javaBean--->jsonString
	  * 3.jsonStr转为Map
	  * Map map = (Map)JSON.parse(jsonStr);
	  * jsonString--->javaBeanList-->jsonString
	  * 4.jsonString转为javaBeanList
	  * 	List list = JSONObject.parseArray(jsonStrList, Contract.class);
	  * 5.List转为jsonStr
	  * 	String jsonString = JSONObject.toJSONString(list);
	  */
	 
	 
	 ResponseMessage resp = ResponseMessage.getSucess();
	 resp.setReturnResult("wangtao");
	 String jsonString = JSON.toJSONString(resp);
	 System.out.println(jsonString);
		
		/*
		 * JSONObject jsonobject = new JSONObject(); jsonobject.put("id", "1");
		 * jsonobject.put("name", "wangtao");
		 * jsonobject.put("contractList",getJavaBeanList()); jsonobject.put("vo",
		 * getJavaBean()); String id=jsonobject.getString("id"); System.out.println(id);
		 * Contract contract =
		 * jsonobject.getJSONObject("vo").toJavaObject(Contract.class);
		 * contract.setHthm("beer0003"); contract.setHydm("88888"); jsonobject.put("vo",
		 * contract); System.out.println(jsonobject);
		 */
}

public static String getJsonStrList() {
	String strjson="[{\"createRq\":1576651489864,\"hthm\":\"Beer00001\",\"hydm\":\"10000\",\"hymc\":\"10000\",\"hzmc\":\"Ibeer\",\"id\":1},{\"createRq\":1576651489864,\"hthm\":\"Beer00002\",\"hydm\":\"10001\",\"hymc\":\"10001\",\"hzmc\":\"Ibeer\",\"id\":2}]";
 return strjson;
}

public static List<Contract> getJavaBeanList() {
	List<Contract> list = new ArrayList<Contract>();
	 Contract t = new Contract();
	 t.setCreateRq(new Date());
	 t.setHymc("10000");
	 t.setHydm("10000");
	 t.setHthm("Beer00001");
	 t.setHzmc("Ibeer");
	 t.setId(1L);
	 Contract t2 = new Contract();
	 t2.setCreateRq(new Date());
	 t2.setHymc("10001");
	 t2.setHydm("10001");
	 t2.setHthm("Beer00002");
	 t2.setHzmc("Ibeer");
	 t2.setId(2L);
	 list.add(t);
	 list.add(t2);
	 return list;
}

public static String getjsonStringObject() {
	String jsonStr="{\"createRq\":1576647338750,\"hthm\":\"Beer00001\",\"hydm\":\"10000\",\"hymc\":\"10000\",\"hzmc\":\"Ibeer\",\"id\":1}";
return jsonStr;
}

public static Contract getJavaBean() {
	
	Contract t = new Contract();
	 t.setCreateRq(new Date());
	 t.setHymc("10000");
	 t.setHydm("10000");
	 t.setHthm("Beer00001");
	 t.setHzmc("Ibeer");
	 t.setId(1L);
	return t;
}

public static JSONObject javaBeanToJSONObject(Contract t) {
	String jsonString = JSONObject.toJSONString(t);
	 System.out.println("JSON字符串：\r"+jsonString);
	 JSONObject parseObject = JSONObject.parseObject(jsonString);
	 System.out.println("JSONObject:\r"+parseObject);
	 return parseObject;
}
}
