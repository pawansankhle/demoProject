package com.petCart.service.impl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IUserDao;
import com.petCart.model.Users;
import com.petCart.service.ICheckoutService;

@Service
@Transactional
public class CheckoutServiceImpl implements ICheckoutService {

	@Autowired 
	IUserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

	@Override
	public String updateShippingDetail(HttpSession session, Users user) {
		logger.info("inside @class UserServiceImpl @method updateProfile entry...");
		try{
		      Authentication auth = (Authentication) session.getAttribute("authentication");
		      Users oldUser = userDao.findUserByName(user.getUsername());
		       if(auth !=null && oldUser !=null){
		    	   oldUser.setAddress(user.getAddress());
		    	   oldUser.setMobile(user.getMobile());
		    	   Users Newuser = userDao.update(oldUser);
		    		  if(Newuser !=null)
		    			  return "{\"status\": \"success\"}";
		    		  else
		    			 return "{\"status\": \"faild\"}";
		    	     }
		}catch(Exception ex){
			logger.error("@class UserServiceImpl @method updateProfile  cause: "+ex.toString());
			return null;
		}
		return null;
	}
}
