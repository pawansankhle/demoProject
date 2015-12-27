package com.petCart.dao;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Cart;
import com.petCart.model.CartItem;
import com.petCart.model.Product;


public interface ICartItemDAO extends IGenericDao<CartItem>  {

	public CartItem findItembyItemId(Cart cart,Product itemId);

	

	
}
