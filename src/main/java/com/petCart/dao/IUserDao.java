package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Users;


public interface IUserDao extends IGenericDao<Users>{

	Users findUserByName(String username);
}
