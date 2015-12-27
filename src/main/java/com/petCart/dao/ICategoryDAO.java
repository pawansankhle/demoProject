package com.petCart.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Category;
import com.petCart.model.Product;



public interface ICategoryDAO extends IGenericDao<Category>  {

	public List<Category> findAllCategory();
	public List<Category> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
	public List<Product> findProductByCategory(Integer cid);
    
}
