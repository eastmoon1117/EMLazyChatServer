package com.jared.emlazychat.service;

import com.jared.emlazychat.pojo.User;

public interface UserService {

	boolean isExist(String account);

	User addUser(String account, String password);

	User findUserByAccount(String account);

	void updateToken(User user);
}