package com.petCart.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.petCart.model.Cart;

public interface ICartService {

	Cart createCart(HttpSession session);
	Cart addToCart(HttpSession session,Integer id);
	String updateCart(HttpSession session,Cart cart);
	Cart getCartByName(String cartName);
	String deleteItem(Integer id);
	
	
	
	
}
