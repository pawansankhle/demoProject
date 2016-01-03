package com.petCart.rest.impl;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.petCart.model.LoginForm;
import com.petCart.model.Users;
import com.petCart.service.IUserService;

@Service("UserRestImpl")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestImpl {

	
	private Logger logger = LoggerFactory.getLogger(UserRestImpl.class);
	
	@Context
	private SearchContext context;
	
	
	/*@Autowired
	@Qualifier("successHandler")
	private AuthenticationSuccessHandler successHandler;
	*/
	@Autowired
	private IUserService userService;
	/*@Autowired
	@Qualifier("jdbcUserService")
	UserDetailsManager userDetailManager;
	*/
	
	/*@Autowired
	IChangePassword changePasswordDao;
	*/
	
	@GET
	@Path("/user")
	public String isUserAvailable(Principal principal){
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
	    
		if(principal != null){
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   String username = authentication.getName();
		   String roles = authentication.
				   getAuthorities().toString().replace("[","").replace("]","");
		   String sessionid = session.getId();
		   String response = "{\"status\": \"success\", \"username\": \""+username+"\",\"roles\": \""+roles+"\",\"id\": \""+sessionid+"\"}";
		   return response;
		}else{
			
			return null;
		}
		
	}
	
	@POST
	@Path("/signup")
	public String SignupUser(Users user){
		logger.info("inside @class UserRestImpl  @method SignupUser entry...");
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
	    String j_username = user.getUsername();
		String j_password = user.getPassword();
		Users usr = userService.createUser(user);
		if(usr !=null){
			return userService.Customlogin(j_username,j_password,session);
		}else{
			return "{\"status\": \"error\",\"message\": \"user Alread Exist with username\"}";
		}
	}
	
	
	@POST
	@Path("/login")
	public String cutomLogin(@Valid LoginForm loginForm){
		logger.info("inside @class UserRestImpl  @method loginUser entry...");
	   
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
	    String j_username = loginForm.getusername();
		String j_password = loginForm.getpassword();
        
		  return userService.Customlogin(j_username,j_password,session);
	   }
	
	@POST
	@Path("/changePassword")
	public String submitChangePassword(@PathParam("password") String newPassword){
		Object principal = SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		String username = principal.toString();
		if(principal instanceof UserDetails){
			username = ((UserDetails)principal).getUsername();
		}
		//changePasswordDao.changePassword(username, newPassword);
		//userDetailManager.changePassword(oldPassword, newPassword);
		SecurityContextHolder.clearContext();
		return "{\"status\": \"success\"}";
	}
	}
	

