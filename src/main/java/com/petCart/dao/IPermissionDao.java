package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Permissions;
import com.petCart.model.Product;
import com.petCart.model.userPermission;

public interface IPermissionDao extends IGenericDao<Permissions> {
     
	Permissions findPermissionByName(userPermission permission);
}
