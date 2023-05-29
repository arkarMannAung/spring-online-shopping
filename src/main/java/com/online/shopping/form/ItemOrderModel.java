package com.online.shopping.form;

import lombok.Data;

@Data
public class ItemOrderModel {
	private int id;
	private String name;
	private int price;
	private int qty;
}
