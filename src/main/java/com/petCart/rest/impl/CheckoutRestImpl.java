package com.petCart.rest.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCart.model.Users;
import com.petCart.service.ICheckoutService;
import com.petCart.service.IUserService;


@Service("CheckoutRestImpl")
@Path("/checkout")
@Produces("application/json")
public class CheckoutRestImpl {

private Logger logger = LoggerFactory.getLogger(CheckoutRestImpl.class);
	
	@Context
	private SearchContext context;
	
	@Autowired
	private ICheckoutService checkoutService;
	    
	@POST
	@Path("/updateShippingDetail")
	public String updateUserProfile(Users user){
		logger.info("inside @class UserRestImpl  @method updateUserProfile entry...");
	    	HttpSession session = getSession();
	    	return checkoutService.updateShippingDetail(session,user);
	 }

     public HttpSession getSession(){
			Message message = PhaseInterceptorChain.getCurrentMessage();
			HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
			HttpSession  session = request.getSession(true);
			return session;
		}

}
