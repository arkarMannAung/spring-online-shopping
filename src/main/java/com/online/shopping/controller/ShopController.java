package com.online.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.online.shopping.service.ShopService;

import jakarta.annotation.PostConstruct;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {
		//  Controller   >>>    Service     >>>     Repository
		//     screen        business logic            data crud
		// System.out.println(this.shopService.getAllItem());
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
}
