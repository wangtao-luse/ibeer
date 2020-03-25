package com.ibeer;

import java.util.Map;

public class TestMethod {
private String resultCode;
private String resltMsg;
private Map<String,Object> result;
public String getResultCode() {
	return resultCode;
}
public void setResultCode(String resultCode) {
	this.resultCode = resultCode;
}
public String getResltMsg() {
	return resltMsg;
}
public void setResltMsg(String resltMsg) {
	this.resltMsg = resltMsg;
}
public Map<String, Object> getResult() {
	return result;
}
public void setResult(Map<String, Object> result) {
	this.result = result;
}
public void setResult(Object object) {
	this.result = result;
}

}
