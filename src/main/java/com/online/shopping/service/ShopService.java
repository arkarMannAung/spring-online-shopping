package com.online.shopping.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.ui.Model;

import com.online.shopping.entity.DivisionEntity;
import com.online.shopping.entity.ItemEntity;
import com.online.shopping.entity.OrderDetailEntity;
import com.online.shopping.entity.UserEntity;
import com.online.shopping.entity.UserOrderEntity;
import com.online.shopping.form.ConfirmOrderForm;
import com.online.shopping.form.ItemOrderModel;
import com.online.shopping.form.LoginForm;
import com.online.shopping.form.OrderForm;
import com.online.shopping.repository.ShopMapper;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
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
	
	public boolean createOrder( ConfirmOrderForm confirmOrderForm ) {
		OrderForm orderForm = new OrderForm();
		orderForm.setItemList( confirmOrderForm.getItemList() );
		List<ItemOrderModel> orderDetails = orderForm.toList();
		try {
			int orderId = this.shopMapper.createOrder(confirmOrderForm);
			for ( ItemOrderModel order : orderDetails ) {
				this.shopMapper.createOrderDetail( order.getQty(), orderId, order.getId() );
			}
			return true;
		}catch (Exception e) {
			System.out.println("#Error");
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		
	}
	
	public List<UserOrderEntity> getUserOrder(int userId){
		return this.shopMapper.getUserOrder(userId);
	}
	
	public List<OrderDetailEntity> getOrderDetail(int orderId){
		return this.shopMapper.getOrderDetail(orderId);
	}
	
	
	
	
}