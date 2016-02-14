package com.petCart.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.service.ISupplierService;


@Service("SupplierRestImpl")
@Path("/supplier")
@Produces(MediaType.APPLICATION_JSON)
public class SupplierRestImpl {
	
	private Logger logger = LoggerFactory.getLogger(SupplierRestImpl.class);

	@Context
	private SearchContext context;
	
	@Autowired
	private ISupplierService supplierService;
	
	
	@ExceptionHandler
	@GET
	@Path("/search")
	@Produces("application/json")
	public List<Supplier> search(@DefaultValue("id")@QueryParam("orderBy")String orderBy,
			@DefaultValue("asc")@QueryParam("orderType")String orderType,@DefaultValue("0")@QueryParam("lowerLimit")Integer lowerLimit,
			@DefaultValue("100")@QueryParam("upperLimit")Integer upperLimit
      ){
		logger.info("inside @class UserRestImpl @method search entry.");
		return supplierService.search(context,lowerLimit,upperLimit,orderBy,orderType);
	  }
	
	@ExceptionHandler
	@GET
	@Path("/findAllSuppliers")
	@Produces("application/json")
	public List<Supplier> findAllSuppliers(){
		logger.info("inside @class SuppliersRestImpl @method SupplierRestImpl entry.");
		return supplierService.findAllSuppliers();
		
	}
	
	
	@ExceptionHandler
	@POST
	@Path("/action/{action}/{id}")
	@Produces("application/json")
	public Supplier changeSupplierState(@PathParam("id") long id,@PathParam("action") String action){
		logger.info("inside @class SuppliersRestImpl @method changeSupplierState entry.");
		Authentication authentication  = (Authentication) getSession().getAttribute("authentication");
		return supplierService.changeSupplierState(authentication,action,id);
	}
	
	@DELETE
	@ExceptionHandler
	@Path("/delete/{id}")
	public String deleteSupplier(@PathParam("id") long id){
		logger.info("inside @class SuppliersRestImpl @method deleteSupplier entry.");
		Authentication authentication  = (Authentication) getSession().getAttribute("authentication");
		return supplierService.deleteSupplier(authentication, id);
	}
	
	private HttpSession getSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = request.getSession(true);
		return session;
	}
	
}
