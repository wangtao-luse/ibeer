package com.ibeer.dto;

import java.util.Set;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import lombok.Data;
@Data
public class MySimpleAuthorizationInfo extends SimpleAuthorizationInfo {
private Set<String> group; 
}
