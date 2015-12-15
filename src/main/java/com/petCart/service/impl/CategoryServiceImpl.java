package com.petCart.service.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petCart.dao.ICategoryDAO;
import com.petCart.dao.IProductDAO;
import com.petCart.model.Category;
import com.petCart.service.ICategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	ICategoryDAO categoryDAO;
	
	@Override
	public void addProduct(Category category) {
		

	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDAO.findAllCategory();
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

}
