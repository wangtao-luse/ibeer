package com.ibeer.dto;

import java.util.Set;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

public class MySimpleAuthorizationInfo extends SimpleAuthorizationInfo {
private Set<String> group;

public Set<String> getGroup() {
	return group;
}

public void setGroup(Set<String> group) {
	this.group = group;
} 
}
