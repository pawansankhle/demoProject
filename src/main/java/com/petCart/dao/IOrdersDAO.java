package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Orders;

public interface IOrdersDAO extends IGenericDao<Orders> {
	 List<Orders> findAllOrders();
	 List<Orders> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
	List<Orders> findByUserId(Integer id);
    
	
}
