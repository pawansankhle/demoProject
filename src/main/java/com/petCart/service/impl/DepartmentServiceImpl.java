package com.petCart.service.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IDepartmentDAO;

import com.petCart.model.Department;
import com.petCart.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService{

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	IDepartmentDAO deptDAO;

	
	@Override
	public void addDepartment(Department dept) {
		deptDAO.create(dept);
		
	}
	
	

	@Override
	public List<Department> getAllDepartment() {
		logger.info("inside @class DepartmentServiceimpl @method getAllDepartment entry ");
		try{
			return deptDAO.findAllDepartment();
		}catch(Exception e){
			logger.error("@class DepartmentServiceimpl @method getAllDepartment cause: "+e.toString());
		}
		return null;
		
	}

	@Override
	public void deleteDepartment(Department dept) {
		deptDAO.delete(dept);
		
	}

	@Override
	public Department findDept(Department dept) {
		return deptDAO.find(dept);
	}

	

	@Override
	public List<Department> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		logger.info("inside @class DepartmentServiceImpl  @mehod search");
		try{
			return deptDAO.search(context,lowerLimit,upperLimit,orderBy,orderType);
		}catch(Exception ex){
			logger.error("@class DepartmentServiceImpl @method search cause: "+ex.toString());
			return null;
		}
	}

	@Override
	public Department findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Long totalCount() {
		logger.info("inside @class DepartmentServiceimpl @method totalCount entry ");
		try{
			return deptDAO.countAll();
		}catch(Exception e){
			logger.error("@class DepartmentServiceimpl @method totalCount cause: "+e.toString());
		}
		return null;
	}

}
