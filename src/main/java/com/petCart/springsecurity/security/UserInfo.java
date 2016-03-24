package com.petCart.springsecurity.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.petCart.dao.IUserDao;
import com.petCart.model.Users;

public class UserInfo {
	
	 
	private static Logger logger = LoggerFactory.getLogger(UserInfo.class);
     public static  Map<String, String> getCurrentUserInfo(){
    	 SecurityContext sc = SecurityContextHolder.getContextHolderStrategy().getContext();		
    	 Authentication authentication = sc.getAuthentication();
    	 Map<String, String> currentUser = new HashMap<String, String>();
    	 
    	 if(authentication == null){
    		 Message message = PhaseInterceptorChain.getCurrentMessage();
    		 HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
    		 HttpSession  session = request.getSession(true);
    		 if(session !=null){
    			 authentication =  (Authentication) session.getAttribute("authentication");
    			 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			 currentUser.put("username", userDetails.getUsername());
        	     return currentUser;
    		 }else{
    			 return null;
    		 }
    		
    	 }
    	 return null;
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
