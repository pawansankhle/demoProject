package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Category;


public interface ICategoryService {

	public  void  addProduct(Category category);
    public List<Category> getAllCategory();
	public void deleteCategory(Category category);
	public Category getCategory();
	public Category findById(Integer id);
	public List<Category> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
	
	
}
