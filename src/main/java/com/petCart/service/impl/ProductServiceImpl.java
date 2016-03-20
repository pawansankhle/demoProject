package com.petCart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	public Product findById(Integer id) {
		return productDAO.findById(id);
	}



	@Override
	public List<Product> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType) {
		logger.info("inside @class ProductServiceImpl  @mehod search");
		 return productDAO.search(context,lowerLimit,upperLimit,orderBy,orderType);
	}



	@Override
	public Product viewProduct(Integer id) {
	  logger.info("inside @class ProductServiceImpl  @mehod viewProduct id is: "+id);
	    return productDAO.viewProduct(id);
	}



	@Override
	public Long totalCount() {
		logger.info("inside @class ProductServiceImpl  @mehod totalCount entry");
		try{
			return productDAO.countAll();
		}catch(Exception ex){
			logger.error("@class @method cause: "+ex.toString());
			return null;
		}
	    
	}



	@Override
	public Product updateProduct(Product product) {
		logger.info("inside @class ProductServiceImpl  @mehod updateProduct entry");
		try{
			
			Product oldProduct = productDAO.find(product.getId());
			if(oldProduct !=null){
				oldProduct.setName(product.getName());
				oldProduct.setModifiedtime(new Date());
				oldProduct.setDiscount(product.getDiscount());
				oldProduct.setPrice(product.getPrice());
				oldProduct.setShowitem(product.getShowitem());
				oldProduct.setQuantity(product.getQuantity());
				return productDAO.update(oldProduct);
			}
		}catch(EntityNotFoundException ex){
			logger.error("@class ProductServiceImpl @method updateProduct cause: "+ex.toString());
		}catch (Exception e) {
			logger.error("@class ProductServiceImpl @method updateProduct cause: "+e.toString());
		}
		return null;
	}
}
