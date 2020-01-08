package com.ibeer.dto;

import lombok.Data;

@Data
public class ImageVerificationVo {
private Integer x;
private Integer y;
private String originImage;
private String shadeImage;
private String cutoutImage;
private String type;
}
