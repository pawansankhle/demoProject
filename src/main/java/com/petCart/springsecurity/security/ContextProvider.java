package com.petCart.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextProvider implements ApplicationContextAware {

	private static ApplicationContext appContext;
	
	private Logger logger = LoggerFactory.getLogger(ContextProvider.class);
	@Override
	public void setApplicationContext(ApplicationContext context)throws BeansException {
		logger.info("@class ContextProvider @method setApplicationContext entry...");
		appContext = context;
	}
	
	
	public static ApplicationContext getContext()
	{
		return appContext;
	}

}
