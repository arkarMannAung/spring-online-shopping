package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.online.shopping.form.LoginForm;
import com.online.shopping.form.OrderForm;
import com.online.shopping.service.ShopService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {

	}
	
	@GetMapping("/")
	public String index(Model model) {		
		model.addAttribute("loginForm", new LoginForm());
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	@PostMapping("/")
	public String login(@Valid @ModelAttribute LoginForm loginForm,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("err_msg","error occour");
			model.addAttribute("loginForm", loginForm);
			model.addAttribute("items",this.shopService.getAllItem());
			return "screens/index";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/create/order")
	public String createOrder(@ModelAttribute OrderForm orderForm, Model model) {
		if( orderForm.getItemList().equals("[]") || orderForm.getItemList().equals("") ) {
			return "redirect:/";
		}
		model.addAttribute("itemList",orderForm.getItemList());
		return "screens/order";
	}
	

}
