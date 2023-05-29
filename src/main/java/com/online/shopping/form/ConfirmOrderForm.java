package com.online.shopping.form;

import java.util.List;

import com.online.shopping.entity.DivisionEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ConfirmOrderForm {
	// form
	@NotEmpty(message="reciever name is required")
	private String receiverName;
	@NotEmpty(message="reciever phone is required")
	private String receiverPhone;
	@NotEmpty(message="reciever address is required")
	private String receiverAddress;
	private String divisionId;
	
	private int userId;
	private String itemList;
	private List<DivisionEntity> divisionList;
}
