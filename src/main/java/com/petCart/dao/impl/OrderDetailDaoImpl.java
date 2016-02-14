package com.petCart.dao.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IOrderDetailDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.OrderDetail;
import com.petCart.model.Orders;
import com.petCart.service.impl.OrderServiceImpl;

@Repository
@Transactional
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetail>  implements IOrderDetailDAO {

	Logger logger  = LoggerFactory.getLogger(OrderDetailDaoImpl.class);;

	@Override
	public OrderDetail create(OrderDetail t) {
		logger.info("@class @method create obj is:"+t.toString());
		return super.create(t);
	}
	
	@Override
	public OrderDetail update(OrderDetail t) {
		return super.update(t);
	}
	
	
	@Override
	public void delete(Object id) {
		super.delete(id);
	}
	
	@Override
	public OrderDetail find(Object id) {
		return super.find(id);
	}
	
	
	@Override
	public List<Orders> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

}
