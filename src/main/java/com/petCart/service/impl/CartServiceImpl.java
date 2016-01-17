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
	public Cart addToCart(HttpSession session,Long itemId) {

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
					item.setBuyNow(false);
					item.setItemId(product);
					item.setQuantity(1);
					item.setCartId(cart);
					items = new ArrayList<CartItem>(1);
					items.add(item);

					cart.setItems(items);
					cart.setAddedOn(new Date());
					cart.setModifiedOn(new Date());
					cart.setTotal(product.getOfferPrice()*item.getQuantity());
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
					Double total = item.getItemId().getOfferPrice()*item.getQuantity();
					cart.setTotal(total);
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
	public Cart updateCart(HttpSession session,Cart cart) {
		logger.info("@class CartServiceimpl @method updateCart cart entry");
		try{
			Cart cart1 = cartDao.findById(cart.getId());
			if(cart1!=null){
				for(CartItem item : cart.getItems()){
					item.setCartId(cart1);
				}
				cart.setModifiedOn(new Date());
				cart.setAddedOn(cart1.getAddedOn());
				cart.setName(cart1.getName());
				return cartDao.updateCart(cart);
			}else
			{
				cart1 = createCart(session);
				cart1.setItems(cart.getItems());
				cart1.setAddedOn(new Date());
				cart1.setTotal(cart.getTotal());
				return cartDao.updateCart(cart1);
			}
		}catch(Exception e){
			logger.error("Exception @class CartServiceimpl @method updateCart cause: "+e.toString());
		}
		return null;
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



}
