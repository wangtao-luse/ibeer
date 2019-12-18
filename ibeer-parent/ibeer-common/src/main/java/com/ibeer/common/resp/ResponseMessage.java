package com.ibeer.common.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ibeer.common.constant.ConstantBase;

import lombok.Data;

@Data
public class ResponseMessage implements Serializable {
private String resultCode;
private String resultMessage;
private Map<String,Object> returnResult;
public ResponseMessage(){}
public ResponseMessage(String resultCode, String resultMessage) {
	super();
	this.resultCode = resultCode;
	this.resultMessage = resultMessage;
}
public void setReturnResult(Object object) {
	Map<String,Object> map = new HashMap<String, Object>();
	map.put("result", object);
	this.returnResult=map;
}
public void setReturnResult(String key,Object object) {
	Map<String,Object> map = new HashMap<String, Object>();
	map.put(key, object);
	this.returnResult=map;
}
public static ResponseMessage getSucess(){
return new ResponseMessage(ConstantBase.SUCCESS_CODE, ConstantBase.SUCESS_MESSAGE);
}
public static ResponseMessage getFailed() {
	return new ResponseMessage(ConstantBase.FAILED_CODE, ConstantBase.FAILED_MESSAGE);
}


 
}
