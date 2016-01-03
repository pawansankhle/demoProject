package com.petCart.springsecurity.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface IChangePassword  extends UserDetailsService{
  
	public void changePassword(String username,String password);

	
	
}
