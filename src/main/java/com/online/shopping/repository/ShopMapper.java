package com.online.shopping.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.online.shopping.entity.ItemEntity;
import com.online.shopping.entity.UserEntity;
import com.online.shopping.form.LoginForm;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();
	
	public UserEntity login(LoginForm loginUser);
	
	public UserEntity login(@Param("email") String email,@Param("password") String password);
}
