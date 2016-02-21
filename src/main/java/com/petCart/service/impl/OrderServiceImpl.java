package com.petCart.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICartDAO;
import com.petCart.dao.ICartItemDAO;
import com.petCart.dao.IOrderDetailDAO;
import com.petCart.dao.IOrdersDAO;
import com.petCart.dao.IUserDao;
import com.petCart.model.Cart;
import com.petCart.model.CartItem;
import com.petCart.model.OrderDetail;
import com.petCart.model.OrderStatus;
import com.petCart.model.Orders;
import com.petCart.model.Users;
import com.petCart.service.ICartService;
import com.petCart.service.IOrderService;
import com.petCart.springsecurity.security.UserInfo;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	IOrdersDAO ordersDAO;
	
	@Autowired
	ICartDAO cartDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	IOrderDetailDAO orderDetailDao;
	
	@Autowired
	ICartItemDAO cartItemDao;
	

	@Override
	public List<Orders> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
	logger.info("inside @class OrderServiceImpl  @mehod search");
		 return ordersDAO.search(context,lowerLimit,upperLimit,orderBy,orderType);
		
	}

	@Override
	public Orders findById(long id) {
		logger.info("inside @class OrderServiceImpl  @mehod findById");
		try{
			return ordersDAO.find(id);
		}catch(Exception ex){
			logger.error("@class @method findById cause: "+ex.toString());
			return null;
		}
		
	}

	@Override
	public Orders placeOrder(Integer cartId, Orders order,HttpSession  session) {
		try{
			  Users user = null;
			  Orders order1=null;
			  Authentication auth = (Authentication) session.getAttribute("authentication");
			  UserDetails udetail = (UserDetails) auth.getPrincipal();
			  
			 if(udetail != null){ 
			     user = userDao.findUserByName(udetail.getUsername());
			 }
			  Cart cart = cartDao.find(cartId);
			  order.setCreatedOn(new Date());
			  order.setStatus(OrderStatus.PLACED);
			  order.setCustomer(user);
			  order.setCustomer(user);
			  
			  if(cart!=null && user!=null && cart.getItems().size() >0){
				  order1 = ordersDAO.create(order);
				  for(CartItem item : cart.getItems()){
					     if(item.getBuyNow()){
					    	 OrderDetail orderDetail = new OrderDetail();
					    	 orderDetail.setProductId(item.getItemId());
					    	 orderDetail.setQuantity(item.getQuantity());
					    	 orderDetail.setUnitCost(item.getItemId().getOfferPrice());
					    	 orderDetail.setOrder(order1);
					    	 orderDetail = orderDetailDao.create(orderDetail);
					    	 cartItemDao.delete(item.getId());
					    }
				  }
				     cart.setTotal(0.0);
			    	 cartDao.update(cart);
				     //Users user1 = UserInfo.getCurrentUser();
				  
			        //return "{\"status\":\"ok\",\"orderId\":\""+order1.getId()+"\"}";
			       //return order1;
				  return ordersDAO.find(order1.getId());
				  
			  }
		}catch(EntityNotFoundException ex){
			logger.error("@class OrderServiceImpl @method placeOrder cause: "+ex.toString());
			return null;
		}catch(Exception ex){
			logger.error("@class OrderServiceImpl @method placeOrder cause: "+ex.toString());
			return null;
		}
		return null;
	}

	@Override
	public List<Orders> findByUserId(long id) {
		logger.info("@class OrderServiceImpl @method findByUserId entry userid is: "+id);
		try{
			 
			 return ordersDAO.findByUserId(id);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class OrderServiceImpl @method findByUserId cause:"+ex.toString());
		}
		return null;
	}

}
