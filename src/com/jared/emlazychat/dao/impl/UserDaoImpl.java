package com.jared.emlazychat.dao.impl;

import java.io.Serializable;
import com.jared.emlazychat.dao.BaseDaoSupport;
import com.jared.emlazychat.dao.UserDao;
import com.jared.emlazychat.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoSupport<User> implements UserDao{
	
	@Override
	public User findUserByAccount(String account) {
	    System.out.println("findUserByAccount");
		return findOneByField(User.class, "account", account);
	}
	
	@Override
	public void addUser(User user) {
		Serializable id = save(user);
		user.setId((String)id);
	}
	
	@Override
	public User findById(String id) {
		return super.findById(id);
	}
	
	@Override
	public void updateUser(User user) {
		update(user);		
	}
}
