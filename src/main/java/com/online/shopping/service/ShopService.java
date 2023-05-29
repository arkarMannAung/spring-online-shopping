package com.online.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.online.shopping.entity.DivisionEntity;
import com.online.shopping.entity.ItemEntity;
import com.online.shopping.entity.UserEntity;
import com.online.shopping.form.LoginForm;
import com.online.shopping.repository.ShopMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class ShopService {
	
	@Autowired
	ShopMapper shopMapper;
	
	public List<ItemEntity> getAllItem(){
		return this.shopMapper.getAllItem();
	}
	
	public boolean login(LoginForm loginUser, Model model,HttpSession session) {
		// business logic here
		UserEntity authUser = this.shopMapper.login( loginUser );
		
		if( authUser != null ) {
			session.setAttribute("Auth", authUser);
		}else {
			model.addAttribute("login_error","email or password does not match!");			
		}
		
		return authUser != null;
	}
	
	public UserEntity login(String email, String password) {
		// business logic here
		return this.shopMapper.login( email, password );
	}
	
	public boolean isEmailSatisfy(String email) {
		return this.shopMapper.isEmailSatisfy(email);
	}
	
	public List<DivisionEntity> getDivisions(){
		return this.shopMapper.getDivisions();
	}
}