package com.jared.emlazychat.core.jdbc;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableServer.IdAssignmentPolicyOperations;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class Dao<POJO> extends HibernateDaoSupport implements IDao<POJO> {
	private Class<POJO>	clazz;

	@SuppressWarnings("unchecked")
	public Dao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<POJO>) type.getActualTypeArguments()[0];
	}

	@Override
	public Serializable save(POJO entity) {
		HibernateTemplate template = getHibernateTemplate();
		return template.save(entity);
	}

	@Override
	public void update(POJO entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public POJO findById(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		return template.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<POJO> findByHQL(String hql, Object... params) {
		HibernateTemplate template = getHibernateTemplate();
		return (List<POJO>) template.find(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<POJO> findByField(Class<POJO> clazz, String fieldName, Object fieldValue) {
		HibernateTemplate template = getHibernateTemplate();
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq(fieldName, fieldValue));
		return (List<POJO>) template.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<POJO> findByFields(Class<POJO> clazz, String[] fieldNames, Object[] fieldValues) {
		HibernateTemplate template = getHibernateTemplate();
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		for (int i = 0; i < fieldValues.length; i++) {
			criteria.add(Restrictions.eq(fieldNames[i], fieldValues[i]));
		}
		return (List<POJO>) template.findByCriteria(criteria);
	}

	@Override
	public POJO findOneByHQL(String hql, Object... params) {
		List<POJO> list = findByHQL(hql, params);
		if (list != null && list.size() > 0) { return list.get(0); }
		return null;
	}

	@Override
	public POJO findOneByField(Class<POJO> clazz, String fieldName, Object fieldValue) {
	    System.out.println("findOneByField");
		List<POJO> list = findByField(clazz, fieldName, fieldValue);
		if (list != null && list.size() > 0) { return list.get(0); }
		return null;
	}
}
