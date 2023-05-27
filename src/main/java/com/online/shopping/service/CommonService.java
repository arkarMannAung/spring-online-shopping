package com.online.shopping.service;

import org.springframework.stereotype.Service;

import com.online.shopping.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class CommonService {
	public UserEntity checkAuth(HttpSession session) {
		UserEntity authUser = (UserEntity) session.getAttribute("Auth");
		return authUser;
	}
}
