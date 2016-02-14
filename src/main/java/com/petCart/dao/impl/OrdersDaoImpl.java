package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import javassist.NotFoundException;

import javax.persistence.TypedQuery;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.IOrdersDAO;
import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Orders;

@Repository
@Transactional
public class OrdersDaoImpl extends GenericDaoImpl<Orders> implements IOrdersDAO{

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
		logger.info("inside @class OrdersDaoimpl @method: search entry...");
		try{
			SearchCondition<Orders> sc = searchContext.getCondition(Orders.class);
			JPATypedQueryVisitor<Orders> visitor =  new JPATypedQueryVisitor<Orders>(getEntityManager(), Orders.class);
			if(sc!=null){
				sc.accept(visitor);
				visitor.visit(sc);
				TypedQuery<Orders> typedQuery = visitor.getQuery();
				if(lowerLimit>=0){
		    		typedQuery.setFirstResult(lowerLimit);
		    	}
		    	if(upperLimit>=0){
		    		typedQuery.setMaxResults(upperLimit-lowerLimit+1);
		    	}
				return typedQuery.getResultList();
			 

			}else{
				try {
					throw new NotFoundException("Invalid search query.");
				} catch (NotFoundException e) {
					logger.error("inside @class OrdersDaoimpl @method: search cause:"+e.toString());
					e.printStackTrace();
				}
			}

		}catch(Exception ex){
			logger.error("inside @class OrdersDaoimpl @method: search cause:"+ex.toString());

		}

		return null;
	}

}
