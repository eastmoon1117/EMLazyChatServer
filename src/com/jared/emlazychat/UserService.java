package com.jared.emlazychat;

public interface UserService {

	boolean isExist(String account);

	User addUser(String account, String password);

	User findUserByAccount(String account);

	void updateToken(User user);
}