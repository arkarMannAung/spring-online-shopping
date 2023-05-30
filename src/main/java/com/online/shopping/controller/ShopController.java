package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.online.shopping.entity.UserEntity;
import com.online.shopping.form.ConfirmOrderForm;
import com.online.shopping.form.LoginForm;
import com.online.shopping.form.OrderForm;
import com.online.shopping.service.CommonService;
import com.online.shopping.service.ShopService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	CommonService commonService;
	
	@PostConstruct
	public void init() {
//		System.out.println( this.shopService.getDivisions() );
	}
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {		

		model.addAttribute("Auth", commonService.checkAuth(session) );
		
		model.addAttribute("loginForm", new LoginForm());
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	@PostMapping("/")
	public String login(@Valid @ModelAttribute LoginForm loginForm,BindingResult result,Model model,HttpSession session) {
		
		if( result.hasErrors() || !this.shopService.login( loginForm, model, session ) ) {
			model.addAttribute("err_msg","error occour");
			model.addAttribute("loginForm", loginForm);
			model.addAttribute("items",this.shopService.getAllItem());
			return "screens/index";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup(Model model, HttpSession session) {
		model.addAttribute("Auth", commonService.checkAuth(session) );
		return "screens/signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("Auth");
		return "redirect:/";
	}
	
	@PostMapping("/create/order")
	public String createOrder(@ModelAttribute OrderForm orderForm, Model model,HttpSession session) {
		UserEntity authUser = (UserEntity) session.getAttribute("Auth");
		if( authUser == null ) {
			return "redirect:/";
		}
		if( orderForm.getItemList().equals("[]") || orderForm.getItemList().equals("") ) {
			return "redirect:/";
		}
		model.addAttribute("Auth", commonService.checkAuth(session) );
		model.addAttribute("itemList",orderForm.getItemList());
		ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm();
		confirmOrderForm.setDivisionList( this.shopService.getDivisions() );
		model.addAttribute("confirmOrderForm",confirmOrderForm);
		return "screens/order";
	}
	
	@PostMapping("/confirm/order")
	public String confirmOrder( @ModelAttribute ConfirmOrderForm confirmOrderForm, HttpSession session ) {
		UserEntity authUser = (UserEntity) session.getAttribute("Auth");
		confirmOrderForm.setUserId( authUser.getId() );
		confirmOrderForm.setLocationId( Integer.parseInt(confirmOrderForm.getDivisionId()) );
		
		this.shopService.createOrder(confirmOrderForm);
		
		return "redirect:/";
	}

}

















