package com.ibeer.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class Button {
private List<AbstractButton> abstractButton = new ArrayList<AbstractButton>();

public List<AbstractButton> getAbstractButton() {
	return abstractButton;
}

public void setAbstractButton(List<AbstractButton> abstractButton) {
	this.abstractButton = abstractButton;
}

}
