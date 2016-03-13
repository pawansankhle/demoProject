package com.petCart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.springframework.security.core.Authentication;

import com.petCart.model.LoginForm;
import com.petCart.model.Product;
import com.petCart.model.Users;

public interface IUserService {

	Users createUserForSignup(Users user);
	Users updateUser(Users user);
    Users Customlogin(String j_username,String j_password,HttpSession session);
    void customLogout(HttpServletRequest request, HttpSession session);
    Users findByName(String username);
	String  updateProfile(HttpSession session, Users user);
	List<Users> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);
	List<Users> getAllUsers();
	Users changeUserState(Authentication authentication, String action, Integer id);
	String deleteUser(Authentication authentication,Integer id);
	String changePassword(String username, String oldPassword, String newPassword);
	Users createUser(Users user);
		
}
