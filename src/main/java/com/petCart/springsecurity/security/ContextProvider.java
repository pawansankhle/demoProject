package com.petCart.springsecurity.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextProvider implements ApplicationContextAware {

	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("application.xml");;
	
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		
		    appContext = context;
	}
	
	
	public static ApplicationContext getContext()
	{
		return appContext;
	}

}
