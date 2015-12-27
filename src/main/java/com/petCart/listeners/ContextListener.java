package com.petCart.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.petCart.model.Cart;


public class ContextListener implements HttpSessionListener {

	private Logger logger = LoggerFactory.getLogger(ContextListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		String sessioniId = arg0.getSession().getId();
		logger.info("inside @class ContextListener @method sessionCreated entry...id is: "+sessioniId);
		 Cart cart = new Cart();
		 cart.setName(sessioniId);
		 arg0.getSession().setAttribute("cart", cart);
		 
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		
	}

}
