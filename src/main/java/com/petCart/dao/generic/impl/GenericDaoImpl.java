package com.petCart.dao.generic.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.petCart.dao.generic.IGenericDao;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	@PersistenceContext
    protected EntityManager entityManager;
	
	private Class<T> type;

    public GenericDaoImpl(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    
    @Override
    public long countAll(final Map<String, Object> params) {
        final StringBuffer queryString = new StringBuffer("SELECT count(o) from");
        queryString.append(type.getSimpleName()).append("o");
        queryString.append(this.getQueryClauses(params, null));
        final Query query = this.entityManager.createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }
    
	
	private Object getQueryClauses(Map<String, Object> params, Object object) {
		
		return null;
	}

	@Override
	public T create(final T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void delete(Object id) {
		this.entityManager.remove(this.entityManager.getReference(type, id));
		
	}

	@Override
	public T find(Object id) {
		return this.entityManager.find(type, id);
	}

	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	
	
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	
    
	 
	
	}


