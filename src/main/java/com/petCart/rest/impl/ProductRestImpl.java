package com.petCart.rest.impl;



import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
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

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.model.Files;
import com.petCart.model.Product;
import com.petCart.service.IProductService;



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
			
			logger.error("inside @class ProductRestImpl  @method getallProduct cause:"+e.toString());

			return null;
		}
	}
	
	@POST
	@Consumes("application/json")
	@Path("create/")
	public Product addProduct(Product p){
		logger.info("inside @class ProductRestImpl @method addProduct entry...");
		try{
			 return productService.addProduct(p);
			 
		    }catch(Exception e){
			   logger.error("inside @class ProductRestImpl  @method addProduct cause:"+e.toString());
            return null;
		}
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Product findById(@PathParam("id") Integer id){
		logger.info("inside @class ProductRestImpl @method findById entry...id:"+id);
		return productService.findById(id);
    }

	
	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public Product veiwProduct(@PathParam("id") Integer id){
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
	
	
    @POST
    @Path("/update")
    @Produces("application/json")
    public Product updateProduct(Product product){
    	logger.info("inside @class ProductRestImpl @method updateProduct entry...id:"+product.getId());
    	return productService.updateProduct(product);
    	
    }
	
    @ExceptionHandler
    @POST
    @Path("/{id}/{action}")
    @Produces("application/json")
    public Product enableDisableProduct(@PathParam("id") Integer id,@PathParam("action") String action){
    	return productService.enableDisable(id,action);
    }
    
    
    
	   @POST
	   @Path("uploadImage/{id}/{filename}")
	   @Consumes("multipart/form-data")
	   @Produces("text/html")
	   @Multipart(value = "root", type = "application/octet-stream")
		public String uploadProductImage(@Multipart(value = "filedata")InputStream in, @PathParam("id") Integer id,@PathParam("filename") String fname) throws Exception {	
           return productService.uploadProductImage(fname,id,in);
		   //UsersProfileImageAttach usersProfileImageAttach = usersProfileImageAttachService.addAttachment(user, fileName, in);
	     }
	   
	   @ExceptionHandler
	   @GET
	   @Path("findProductRecomm/{id}")
	   @Produces("application/json")
	   public List<Product> findProductRecommendation(@PathParam("id") Integer id){
		   return productService.findProductRecommendation(id);
	   }
	   

}
