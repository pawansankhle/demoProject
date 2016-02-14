package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Supplier;
import com.petCart.model.Users;

public interface ISupplierDao extends IGenericDao<Supplier>{

	List<Supplier> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);
	List<Supplier> findAllSuppliers();
}
