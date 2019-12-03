package com.ibeer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class Contract implements Serializable {
private Long id;
private String hthm;
private String hydm;
private String hymc;
private Date createRq;
private String hzmc;

}
