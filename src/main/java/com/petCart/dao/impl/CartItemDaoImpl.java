package com.petCart.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICartItemDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Cart;
import com.petCart.model.CartItem;
import com.petCart.model.Product;

@Repository
@Transactional
public class CartItemDaoImpl extends GenericDaoImpl<CartItem> implements ICartItemDAO{

	private final Logger logger = LoggerFactory.getLogger(CartItemDaoImpl.class);
	
	@Override
	public CartItem findItembyItemId(Cart cart,Product itemId) {
		logger.info("inside @class CartItemDaoImpl @method findItembyItemId entry");
		try{
			Query query=getEntityManager().createNamedQuery("findItemByItemId").setParameter("itemId",itemId).setParameter("cartId",cart);
			return  (CartItem) query.getSingleResult();
		   
		}catch(EntityNotFoundException e){
			logger.error("inside @class CartItemDaoImpl @method: findItembyItemId cause:"+e.toString());
		}
		catch(Exception e){
			logger.error("inside @class CartItemDaoImpl @method: findItembyItemId cause:"+e.toString());
		}
		return null;
	}
	
	@Override
	public List<CartItem> findItemsbyCatId(Cart cart) {
		logger.info("inside @class CartItemDaoImpl @method findItembyItemId entry");
		try{
			Query query=getEntityManager().createNamedQuery("findItemsByCartId").setParameter("cartId",cart);
			return query.getResultList();
		}catch(Exception e){
			logger.error("inside @class CartItemDaoImpl @method: findItemsbyCatId cause:"+e.toString());
		}
		return null;
	}

}
