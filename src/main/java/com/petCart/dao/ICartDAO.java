package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Cart;
import com.petCart.model.Product;


public interface ICartDAO extends IGenericDao<Cart>  {

	
	public List<Cart> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
	public Cart addToCart(Cart cart);
	public Cart updateCart(Cart cart);
	public Cart findByItemId(Long itemId);
	public Cart findCartByName(String name);
	public Cart findById(Integer id);
}
