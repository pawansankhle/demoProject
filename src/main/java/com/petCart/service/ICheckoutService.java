package com.petCart.service;

import javax.servlet.http.HttpSession;

import com.petCart.model.Users;

public interface ICheckoutService {
	String updateShippingDetail(HttpSession session, Users user);
}
