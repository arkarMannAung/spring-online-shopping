package com.online.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.ItemEntity;
import com.online.shopping.repository.ShopMapper;

@Service
public class ShopService {
	
	@Autowired
	ShopMapper shopMapper;
	
	public List<ItemEntity> getAllItem(){
		return this.shopMapper.getAllItem();
	}
}