package com.ibeer.common.req;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestHeader implements Serializable {
private static final long serialVersionUID = 1L;
private String functionMethod;//方法的url
private String requestURI;
private StringBuffer requestURL;
private String contextPath;
private String servletPath;
private String remoteAddr;
private String LocalAddr;
private String serverName;
private int serverPort;
private String scheme;
private String remoteHost;
private long time;

}
