package com.petCart.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Orders;

public interface IOrderService {

	List<Orders> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
	Orders findById(long id);
	Orders placeOrder(Integer cartId,Orders order,HttpSession  session);
	List<Orders> findByUserId(long id);
}
