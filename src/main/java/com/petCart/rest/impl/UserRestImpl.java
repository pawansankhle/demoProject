package com.petCart.rest.impl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.petCart.model.User;

@Service("UserRestImpl")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestImpl {

	
	private Logger logger = LoggerFactory.getLogger(UserRestImpl.class);
	
	@Context
	private SearchContext context;
	
	@POST
	@Path("/login")
	public User loginUser(User user){
		logger.info("inside @class UserRestImpl  @method loginUser entry...");
	try{
		User u = new User();
		u.setId(user.getId());
		u.setPassword(user.getPassword());
		u.setUsername(user.getUsername());
		logger.info("inside @class UserRestImpl  @method loginUser return.."+u.toString());
		return u;
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("@class UserRestImpl  @method loginUser cause"+e.toString());
	   }
	
	   return null;
	    
	}
}
