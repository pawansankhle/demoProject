package com.petCart.service;

import javax.servlet.http.HttpSession;

import com.petCart.model.LoginForm;
import com.petCart.model.Users;

public interface IUserService {

	public Users createUser(Users user);

	public String Customlogin(String j_username,String j_password,HttpSession session);
}
