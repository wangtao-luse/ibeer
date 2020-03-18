package com.ibeer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Articles {
@XStreamAlias("Image")
private Item item;

public Item getItem() {
	return item;
}

public void setItem(Item item) {
	this.item = item;
}

}
