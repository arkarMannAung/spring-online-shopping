package com.online.shopping.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.online.shopping.entity.ItemEntity;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();
}
