package com.petCart.rest.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCart.model.Category;

import com.petCart.service.ICategoryService;


@Service("CategoryRestImpl")
@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestImpl {

	private Logger logger = LoggerFactory.getLogger(CategoryRestImpl.class);

	@Autowired
	ICategoryService categoryService;
	
	@Context
	private SearchContext context;
	
	
	@GET
	public List<Category> getAllCategory(){
		logger.info("inside @class CategoryRestImpl @method getAllCategory entry...");
		try{
			return categoryService.getAllCategory();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside @class CategoryRestImpl  @method getAllCategory cause:"+e.toString());

			return null;
		}
	}

}
