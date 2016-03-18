package com.petCart.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inn.siteforge2.utils.ConfigUtil;
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
	@Path("/changePassword/{oldpassword}/{password}")
	public String submitChangePassword(@PathParam("oldpassword") String oldPassword,@PathParam("password") String newPassword){

		HttpSession session = getSession();
		Authentication auth = (Authentication) session.getAttribute("authentication");
		if(auth !=null){
			String username = auth.getName();
			logger.info("@class @method username is: "+username);
			String res = userService.changePassword(username,oldPassword,newPassword);
			if(res !=null){
				SecurityContextHolder.clearContext();
				return res;
			}
		}
		return null;
	}


	@ExceptionHandler
	@GET
	@Path("/search")
	@Produces("application/json")
	public List<Users> search(@DefaultValue("id")@QueryParam("orderBy")String orderBy,
			@DefaultValue("asc")@QueryParam("orderType")String orderType,@DefaultValue("0")@QueryParam("lowerLimit")Integer lowerLimit,
			@DefaultValue("100")@QueryParam("upperLimit")Integer upperLimit
			){
		logger.info("inside @class UserRestImpl @method search entry.");
		return userService.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}

	@ExceptionHandler
	@GET
	@Path("/findAllUsers")
	@Produces("application/json")
	public List<Users> getAllUsers(){
		logger.info("inside @class UserRestImpl @method getAllUsers entry.");
		return userService.getAllUsers();

	}


	@ExceptionHandler
	@POST
	@Path("/action/{action}/{id}")
	@Produces("application/json")
	public Users changeUserState(@PathParam("id") Integer id,@PathParam("action") String action){
		logger.info("inside @class UserRestImpl @method disableUser entry.");
		Authentication authentication  = (Authentication) getSession().getAttribute("authentication");
		return userService.changeUserState(authentication,action,id);
	}

	@DELETE
	@ExceptionHandler
	@Path("/delete/{id}")
	public String deleteUser(@PathParam("id") Integer id){
		logger.info("inside @class UserRestImpl @method deleteUser entry.");
		Authentication authentication  = (Authentication) getSession().getAttribute("authentication");
		return userService.deleteUser(authentication, id);
	}

	@POST
	@ExceptionHandler
	@Path("/update")
	public Users update(Users user){
		logger.info("inside @class UserRestImpl @method update entry.");
		return userService.updateUser(user);
	}
	
	@POST
	@ExceptionHandler
	@Path("/create")
	public Users create(Users user){
		logger.info("inside @class UserRestImpl @method update entry.");
		return userService.createUser(user);
	}
	
	
	/*public void sendActivationEmail(Users users)
	{
		    logger.debug("Sending activation mail by  an entity :"+users);
			String activationURL=ConfigUtil.getConfigProp(ConfigUtil.APP_DEPLOY_URL);
			String actObjClass = getObjectClassName(activationURL);
			String objClass = getObjectClassName(users);
		  
		  Map model = new HashMap();
		  model.put(objClass,users);
		  model.put(actObjClass,activationURL);
		  String path=ConfigUtil.ACTIVATION_TEMPALTE;
		   String text="";
			try {
				text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, path,"UTF-8", model);
			} catch (VelocityException e1) {
				logger.error("Error Inside  @class :"+this.getClass().getName()+" @Method :sendActivationEmail()"+e1.getMessage());
			}
		   serviceEmail.createNotification(users.getEmail(),"application",users,"New user!!");
		   try {
			this.mailsender.sendMail("New user!!", text, users.getEmail(),"USER_MANAGEMENT");
		} catch (AddressException e) {
		    logger.error("Error Inside  @class :"+this.getClass().getName()+" @Method :sendActivationEmail()"+e.getMessage());
			logger.error("Sending activation mail by  an entity :"+users+e.getMessage());
			
		} catch (MessagingException e) {
			logger.error("Error Inside  @class :"+this.getClass().getName()+" @Method :sendActivationEmail()"+e.getMessage());
		    logger.error(e.getMessage());
		}  	
		
		
	}*/

	private HttpSession getSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = request.getSession(true);
		return session;
	}
}
