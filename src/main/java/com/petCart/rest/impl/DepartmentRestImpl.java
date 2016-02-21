package com.petCart.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.model.Department;
import com.petCart.model.Product;
import com.petCart.service.IDepartmentService;



@Service("DepartmentRestImpl")
@Path("/dept")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentRestImpl {

	private Logger logger = LoggerFactory.getLogger(ProductRestImpl.class);

	@Autowired
	IDepartmentService DepartmentService;
	
	@Context
	private SearchContext context;
	
	@ExceptionHandler
	@GET
	@Path("/totalCount")
	@Produces("application/json")
	public Long getTotalCount(){
		logger.info("inside @class DepartmentRestImpl @method totalCount entry.");
		return DepartmentService.totalCount();
	}
	
	
	@ExceptionHandler
	@GET
	@Path("/search")
	@Produces("application/json")
	public List<Department> search(@DefaultValue("1")@QueryParam("page")Integer page,@DefaultValue("100")@QueryParam("limit")Integer limit,@QueryParam("orderBy")String orderBy,@QueryParam("orderType")String orderType
      ){
		logger.info("inside @class DepartmentRestImpl @method search entry.");
		  try{
			  if(limit > 0 && page >0){
				  Integer Totalcount =  DepartmentService.totalCount().intValue();
				  Integer totalPages = Totalcount/limit;
				  Integer lowerLimit = (totalPages*(page-1)+1);
				  Integer upperLimit = (lowerLimit -1) + limit;
				  return DepartmentService.search(context,lowerLimit,upperLimit,orderBy,orderType);
			   }else{
				   return DepartmentService.search(context,0,100,orderBy,orderType);
			   }
			  
		  }catch(Exception ex){
			  ex.printStackTrace();
			  logger.info("inside @class ProductRestImpl @method search couse: "+ex.toString());
			  return null;
			  
		  }
		}
	
	
	@GET
	@Produces("application/json")
	public List<Department> findAllDept(){
		logger.info("inside @class DepartmentRestImpl  @method findAllDept entry...");
		return DepartmentService.getAllDepartment();
	  }
	
	
		
	}


