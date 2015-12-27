package com.petCart.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.petCart.model.Cart;

public interface ICartService {

	public Cart createCart(HttpSession session);
	public Cart addToCart(HttpSession session,Long id);
	public Cart updateCart(HttpSession session,Cart cart);
	public Cart getCartByName(String cartName);
	
	
	
}
