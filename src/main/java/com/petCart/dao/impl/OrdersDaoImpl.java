package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IOrdersDAO;
import com.petCart.dao.IUserDao;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Orders;
import com.petCart.model.Product;
import com.petCart.model.Users;

@Repository
@Transactional
public class OrdersDaoImpl extends GenericDaoImpl<Orders> implements IOrdersDAO{

	
	@Autowired
	IUserDao userDao;
	
	private final Logger logger = LoggerFactory.getLogger(OrdersDaoImpl.class);
	@Override
	public long countAll(Map<String, Object> params) {
		return super.countAll(params);
	}

	@Override
	public Orders create(Orders order) {
		return super.create(order);
	}

	@Override
	public void delete(Object id) {
		super.delete(id);
		
	}

	@Override
	public Orders find(Object id) {
		return super.find(id);
	}

	@Override
	public Orders update(Orders order) {
		return super.update(order);
	}

	@Override
	public List<Orders> findAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Orders> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
	   
		return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
	}
	

	@Override
	public List<Orders> findByUserId(Integer id) {
		try{
			Users user = userDao.find(id);
			if(user !=null){
				Query query=getEntityManager().createNamedQuery("findOrderByUserId").setParameter("user",user);
				return  query.getResultList();
				
			}
			
		}catch(Exception ex){
			logger.error("@class OrdersDaoimpl @method findByUserId cause: "+ex.toString());
		}
		return null;
	}

}
