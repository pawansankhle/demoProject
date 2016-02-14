package com.petCart.rest.impl;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.petCart.dao.IProductDAO;
import com.petCart.model.Department;
import com.petCart.model.Product;
import com.petCart.service.IProductService;
import com.petCart.service.impl.ProductServiceImpl;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Service("ProductRestImpl")
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductRestImpl{

	private Logger logger = LoggerFactory.getLogger(ProductRestImpl.class);

	@Autowired
	IProductService productService;
	
	@Context
	private SearchContext context;

	
	@GET
	public List<Product> get(){
		logger.info("inside @class ProductRestImpl @method getallProduct entry...");
		try{
			return productService.getAllProduct();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside @class ProductRestImpl  @method getallProduct cause:"+e.toString());

			return null;
		}
	}
	
	@POST
	@Consumes("application/json")
	@Path("{id}")
	public Response addProduct(Product p){
		logger.info("inside @class ProductRestImpl @method addProduct entry...");
		try{
			 productService.addProduct(p);
			 return null;
		    }catch(Exception e){
			e.printStackTrace();
			logger.error("inside @class ProductRestImpl  @method addProduct cause:"+e.toString());
            return null;
		}
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Product findById(@PathParam("id") Long id){
		logger.info("inside @class ProductRestImpl @method findById entry...id:"+id);
		return productService.findById(id);
    }

	
	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public Product veiwProduct(@PathParam("id") Long id){
	    logger.info("inside @class ProductRestImpl @method veiwProduct entry...id:"+id);
		return productService.viewProduct(id);
	}
	
	@ExceptionHandler
	@GET
	@Path("search")
	@Produces("application/json")
	public List<Product> search(@DefaultValue("id")@QueryParam("orderBy")String orderBy,
			@DefaultValue("asc")@QueryParam("orderType")String orderType,@DefaultValue("0")@QueryParam("lowerLimit")Integer lowerLimit,
			@DefaultValue("0")@QueryParam("upperLimit")Integer upperLimit
      ){
		logger.info("inside @class ProductRestImpl @method search entry.");
		return productService.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public Product Delete(@PathParam("id") Integer id){
		return null;
	}
	
	
    @PUT
    @Path("/update/{id}")
    @Produces("application/json")
    public Product updateProduct(Product product){
    	
    	return null;
    }
	

}
