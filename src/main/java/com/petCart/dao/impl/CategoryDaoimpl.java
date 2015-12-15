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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category find(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(Category t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findById(Object id) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
