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
	@Path("/profile")
	public Users isUserAvailable(){
		HttpSession  session = getSession();
		
		    Authentication authentication  = (Authentication) session.getAttribute("authentication");
		    if(authentication!=null){
				String username = authentication.getName();
				Users user =  userService.findByName(username);
				return user;
			}else{
              return null;
		   }

	}
	
	

	@POST
	@Path("/signup")
	public Users SignupUser(Users user){
		logger.info("inside @class UserRestImpl  @method SignupUser entry...");
		HttpSession  session = getSession();
		String j_username = user.getUsername();
		String j_password = user.getPassword();
		Users usr = userService.createUser(user);
		if(usr !=null){
			usr =  userService.Customlogin(j_username,j_password,session);
			return usr;
		}
		return null;
	}


	@POST
	@Path("/login")
	public Users cutomLogin(@Valid LoginForm loginForm){
		logger.info("inside @class UserRestImpl  @method loginUser entry...");
		HttpSession  session = getSession();
		String j_username = loginForm.getusername();
		String j_password = loginForm.getpassword();

        Users usr =  userService.Customlogin(j_username,j_password,session);
		return usr;
	}
	
	

	@GET
	@Path("/logout")
	public String customLogout(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = getSession();
		userService.customLogout(request,session);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication == null)
		{
			return "{\"status\": \"success\",\"message\":\"logout successfully\"}"; 

		}else
		{
			return "{\"status\": \"error\",\"message\":\"could not logout\"}";
		}

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


	public HttpSession getSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = request.getSession(true);
		return session;
	}
}
