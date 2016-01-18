package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Roles;
import com.petCart.model.userRoles;


public interface IRolesDAO  extends IGenericDao<Roles> {

	Roles findRoleByName(userRoles role) throws Exception;
}
