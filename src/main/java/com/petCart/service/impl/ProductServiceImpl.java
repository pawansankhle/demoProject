package com.petCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petCart.dao.IProductDAO;
import com.petCart.model.Product;
import com.petCart.service.IProductService;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	IProductDAO productDAO;

	@Override
	public void  addProduct(Product product){
		logger.info("inside @class ProductServiceImpl  @mehod addProduct product is: "+product.toString());
		 productDAO.create(product);
	}
		
	

	@Override
	public List<Product> getAllProduct() {
		return productDAO.findAllProduct();
		
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);
		
	}

	@Override
	public Product getProduct() {
		return null;
	}

	@Override
	public Product findById(Long id) {
		return productDAO.findById(id);
	}



	@Override
	public List<Product> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType) {
		logger.info("inside @class ProductServiceImpl  @mehod search");
		 return productDAO.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}



	@Override
	public Product viewProduct(Long id) {
	  logger.info("inside @class ProductServiceImpl  @mehod viewProduct id is: "+id);
	    return productDAO.viewProduct(id);
	}



	@Override
	public Long totalCount() {
		logger.info("inside @class ProductServiceImpl  @mehod totalCount entry");
	    return productDAO.countAll();
	}
}
