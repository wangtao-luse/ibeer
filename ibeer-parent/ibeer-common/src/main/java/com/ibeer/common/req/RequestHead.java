package com.ibeer.common.req;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestHead implements Serializable {
private String functionMethod;//方法的url

}
