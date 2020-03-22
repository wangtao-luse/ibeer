package com.ibeer.dto.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractButton {
private String name;


public AbstractButton() {
	super();
}

public  AbstractButton(String name) {
	super();
	this.name = name;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}




}
