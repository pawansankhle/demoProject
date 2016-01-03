package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Users;


public interface IUserDao extends IGenericDao<Users>{

	public Users findUserByName(Users user);
}
