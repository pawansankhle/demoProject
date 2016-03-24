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
import com.petCart.dao.ISupplierDao;
import com.petCart.model.Product;
import com.petCart.model.Roles;
import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.model.userRoles;
import com.petCart.service.IProductService;
import com.petCart.springsecurity.security.UserInfo;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	IProductDAO productDAO;

	@Autowired 
	ISupplierDao supplierDao;

	@Override
	public Product  addProduct(Product product){
		logger.info("inside @class ProductServiceImpl  @mehod addProduct product is: "+product.toString());
		try{

			Users user = UserInfo.getCurrentUser();
			product.setCreatedtime(new Date());
			product.setModifiedtime(new Date());
			product.setShowitem(true);
			Supplier supplier = null;
			if(user !=null){
				logger.info("inside @class ProductServiceImpl  @mehod addProduct currernt username is: "+user.getUsername());
				for(Roles role : user.getRoles()){
					logger.info("inside @class ProductServiceImpl  @mehod addProduct currernt role is: "+role.getRoleName());

					if(role.getRoleName().equals(userRoles.ROLE_SUPPLIER)){
						supplier = supplierDao.findSupplierByDetailId(user);
						product.setSupplier(supplier);
						return productDAO.create(product);
					}else if(role.getRoleName().equals(userRoles.ROLE_ADMIN)){
						return productDAO.create(product);
					}
				}

			}


		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class @method cause: "+ex.toString());
		}
		return null;
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
