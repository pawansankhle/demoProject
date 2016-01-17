package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICartDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Cart;
import com.petCart.model.Product;

@Repository
@Transactional
public class CartDaoImpl extends GenericDaoImpl<Cart> implements ICartDAO {

	private final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);
	
	
	@Override
	public Cart addToCart(Cart cart) {
		logger.info("inside @class CartDaoImpl @method addToCart entry..");
		try{
			 return super.update(cart);
			 
		}catch(Exception e){
			logger.error("@class CartDaoImpl @method addToCart cause"+e.toString());
		}
		return null;
	}

	@Override
	public Cart updateCart(Cart cart) {
		logger.info("inside @class CartDaoImpl @method updateCart entry..");
		try{
			 return super.update(cart);
			 
		}catch(Exception e){
			logger.error("@class CartDaoImpl @method updateCart cause"+e.toString());
		}
		return null;
	}

	@Override
	public List<Cart> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findByItemId(Long itemId) {
		logger.info("inside @class cartDaoimpl @method: findByItemId entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findCartByItemId").setParameter("itemId",itemId);
			return  (Cart)query.getSingleResult();
		   
		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside @class cartDaoimpl @method: findByItemId cause:"+e.toString());
		}
		return null;
	}

	@Override
	public Cart findCartByName(String name) {
		logger.info("inside @class cartDaoimpl @method: findCartByName entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findCartByName").setParameter("name",name);
			Cart cart =  (Cart) query.getSingleResult();
			return cart;
		}catch(EntityNotFoundException e){
			logger.error("inside @class cartDaoimpl @method: findCartByName entity not found with name: "+name);
		}catch(Exception e){
			logger.error("inside @class cartDaoimpl @method: findCartByName cause: "+e.toString());
		}
		return null;
	}

	
	public Cart findById(Integer id){
		logger.info("inside @class cartDaoimpl @method: findById entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findCartById").setParameter("id",id);
			Cart cart =  (Cart) query.getSingleResult();
			return cart;
		}catch(EntityNotFoundException e){
			logger.error("inside @class cartDaoimpl @method: findCartByName entity not found");
		}catch(Exception e){
			logger.error("inside @class cartDaoimpl @method: findCartByName cause: "+e.toString());
		}
		return null;
		
	}
	
}
