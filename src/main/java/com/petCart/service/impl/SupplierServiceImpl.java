package com.petCart.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ISupplierDao;
import com.petCart.dao.IUserDao;
import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.service.ISupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService{
   
	@Autowired
	ISupplierDao supplierDao;
	
	@Autowired
	IUserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);
	@Override
	public List<Supplier> findAllSuppliers() {
		logger.info("inside @class UserServiceImpl  @mehod findAllSuppliers");
		try{
		     return supplierDao.findAllSuppliers();
		  }catch(Exception ex){
			  logger.error("@class UserServiceImpl @method findAllSuppliers  cause: "+ex.toString());
			  return null;
			  
		  }
	}
	

	@Override
	public List<Supplier> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType) {
		logger.info("inside @class SupplierServiceImpl  @mehod search");
		try{
		     return supplierDao.search(context,lowerLimit,upperLimit,orderBy,orderType);
		  }catch(Exception ex){
			  logger.error("@class SupplierServiceImpl @method search  cause: "+ex.toString());
			  return null;
			  
		  }
	}
	
	

	@Override
	public String deleteSupplier(Authentication authentication, long id) {
		try{
			 Supplier supplier = supplierDao.find(id);
			 Users user = userDao.find(supplier.getDetail().getId());
			 if(supplier !=null){
				 user.setEnabled(false);
				 user.setDeleted(true);
				 //supplier.setDetail(user);
				 //supplierDao.update(supplier);
				 userDao.update(user);
				 return "{\"status\":\"ok\",\"msg\":\"Supplier Deleted Successfully...\"}";
			 }
		}catch(EntityNotFoundException ex){
			logger.error("@class SupplierServiceImpl @method deleteSupplier  cause: "+ex.toString());
			return null;
			
		}catch (Exception e) {
			logger.error("@class SupplierServiceImpl @method deleteSupplier  cause: "+e.toString());
			return null;
		}
		return null;
	}


	@Override
	public Supplier changeSupplierState(Authentication auth, String action,
			long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
