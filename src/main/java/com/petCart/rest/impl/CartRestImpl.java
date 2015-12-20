package com.petCart.rest.impl;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("CartRestImpl")
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartRestImpl {

private Logger logger = LoggerFactory.getLogger(CartRestImpl.class);
	
	@Context
	private SearchContext context;
	
	
	
}
