package com.petCart.service;

import java.io.InputStream;
import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.petCart.model.Files;
import com.petCart.model.Product;

public interface IProductService {

    Product  addProduct(Product product);
    List<Product> getAllProduct();
	void deleteProduct(Product product);
	Product getProduct();
	Product findById(Integer id);
	Product viewProduct(Integer id);
	List<Product> search(SearchContext context,Integer lowerLimit,Integer upperLimit, String orderBy, String orderType);
    Long totalCount();
	Product updateProduct(Product product);
	Product enableDisable(Integer id, String action);
	String uploadProductImage(String filename,Integer id, InputStream in);
	List<Product> findProductRecommendation(Integer id);
	
	
	
}
