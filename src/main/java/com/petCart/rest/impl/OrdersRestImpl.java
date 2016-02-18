package com.petCart.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.model.Orders;
import com.petCart.model.Product;
import com.petCart.service.IOrderService;

@Service("OrdersRestImpl")
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrdersRestImpl {
	
	private Logger logger = LoggerFactory.getLogger(OrdersRestImpl.class);

	@Autowired
	IOrderService orderService;
	
	@Context
	private SearchContext context;
	
	@ExceptionHandler
	@GET
	@Path("search")
	@Produces("application/json")
	public List<Orders> search(@DefaultValue("id")@QueryParam("orderBy")String orderBy,
			@DefaultValue("asc")@QueryParam("orderType")String orderType,@DefaultValue("0")@QueryParam("lowerLimit")Integer lowerLimit,
			@DefaultValue("0")@QueryParam("upperLimit")Integer upperLimit
      ){
		logger.info("inside @class OrdersRestImpl @method search entry.");
		return orderService.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}
	
	@ExceptionHandler
	@GET
	@Path("/findById/{id}")
	@Produces("application/json")
	public Orders findById(@PathParam("id") Long id){
		logger.info("inside @class ProductRestImpl @method findById entry...id:"+id);
		return orderService.findById(id);
    }
		
	@ExceptionHandler
	@POST
	@Path("/placeOrder/{cartId}")
	@Produces("application/json")
	public Orders placeOrder(@PathParam("cartId") Integer cartId,Orders order){
		logger.info("inside @class OrdersRestImpl @method placeOrder entry...cartId:"+cartId);
		
		return orderService.placeOrder(cartId,order,getSession());
    }
	
	
	private HttpSession getSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = request.getSession(true);
		return session;
	}
  }
