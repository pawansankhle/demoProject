package com.petCart.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.cxf.transport.https.CertConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petCart.dao.ICartDAO;
import com.petCart.dao.ICartItemDAO;
import com.petCart.dao.IProductDAO;
import com.petCart.model.Cart;
import com.petCart.model.CartItem;
import com.petCart.model.Product;
import com.petCart.service.ICartService;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class CartServiceImpl implements ICartService{

	private Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	ICartDAO cartDao;

	@Autowired
	IProductDAO productDao;

	@Autowired
	ICartItemDAO cartItemDao;



	@Override
	public Cart addToCart(HttpSession session,Integer itemId) {

		logger.info("@class CartServiceimpl @method addToCart entry item id is: "+itemId);
		try{
			Cart cart=null;
			List<CartItem> items =null;
			cart = (Cart) session.getAttribute("cart");
			if(cart !=null){
				cart = cartDao.findCartByName(cart.getName());
				if(cart == null){
					logger.info("@class CartServiceimpl @method addToCart cart not fount in db creating one");
					cart = createCart(session);
				}
			}else{
				cart = createCart(session); 
			}

			Product product = productDao.findById(itemId);
			CartItem item = cartItemDao.findItembyItemId(cart,product);

			if(item == null)
			{
				logger.info("@class CartServiceimpl @method addToCart new Item adding");

				if(product !=null){
					item = new CartItem();
					item.setBuyNow(true);
					item.setItemId(product);
					item.setQuantity(1);
					item.setCartId(cart);
					items = new ArrayList<CartItem>(1);
					items.add(item);

					cart.setItems(items);
					cart.setAddedOn(new Date());
					cart.setModifiedOn(new Date());
					/*price in cart shold be after calculating discount later*/
				    Double offerPrice = product.getPrice() - ((product.getPrice()*product.getDiscount())/100);
					Double total = cart.getTotal()+(offerPrice*item.getQuantity());
					
					cart.setTotal(total);
					cart = cartDao.addToCart(cart);
					session.setAttribute("cart", cart);
					return cart;

				}else
					return null;
			}else
			{
				logger.info("@class CartServiceimpl @method addToCart updating ");
				Integer qwt = item.getQuantity();
				item.setQuantity(qwt+1);
				if(cart !=null){
					cart = cartDao.findCartByName(cart.getName());
					items = new ArrayList<CartItem>(1);
					items.add(item);
					cart.setItems(items);
					Double total = item.getItemId().getPrice()*item.getQuantity();
					cart.setTotal(cart.getTotal()+total);
					session.setAttribute("cart", cart);
					return cartDao.addToCart(cart);


				}

			}
		}catch(Exception e){

			logger.error("@class CartServiceimpl @method addToCart cause: "+e.toString());
		}
		return null;
	}

	@Override
	public String updateCart(HttpSession session,Cart cart) {
		logger.info("@class CartServiceimpl @method updateCart cart entry");
		String msg = "ok";
		Cart cart1 = null;
		try{
			cart1 = cartDao.findById(cart.getId());
			if(cart1!=null){
				for(CartItem item : cart.getItems()){
					Product p = productDao.findById(item.getItemId().getId());
					if(p !=null){
						if(item.getQuantity() != 0){
						 if((item.getQuantity() <= p.getQuantity())){
							 cart1.setTotal(item.getQuantity() * item.getItemId().getPrice());
							 item.setCartId(cart1);
						 }else{
							 msg = "Sorry Product "+p.getName()+" quantity greater then availble ";
							 break;
						 }
					}else{
						cartItemDao.delete(item.getId());
					}
				 }else{
					 msg = "Sorry currently Product"+p.getName()+"not Available";
					 break;
				 }
			 }
				
				if(cart.getItems().get(0).getCartId() !=null){
					cart.setModifiedOn(new Date());
					cart.setAddedOn(cart1.getAddedOn());
					cart.setName(cart1.getName());
					cart.setId(cart1.getId());
					cartDao.updateCart(cart);
				}
			   }else
			{
				cart1 = createCart(session);
				cart1.setItems(cart.getItems());
				cart1.setAddedOn(new Date());
				cart1.setTotal(cart.getTotal());
				cartDao.updateCart(cart1);
			}
		}catch(Exception e){
			logger.error("Exception @class CartServiceimpl @method updateCart cause: "+e.toString());
		}
		return "{\"status\":\"200\",\"msg\":\""+msg+"\"}";
	}

	@Override
	public Cart createCart(HttpSession session){
		logger.info("@class CartServiceimpl @method createCart creating  cart with id "+session.getId());
		Cart cart = new Cart();
		cart.setName(session.getId());
		cart.setItems(new ArrayList<CartItem>(1));
		cart = cartDao.create(cart);
		session.setAttribute("cart", cart);
		return cart;
	}

	@Override
	public Cart getCartByName(String cartName) {
		logger.info("@class CartServiceimpl @method getCartByName");
		try{
			Cart cart = cartDao.findCartByName(cartName);
			return cart;
			/*if(cart !=null){
				 List<CartItem> items = cartItemDao.findItemsbyCatId(cart);
				 cart.setItems(items);
				 return cart;

			 }*/
		}catch(Exception e){
			logger.error("@class CartServiceimpl @method getCartByName");
		}

		return null;
	}

	@ExceptionHandler
	@Override
	public String deleteItem(Integer id) {
		try{
			cartItemDao.delete(id);
			return "{\"status\": \"success\"}";
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("@class CartServiceimpl @method deleteItem"+ex.toString());
			return null;
		}
		
	}



}
