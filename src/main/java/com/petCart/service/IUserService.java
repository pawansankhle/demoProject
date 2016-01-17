package com.petCart.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.petCart.model.LoginForm;
import com.petCart.model.Users;

public interface IUserService {

	Users createUser(Users user);
    Users Customlogin(String j_username,String j_password,HttpSession session);
    void customLogout(HttpServletRequest request, HttpSession session);
    Users findByName(String username);
	
}
