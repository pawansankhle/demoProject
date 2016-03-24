package com.petCart.rest.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

import com.petCart.model.Category;
import com.petCart.model.Department;
import com.petCart.model.Product;

import com.petCart.service.ICategoryService;
import com.petCart.util.ConfigUtil;


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
			logger.error("inside @class CategoryRestImpl  @method getAllCategory cause:"+e.toString());

			return null;
		}
	}
	
	@GET
	@Path("/products/{cid}")
	public List<Product> getProductByCategory(@PathParam("id") Integer cid){
		logger.info("inside @class CategoryRestImpl @method getProductByCategory entry...");
		return categoryService.getProductByCategory(cid);
	}
	
	@ExceptionHandler
	@GET
	@Path("/search")
	@Produces("application/json")
	public List<Category> search(@DefaultValue("1")@QueryParam("page")Integer page,@DefaultValue("100")@QueryParam("limit")Integer limit,@DefaultValue("id") @QueryParam("orderBy")String orderBy,@DefaultValue("desc") @QueryParam("orderType")String orderType
      ){
		logger.info("inside @class CategoryRestImpl @method search entry.");
		  try{
			  if(limit > 0 && page >0){
				  //Integer Totalcount =  DepartmentService.totalCount().intValue();
				  //Integer totalPages = Totalcount/limit;
				  Map<String,Integer> result = ConfigUtil.getUpperLowerLimit(page, limit);
				  return categoryService.search(context,result.get("lowerLimit"),result.get("upperLimit"),orderBy,orderType);
			   }else{
				   return categoryService.search(context,0,100,orderBy,orderType);
			   }
			  
		  }catch(Exception ex){
			  logger.info("inside @class CategoryRestImpl @method search couse: "+ex.toString());
			  return null;
			  
		  }
		}

}
