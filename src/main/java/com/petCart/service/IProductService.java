package com.petCart.service;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Product;

public interface IProductService {

    void  addProduct(Product product);
    List<Product> getAllProduct();
	void deleteProduct(Product product);
	Product getProduct();
	Product findById(Long id);
	Product viewProduct(Long id);
	List<Product> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
    Long totalCount();
	
	
}
