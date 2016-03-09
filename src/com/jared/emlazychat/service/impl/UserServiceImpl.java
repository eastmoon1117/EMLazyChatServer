package com.jared.emlazychat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jared.emlazychat.pojo.User;
import com.jared.emlazychat.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	
	@Override
	public boolean isExist(String account) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public User addUser(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User findUserByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateToken(User user) {
		// TODO Auto-generated method stub
		
	}
}
