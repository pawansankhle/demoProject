package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.springframework.security.core.Authentication;

import com.petCart.model.Supplier;
import com.petCart.model.Users;

public interface ISupplierService {
	List<Supplier> findAllSuppliers();

	List<Supplier> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);

	Supplier changeSupplierState(Authentication auth, String action, long id);

	String deleteSupplier(Authentication authentication, long id);
}
