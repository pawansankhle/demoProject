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
import com.petCart.model.Supplier;
import com.petCart.model.Users;
import com.petCart.service.ISupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService{
   
	@Autowired
	ISupplierDao supplierDao;
	
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
	public Supplier changeSupplierState(Authentication auth,String action,long id) {
		try{
			  Supplier suppier = supplierDao.find(id);
			  if(suppier != null){
				  if(action.equalsIgnoreCase("disable")){
					  suppier.setEnabled(false);
				  }else if(action.equalsIgnoreCase("enable")){
					  suppier.setEnabled(true);
				  }
				  return supplierDao.update(suppier);
				 }
			  
		}catch(EntityNotFoundException ex){
			logger.error("@class SupplierServiceImpl @method changeSupplierState  cause: "+ex.toString());
			return null;
			
		}catch (Exception e) {
			logger.error("@class SupplierServiceImpl @method changeSupplierState  cause: "+e.toString());
			return null;
		}
		
		
		return null;
	}

	@Override
	public String deleteSupplier(Authentication authentication, long id) {
		try{
			 Supplier suppier = supplierDao.find(id);
			 if(suppier !=null){
				 suppier.setDeleted(true);
				 supplierDao.update(suppier);
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
}
