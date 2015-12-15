package com.petCart.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCart.model.Department;
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
	
	
	List<Department> department = new ArrayList<Department>();
	
	
	@GET
	@Path("search")
	@Produces("application/json")
	public List<Department> search(){
		SearchCondition<Department> sc = context.getCondition(Department.class);
		
		if (sc == null) {
            try {
				throw new NotFoundException("Invalid search query.");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
       
 
        List<Department> found = sc.findAll(department);
        if (found.size() == 0) {
            try {
				throw new NotFoundException("No matching actor found.");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return found;
		
	}
	
	@GET
	@Produces("application/json")
	public List<Department> findAllDept(){
		logger.info("inside @class DepartmentRestImpl  @method findAllDept entry...");
		return DepartmentService.getAllDepartment();
	  }
	
	
		
	}


