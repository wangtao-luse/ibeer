package com.ibeer.dto.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Articles {


@XStreamAlias("Item")

private Item item;

public Item getItem() {
	return item;
}

public void setItem(Item item) {
	this.item = item;
}

}
