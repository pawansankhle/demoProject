package com.petCart.rest.impl;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.model.Review;
import com.petCart.service.IReviewService;


@Service("ReviewRestImpl")
@Path("/review")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewRestImpl {

	private Logger logger = LoggerFactory.getLogger(ReviewRestImpl.class);

	@Context
	private SearchContext context;
	
	@Autowired
	private IReviewService reviewService;
	
	
	@ExceptionHandler
	@GET
	@Path("/search")
	@Produces("application/json")
	public List<Review> search(@DefaultValue("id")@QueryParam("orderBy")String orderBy,
			@DefaultValue("asc")@QueryParam("orderType")String orderType,@DefaultValue("0")@QueryParam("lowerLimit")Integer lowerLimit,
			@DefaultValue("100")@QueryParam("upperLimit")Integer upperLimit
      ){
		logger.info("inside @class ReviewRestImpl @method search entry.");
		return reviewService.search(context,lowerLimit,upperLimit,orderBy,orderType);
	  }
	
	
}
