package com.ibeer.common.req;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestMessage implements Serializable {
private static final long serialVersionUID = 1L;
private RequestHeader requestHeader;
private RequestBody body;

}
