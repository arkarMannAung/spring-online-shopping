package com.online.shopping.entity;

import lombok.Data;

@Data
public class OrderDetailEntity {
	private int id;
	private String name;
	private int quantity;
	private int price;
}
