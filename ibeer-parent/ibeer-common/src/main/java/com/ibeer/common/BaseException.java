package com.ibeer.common;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
private static final long serialVersionUID = 1L;
private String message;
public BaseException() {}
public BaseException(String message) {
	super();
	this.message = message;
}
}
