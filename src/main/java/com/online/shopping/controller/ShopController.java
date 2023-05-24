package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.online.shopping.form.OrderForm;
import com.online.shopping.service.ShopService;

import jakarta.annotation.PostConstruct;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {

	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	@PostMapping("/create/order")
	public String createOrder(@ModelAttribute OrderForm orderForm, Model model) {
		
		model.addAttribute("itemList",orderForm.getItemList());
		
		return "screens/order";
	}
}
