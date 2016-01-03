package com.petCart.dao.impl;

import java.util.Map;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IUserDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;

import com.petCart.model.Product;
import com.petCart.model.Users;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<Users> implements IUserDao{

	private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Override
	public long countAll(Map<String, Object> params) {
		
		return super.countAll(params);
	}

	@Override
	public Users create(Users user) {
		logger.info("inside #class UserDaoImpl #method create entry...");
		try{
			return super.create(user);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("#class UserDaoImpl #method create cause: "+e.toString());
		}
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(id);
	}

	@Override
	public Users find(Object id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Override
	public Users update(Users t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Users findUserByName(Users user) {
		logger.info("inside @class UserDaoimpl @method: findUserByName entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findUserByName").setParameter("username",user.getUsername());
			return  (Users) query.getSingleResult();
		  }catch(Exception ex){
			logger.error("Exception occured @class: UserDaoimpl @method: findUserByName @cause: "+ex.getMessage());
		}
		return null;
		
	}

	
}
