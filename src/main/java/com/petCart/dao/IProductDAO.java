package com.petCart.dao;



import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.dao.generic.IGenericDao;
import com.petCart.model.Product;

public interface  IProductDAO extends IGenericDao<Product> {

	public List<Product> findAllProduct();
	public Product viewProduct(Long id);
	public List<Product> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
	public Product findById(Long itemId);
	Long countAll();
    
	
	
}