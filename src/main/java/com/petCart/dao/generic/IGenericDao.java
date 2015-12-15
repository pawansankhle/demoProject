package com.petCart.dao.generic;

import java.util.Map;



public interface IGenericDao<T> {
	
	
	long countAll(Map<String, Object> params);
	
    T create(T t);

    void delete(Object id);

    T find(Object object);

    T update(T t); 
    
    T findById(Object id);
    
   
    
    
}
