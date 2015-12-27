package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Category;
import com.petCart.model.Product;


public interface ICategoryService {

	
    public List<Category> getAllCategory();
	public void deleteCategory(Category category);
	public Category getCategory();
	public Category findById(Integer id);
	public List<Product> getProductByCategory(Integer cid);
	public List<Category> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
	
	
}
