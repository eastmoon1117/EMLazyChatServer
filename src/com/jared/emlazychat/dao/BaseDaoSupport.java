package com.jared.emlazychat.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.jared.emlazychat.core.jdbc.Dao;

public class BaseDaoSupport<POJO> extends Dao<POJO>{
	
	@Resource(name = "sessionFactory")
	public void setFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
