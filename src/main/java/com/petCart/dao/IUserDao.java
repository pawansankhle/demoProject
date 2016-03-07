package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Product;
import com.petCart.model.Users;


public interface IUserDao extends IGenericDao<Users>{

	Users findUserByName(String username);

	List<Users> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);

	List<Users> findAllUsers();

	Users findUserById(Integer id);
	
}
