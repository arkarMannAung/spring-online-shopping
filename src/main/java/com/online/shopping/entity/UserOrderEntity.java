package com.online.shopping.entity;

import lombok.Data;

@Data
public class UserOrderEntity {
	private int orderId;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private int total;
}
