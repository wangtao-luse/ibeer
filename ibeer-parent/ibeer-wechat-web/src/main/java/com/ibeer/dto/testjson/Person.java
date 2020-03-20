package com.ibeer.dto.testjson;

import java.util.ArrayList;
import java.util.List;

public class Person {
private String id;
private String name;
private List<Hobby> hobby = new ArrayList<>();
public Person() {
	super();
}
public Person(String id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Hobby> getHobby() {
	return hobby;
}
public void setHobby(List<Hobby> hobby) {
	this.hobby = hobby;
}
}
