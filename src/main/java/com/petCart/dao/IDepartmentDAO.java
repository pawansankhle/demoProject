package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Department;



public interface IDepartmentDAO extends IGenericDao<Department> {
	public List<Department> findAllDepartment();
	public List<Department> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
    
	

}
