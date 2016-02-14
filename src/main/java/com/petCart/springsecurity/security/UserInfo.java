package com.petCart.springsecurity.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.petCart.dao.IUserDao;
import com.petCart.model.Users;

public class UserInfo {
	
	 
     
     public static  Map<String, String> getCurrentUserInfo(){
    	 SecurityContext sc = SecurityContextHolder.getContextHolderStrategy().getContext();		
    	 Authentication authentication = sc.getAuthentication();
    	 System.out.println("inside UserInfo auth is: "+authentication.getPrincipal().toString());
    	 Map<String, String> currentUser = new HashMap<String, String>();
    	 
    	 if(authentication == null)
    			return null;
    	 
    	 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	 currentUser.put("username", userDetails.getUsername());
	     System.out.println("returninig: "+currentUser.toString());
	     return currentUser;
	  }
     
     public static String getCurrentUsername(){
 		if(getCurrentUserInfo()==null){
 			return null;
 		}
 			return getCurrentUserInfo().get("username");
 		
 	} 
     
   public static Users getCurrentUser(){
    		
	   IUserDao userDao =ContextProvider.getContext().getBean(IUserDao.class);
 	   return userDao.findUserByName(getCurrentUsername()); 
 	}
	
	
	

}
