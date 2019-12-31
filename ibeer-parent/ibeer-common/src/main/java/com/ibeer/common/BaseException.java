package com.ibeer.common;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
private static final long serialVersionUID = 1L;
private String errorMessage;
public BaseException() {}
public BaseException(String errorMessage) {
	super();
	this.errorMessage = errorMessage;
}
}
