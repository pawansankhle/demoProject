package com.petCart.dao;

import java.util.List;
import java.util.Set;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Cart;
import com.petCart.model.CartItem;
import com.petCart.model.Product;


public interface ICartItemDAO extends IGenericDao<CartItem>  {

	CartItem findItembyItemId(Cart cart,Product itemId);
    List<CartItem> findItemsbyCatId(Cart cart);
	
    
	

	

	
}
