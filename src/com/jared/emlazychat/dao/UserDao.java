package com.jared.emlazychat.dao;

import com.jared.emlazychat.pojo.User;

public interface UserDao {
	User findUserByAccount(String account);
	
	User findById(String id);
	
	void addUser(User user);
	
	void updateUser(User user);
}
