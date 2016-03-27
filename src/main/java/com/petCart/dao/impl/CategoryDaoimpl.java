package com.petCart.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICategoryDAO;

import com.petCart.dao.generic.impl.GenericDaoImpl;
import com.petCart.model.Category;
import com.petCart.model.Product;

@Repository
@Transactional
public class CategoryDaoimpl extends GenericDaoImpl<Category> implements ICategoryDAO{

	private final Logger logger = LoggerFactory.getLogger(CategoryDaoimpl.class);

	@Override
	public long countAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category create(Category t) {
		return super.create(t);
	}

	@Override
	public void delete(Object id) {
		 super.delete(id);
		
	}

	@Override
	public Category find(Object object) {
		return super.find(object);
	}

	@Override
	public Category update(Category t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Category> findAllCategory() {
		logger.info("inside @class CategoryDaoimpl @method: findAllCategory entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findAllCategory");
			return  query.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Exception occured @class: CategoryDaoimpl @method: findAllCategory @cause: "+ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Category> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		return super.search(context, lowerLimit, upperLimit, orderBy, orderType);
	}

	@Override
	public List<Product> findProductByCategory(Integer id) {
		logger.info("inside @class CategoryDaoimpl @method: findProductByCategory entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findCategoryById").setParameter("id", id);
			return  query.getResultList();
			
		}catch(Exception ex){
			logger.error("Exception occured @class: CategoryDaoimpl @method: findProductByCategory @cause: "+ex.getMessage());
		}
		return null;
		
	}

}
