package com.jared.emlazychat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jared.emlazychat.pojo.User;
import com.jared.emlazychat.dao.UserDao;
import com.jared.emlazychat.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao	userDao;

	@Override
	public boolean isExist(String account) {
		User user = userDao.findUserByAccount(account);
		return user != null;	
	}
	
	@Override
	public User addUser(String account, String password) {
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
	    System.out.println("User addUser");
		userDao.addUser(user);
		return user;
	}
	
	@Override
	public User findUserByAccount(String account) {
	    System.out.println("User findUserByAccount");
		return userDao.findUserByAccount(account);
	}
	
	@Override
	public void updateToken(User user) {
		userDao.updateUser(user);
	}
}
