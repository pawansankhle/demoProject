package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Product;

public interface IProductService {

	public  void  addProduct(Product product);
    public List<Product> getAllProduct();
	public void deleteProduct(Product product);
	public Product getProduct();
	public Product findById(Long id);
	public Product viewProduct(Long id);
	public List<Product> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
	
	
}
