package com.ibeer.dto;


public class ImageVerificationVo {
private Integer x;
private Integer y;
private String originImage;
private String shadeImage;
private String cutoutImage;
private String type;
public Integer getX() {
	return x;
}
public void setX(Integer x) {
	this.x = x;
}
public Integer getY() {
	return y;
}
public void setY(Integer y) {
	this.y = y;
}
public String getOriginImage() {
	return originImage;
}
public void setOriginImage(String originImage) {
	this.originImage = originImage;
}
public String getShadeImage() {
	return shadeImage;
}
public void setShadeImage(String shadeImage) {
	this.shadeImage = shadeImage;
}
public String getCutoutImage() {
	return cutoutImage;
}
public void setCutoutImage(String cutoutImage) {
	this.cutoutImage = cutoutImage;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
}
