package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Department;



public interface IDepartmentService {

	public  void  addDepartment(Department dept);
    public List<Department> getAllDepartment();
	public void deleteDepartment(Department dept);
	public Department findDept(Department dept);
	public Department findById(Integer id);
	public List<Department> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
	
}
