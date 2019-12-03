package com.ibeer.common.req;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
@Data
public class RequestBody implements Serializable {
private JSONObject content;

}
